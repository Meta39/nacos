package com.jw.ums.aop;

import java.lang.annotation.*;

/**
 * 不返回Res结果类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreResAnnotate {
}
