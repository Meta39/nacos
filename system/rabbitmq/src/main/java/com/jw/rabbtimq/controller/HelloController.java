package com.jw.rabbtimq.controller;

import com.jw.rabbtimq.openfeign.HelloFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private HelloFeign helloFeign;

    @GetMapping("hello")
    public String hello(){
        return helloFeign.hello();
    }
}
