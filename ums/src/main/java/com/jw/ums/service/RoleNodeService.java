package com.jw.ums.service;

import com.jw.ums.entity.RoleNode;
import com.jw.ums.mapper.RoleNodeMapper;
import com.jw.ums.service.RoleNodeService;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class RoleNodeService {
    @Resource
    private RoleNodeMapper roleNodeMapper;

    //根据ID查询
    public RoleNode select(Long id) {
        return roleNodeMapper.select(id);
    }

    //查询全部
    public List<RoleNode> selectAll() {
        return roleNodeMapper.selectAll();
    }

    //新增
    public int insert(RoleNode roleNode) {
        return roleNodeMapper.insert(roleNode);
    }

    //更新
    public int update(RoleNode roleNode) {
        return roleNodeMapper.update(roleNode);
    }

    //删除
    public int delete(Long id) {
        return roleNodeMapper.delete(id);
    }
}

