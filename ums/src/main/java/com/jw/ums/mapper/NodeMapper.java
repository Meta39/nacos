package com.jw.ums.mapper;

import com.jw.common.entity.ums.Node;

import java.util.List;

public interface NodeMapper {

    //根据ID查询
    Node select(Integer id);

    //查询全部
    List<Node> selectAll();

    //新增
    int insert(Node node);

    //更新
    int update(Node node);

    //删除
    int delete(Integer id);

}

