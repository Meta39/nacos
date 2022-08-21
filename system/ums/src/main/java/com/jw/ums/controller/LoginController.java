package com.jw.ums.controller;

import com.jw.ums.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 登录登出接口
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("login")
    public Map<String,Object> login(@RequestParam String username,@RequestParam String password){
        return loginService.login(username,password);
    }

    /**
     * 登出
     * @param token token
     */
    @PostMapping("logout")
    public void logout(@RequestParam String token){
        loginService.logout(token);
    }
}
