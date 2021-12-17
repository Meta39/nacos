package com.jw.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.common.result.Res;
import com.jw.gateway.redis.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 全局过滤器
 **/
@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(GatewayFilter.class);
    @Value("${token-overtime}")
    private int tokenOvertime;
    @Value("${filter-path}")
    private String filterPath;

    @Resource
    RedisUtils redisUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        DataBuffer buffer;
        String requestPath = request.getPath().toString(); //前端请求URI
        String token = request.getHeaders().getFirst("token");
        boolean harContains = false;//白名单URI
        //遍历配置文件中白名单URI地址
        for (String path : filterPath.split(",")) {
            if (requestPath.contains(path)) {
                harContains = true;
                break;
            }
        }

        //RUI过滤
        if (harContains) {//URI白名单
            return chain.filter(exchange);
        } else if (StringUtils.isNotBlank(token) && redisUtils.hasKey(token)) {//有token
            redisUtils.set(token, redisUtils.get(token), tokenOvertime); //重置redis过期时间
            return chain.filter(exchange);
        } else {//返回错误信息给前端
            Res res = new Res();
            res.setStatus(-1);
            res.setData("token不存在");

            byte[] bytes = new byte[0];
            try {
                bytes = new ObjectMapper().writeValueAsBytes(res);
            } catch (JsonProcessingException e) {
                logger.error("错误信息转换异常！"+e);
            }
            buffer = response.bufferFactory().wrap(bytes);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
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
