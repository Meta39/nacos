package com.jw.ums.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.common.entity.ums.LoginInfo;
import com.jw.common.result.Err;
import com.jw.ums.redis.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class PowerAspect {
    @Resource
    private RedisUtils redisUtils;

    /**
     * 执行切点之前，校验当前登录用户是否有权限访问
     */
    @Before(value = "@annotation(com.jw.ums.aop.PowerAnnotate)")
    public void doBefore(JoinPoint point) throws JsonProcessingException, NoSuchMethodException {
        //开始打印日志
        Object target = point.getTarget();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
        PowerAnnotate powerAnnotate = method.getAnnotation(PowerAnnotate.class);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null)throw new Err("RequestAttributes请求属性不能为空");
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader("token");
        if (redisUtils.hasKey(token)){
            LoginInfo loginInfo = new ObjectMapper().readValue(redisUtils.get(token).toString(),LoginInfo.class);//当前登录用户信息，包括权限。
            List<String> nodes = loginInfo.getNodes();//获取权限列表
            if (!nodes.contains(powerAnnotate.value())) throw new Err("您无权访问");
        }
    }

}
