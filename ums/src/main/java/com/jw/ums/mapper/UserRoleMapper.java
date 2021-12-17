package com.jw.ums.mapper;

import com.jw.ums.entity.UserRole;

import java.util.List;

public interface UserRoleMapper {

    //根据ID查询
    UserRole select(Long id);
    List<UserRole> selectByUserId(Integer id);

    //查询全部
    List<UserRole> selectAll();

    //新增
    int insert(UserRole userRole);

    //更新
    int update(UserRole userRole);

    //删除
    int delete(Long id);

}

