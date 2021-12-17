package com.jw.ums.service;

import com.jw.ums.entity.UserRole;
import com.jw.ums.mapper.UserRoleMapper;
import com.jw.ums.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

    //根据ID查询
    public UserRole select(Long id) {
        return userRoleMapper.select(id);
    }

    //查询全部
    public List<UserRole> selectAll() {
        return userRoleMapper.selectAll();
    }

    //新增
    public int insert(UserRole userRole) {
        return userRoleMapper.insert(userRole);
    }

    //更新
    public int update(UserRole userRole) {
        return userRoleMapper.update(userRole);
    }

    //删除
    public int delete(Long id) {
        return userRoleMapper.delete(id);
    }
}

