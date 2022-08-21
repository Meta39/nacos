package com.jw.dynamicmybatis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 数据源2切面
 */
@Aspect
@Order(1)
@Component
public class DataSource2Aspect {

    @Around("@annotation(com.jw.dynamicmybatis.DataSource2)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try{
            //切换数据源2
            DynamicDataSourceContextHolder.setDateSourceType(HikariConfig.DATA_SOURCE2);
            return point.proceed();
        }finally {
            DynamicDataSourceContextHolder.clearDateSourceType();
        }
    }
}
