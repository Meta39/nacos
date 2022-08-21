package com.jw.rabbtimq.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name="ums")//nacos配置的被调用的application名称
public interface HelloFeign {

    @GetMapping("hello")
    public String hello();
}
