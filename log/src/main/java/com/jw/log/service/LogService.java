package com.jw.log.service;

import com.jw.common.entity.log.Log;
import com.jw.log.mapper.LogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class LogService {
    @Resource
    private LogMapper logMapper;

    //根据ID查询
    public Log select(Long id) {
        return logMapper.select(id);
    }

    //查询全部
    public List<Log> selectAll() {
        return logMapper.selectAll();
    }

    //新增
    public Integer insert(Log log) {
        return logMapper.insert(log);
    }
}

