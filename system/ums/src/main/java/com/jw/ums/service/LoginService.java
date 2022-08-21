package com.jw.ums.service;

import com.jw.core.base.Err;
import com.jw.ums.dao.LoginDao;
import com.jw.ums.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    @Value("${token-overtime}")
    private int tokenOvertime;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private LoginDao loginDao;

    public Map<String, Object> login(String name, String password) {
        User user = loginDao.queryByName(name);
        if (user == null) {
            throw new Err("用户不存在！");
        } else if (!password.equals(user.getPassword())) {
            throw new Err("密码错误！");
        } else {
            String token = UUID.randomUUID().toString();
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("userId", user.getId());
            map.put("userName", user.getName());
            redisTemplate.opsForValue().set(token, map, tokenOvertime, TimeUnit.SECONDS);
            return map;
        }
    }

    public void logout(String token){
        boolean hasKey = redisTemplate.hasKey(token);
        if (hasKey){
            redisTemplate.delete(token);
        }
    }
}
