package com.jw.ums.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.common.entity.log.Log;
import com.jw.ums.openfeign.LogFeign;
import feign.FeignException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Resource
    private LogFeign logFeign;

    @Pointcut("@annotation(com.jw.ums.aop.LogAnnotate)")
    public void logOperation() {}

    /**
     * 切点之前执行
     */
//    @Before("logOperation()")
    public void doBefore(JoinPoint point) throws JsonProcessingException {
        //开始打印日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取自定义注解的信息
        logger.info("====================================================");
        logger.info("URL：{}",request.getRequestURL().toString());
        logger.info("HTTP Method：{}",request.getMethod());
        logger.info("Class Method：{}.{}",point.getSignature().getDeclaringTypeName(),point.getSignature().getName());
        logger.info("IP：{}",request.getRemoteAddr());
        logger.info("Request Args：{}",new ObjectMapper().writeValueAsString(point.getArgs()));
    }

    /**
     * 切点之后执行
     */
//    @After("logOperation()")
    public void  doAfter(){
        logger.info("====================================================");
    }

    /**
     * 抛出异常时执行
     */
    @AfterThrowing("logOperation()")
    public void afterThrowing(JoinPoint point) throws JsonProcessingException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        try {
            logFeign.update(new Log(request.getHeader("userName"), Long.valueOf(request.getHeader("userId")), "IP：" + request.getRemoteAddr() + "，URL：" + request.getRequestURL().toString() + "，Request Args：" + new ObjectMapper().writeValueAsString(point.getArgs()), false));
        }catch (FeignException e){
            logger.error("日志系统崩了：",e);
        }
    }

    /**
     * 环绕是最后执行的
     */
    @Around("logOperation()&&@annotation(logAnnotate)")
    public Object doAround(ProceedingJoinPoint point, LogAnnotate logAnnotate) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = point.proceed();//执行切点
        try {
            logFeign.update(new Log(request.getHeader("userName"),Long.valueOf(request.getHeader("userId")),"IP："+request.getRemoteAddr()+"，URL："+request.getRequestURL().toString()+"，Request Args："+new ObjectMapper().writeValueAsString(point.getArgs())+"，Response Args："+new ObjectMapper().writeValueAsString(result),true));
        }catch (FeignException e){
            logger.error("日志系统崩了：",e);
        }
        logger.info("Response Args：{}",new ObjectMapper().writeValueAsString(result));//打印参数
        logger.info("LogAOP param value:{}", logAnnotate.value());
        return result;
    }
}
