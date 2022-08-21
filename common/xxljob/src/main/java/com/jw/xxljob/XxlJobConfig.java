package com.jw.xxljob;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job分布式任务调度配置
 */
@Configuration
public class XxlJobConfig {

    /**
     * xxl-job admin url地址，如：http://127.0.0.1:9998/xxl_job
     */
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    /**
     * 名称，建议和子系统名称一致
     */
    @Value("${xxl.job.executor.app-name}")
    private String appName;

    /**
     * 定时任务端口，即子系统需要额外的一个端口来被调用
     */
    @Value("${xxl.job.executor.port}")
    private int port;

    /**
     * 日志保存天数
     */
    @Value("${xxl.job.executor.log-retention-days}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appName);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }
}

