package com.jw.web;

import java.lang.annotation.*;

/**
 * 禁止全局返回结果处理，通常用户openfeign调用的接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreResAnnotate {
}
