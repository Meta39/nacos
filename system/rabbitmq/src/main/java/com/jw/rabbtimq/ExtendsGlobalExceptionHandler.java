package com.jw.rabbtimq;

import com.jw.core.base.Res;
import com.jw.web.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 全局异常处理扩展
 */
@RestControllerAdvice
public class ExtendsGlobalExceptionHandler extends GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExtendsGlobalExceptionHandler.class);

    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public Res bindException(BindException e) {
        log.error("参数校验异常{}", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return Res.err("参数校验异常：" + e.getBindingResult().getFieldError().getDefaultMessage());
    }

}
