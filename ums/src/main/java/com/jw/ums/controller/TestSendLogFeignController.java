package com.jw.ums.controller;

import com.jw.common.entity.log.Log;
import com.jw.ums.openfeign.LogFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("log")
public class TestSendLogFeignController {
    @Resource
    LogFeign logFeign;

    @GetMapping("select")
    public Log select(@RequestParam Long id){
        return logFeign.selectFeign(id);
    }
}
