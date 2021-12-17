package com.jw.ums.mapper;

import com.jw.common.entity.ums.Role;

import java.util.List;

public interface RoleMapper {

    //根据ID查询
    Role select(Integer id);

    //查询全部
    List<Role> selectAll();

    //新增
    int insert(Role role);

    //更新
    int update(Role role);

    //删除
    int delete(Integer id);

}

