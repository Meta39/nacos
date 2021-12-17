package com.jw.ums.service;

import com.jw.common.entity.ums.Node;
import com.jw.ums.mapper.NodeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class NodeService {
    @Resource
    private NodeMapper nodeMapper;

    //根据ID查询
    public Node select(Integer id) {
        return nodeMapper.select(id);
    }

    //查询全部
    public List<Node> selectAll() {
        return nodeMapper.selectAll();
    }

    //新增
    public int insert(Node node) {
        return nodeMapper.insert(node);
    }

    //更新
    public int update(Node node) {
        return nodeMapper.update(node);
    }

    //删除
    public int delete(Integer id) {
        return nodeMapper.delete(id);
    }
}

