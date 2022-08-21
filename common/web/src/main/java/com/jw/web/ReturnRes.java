package com.jw.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.core.base.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.LinkedHashMap;

/**
 * 全局返回结果处理
 * 1、封装成Res再返回给前端
 * 2、在Controller层接口调用加上@IgnoreResAnnotate注解，可以不返回Res，返回原始类型。PS：@IgnoreResAnnotate注解通常用于openfeign调用时使用
 */
@RestControllerAdvice
public class ReturnRes implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(ReturnRes.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //判断是否有加自定义注解，有就跳过，不返回Res
        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
        IgnoreResAnnotate ignoreResAnnotate = AnnotationUtils.findAnnotation(annotatedElement, IgnoreResAnnotate.class);
        return ignoreResAnnotate == null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof String) {//String要特殊处理
            try {
                return new ObjectMapper().writeValueAsString(new Res(o));
            } catch (JsonProcessingException e) {
                log.error("JSON处理异常", e);
                return Res.err("JSON处理异常");
            }
        } else if (o instanceof Res) {//本身是Res直接返回即可
            return o;
        } else if (o instanceof LinkedHashMap) {//解决404、500等spring没有捕获的异常问题，只能放到最后的判断条件去判断
            LinkedHashMap map = (LinkedHashMap) o;//强转
            if (map.get("status") != null) {
                int status = (int) map.get("status");
                String error = (String) map.get("error");
                String message = (String) map.get("message");
                String path = (String) map.get("path");
                return Res.err(status, "error：" + error + ",message:" + message + ",path:" + path);
            }
        }
        return new Res(o);
    }
}
