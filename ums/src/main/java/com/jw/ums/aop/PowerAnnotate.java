package com.jw.ums.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PowerAnnotate {
    String value() default "";//执行切点前需要的权限
}
