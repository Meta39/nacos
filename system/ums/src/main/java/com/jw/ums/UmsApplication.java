package com.jw.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.jw"})
public class UmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsApplication.class,args);
    }
}
