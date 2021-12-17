package com.jw.ums.mapper;

import com.jw.ums.entity.RoleNode;

import java.util.List;

public interface RoleNodeMapper {

    //根据ID查询
    RoleNode select(Long id);
    List<RoleNode> selectByRoleId(Integer roleId);

    //查询全部
    List<RoleNode> selectAll();

    //新增
    int insert(RoleNode roleNode);

    //更新
    int update(RoleNode roleNode);

    //删除
    int delete(Long id);

}

