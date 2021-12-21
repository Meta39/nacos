package com.jw.ums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.common.result.Err;
import com.jw.common.result.Res;
import com.jw.ums.aop.IgnoreResAnnotate;
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

@RestControllerAdvice
public class ReturnRes implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
        IgnoreResAnnotate ignoreResAnnotate = AnnotationUtils.findAnnotation(annotatedElement, IgnoreResAnnotate.class);
        return ignoreResAnnotate == null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof LinkedHashMap) {
            LinkedHashMap map = (LinkedHashMap) o;
            if (map.get("status") != null) {
                int status = (int) map.get("status");
                if (404 == status) throw new Err(404,"找不到页面");
            }
        }
        if (o instanceof String) {
            try {
                return new ObjectMapper().writeValueAsString(new Res(o));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else if (o instanceof Res) {
            return o;
        }
        return new Res(o);
    }
}
