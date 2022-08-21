package com.jw.web;

import com.jw.core.base.Err;
import com.jw.core.base.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 * 子系统继承GlobalExceptionHandler类，继承以后加上@RestControllerAdvice注解对其进行扩展使用即可。
 */
//@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常
     */
    @ExceptionHandler(value = Err.class)
    public Res Err(Err e) {
        //e.getMsg()获取的是自己定义的msg信息，e.getMessage()获取的是null，因为我们没有填值。
        return Res.err(e.getCode(), e.getMsg());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Res exceptionHandler(NullPointerException e) {
        log.error("空指针异常", e);
        return Res.err("空指针异常" + e.getMessage());
    }

    /**
     * 500异常
     */
    @ExceptionHandler(value = Exception.class)
    public Res exception(Exception e) {
        log.error("500异常", e);
        return Res.err("后端服务器异常：" + e.getMessage());
    }

    /**
     * 缺少请求参数异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Res missingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数{}", e.getParameterName());
        return Res.err("缺少请求参数：" + e.getParameterName());
    }

}
