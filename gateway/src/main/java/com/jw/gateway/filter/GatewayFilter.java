package com.jw.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.core.base.Err;
import com.jw.core.base.Res;
import com.jw.core.base.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * gateway网关过滤器
 **/
@Component
@RefreshScope   //变量改变时自动刷新
public class GatewayFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(GatewayFilter.class);

    /**
     * token过期时间
     */
    @Value("${token-overtime}")
    private int tokenOvertime;

    /**
     * uri白名单
     */
    @Resource
    private PassRequire passRequire;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String token = request.getHeaders().getFirst("token");
        //前端请求URI
        String requestUri = request.getPath().toString();
        //是否包含白名单uri
        boolean pass = passRequire.getPath().stream().anyMatch(x -> requestUri.equals("/"+x));
//        log.info("完整请求uri：{}",requestUri);

        if (pass) {
            //无token，uri白名单
            return chain.filter(exchange);
        } else if (StringUtils.hasLength(token) && Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
            //有token，非uri白名单
            redisTemplate.expire(token,tokenOvertime, TimeUnit.SECONDS);//刷新token过期时间
            return chain.filter(exchange);
        } else {
            //非法请求，抛出异常
            byte[] bytes = new byte[0];
            try {
                bytes = new ObjectMapper().writeValueAsBytes(!StringUtils.hasLength(token) ? Res.err("请求头token不能为空！") : Res.err(Status.NOT_LOGIN.getStatus(), Status.NOT_LOGIN.getMsg()));
            } catch (JsonProcessingException e) {
                log.error("JSON处理异常：",e);
                throw new Err("JSON处理异常");
            }catch (Exception e){
                log.error("未知异常：",e);
            }
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
        }
    }

    /**
     * 权重，值越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
