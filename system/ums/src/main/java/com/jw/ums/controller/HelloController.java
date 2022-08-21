package com.jw.ums.controller;

import com.jw.web.IgnoreResAnnotate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @IgnoreResAnnotate //被openfeign调用时不需要返回Res
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }
}
