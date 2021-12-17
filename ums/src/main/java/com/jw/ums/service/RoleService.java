package com.jw.ums.service;

import com.jw.common.entity.ums.Role;
import com.jw.ums.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    //根据ID查询
    public Role select(Integer id) {
        return roleMapper.select(id);
    }

    //查询全部
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    //新增
    public int insert(Role role) {
        return roleMapper.insert(role);
    }

    //更新
    public int update(Role role) {
        return roleMapper.update(role);
    }

    //删除
    public int delete(Integer id) {
        return roleMapper.delete(id);
    }
}

