package com.jw.ums.service;

import com.jw.ums.entity.User;
import com.jw.ums.dao.UserDao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 根据ID查询
     */
    public User queryById(Integer id) {
        User user = userDao.queryById(id);
        //不返回password处理
        user.setPassword(null);
        return user;
    }

    /**
     * 查询全部
     */
    public List<User> findAll() {
        List<User> userList = userDao.findAll();
        //不返回password处理
        userList.stream().filter(user -> user.getPassword()!=null).forEach(user -> user.setPassword(null));
        return userList;
    }

    /**
     * 新增
     */
    public Integer insert(User user) {
        return userDao.insert(user);
    }

    /**
     * 跟新
     */
    public Integer update(User user) {
        return userDao.update(user);
    }

    /**
     * 删除
     */
    public Integer deleteById(Integer id) {
        return userDao.deleteById(id);
    }
}
