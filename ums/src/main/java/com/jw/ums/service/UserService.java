package com.jw.ums.service;

import com.jw.ums.entity.User;
import com.jw.ums.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    //根据ID查询
    public User select(Integer id) {
        return userMapper.select(id);
    }

    //查询全部
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    //新增
    public int insert(User user) {
        if (StringUtils.isBlank(user.getPassword())){
            user.setPassword(DigestUtils.md5DigestAsHex((user.getName()+"a").getBytes()));
        }else {
            user.setPassword(DigestUtils.md5DigestAsHex((user.getName()+user.getPassword()).getBytes()));
        }
        return userMapper.insert(user);
    }

    //更新
    public int update(User user) {
        return userMapper.update(user);
    }

    //删除
    public int delete(Integer id) {
        return userMapper.delete(id);
    }

}

