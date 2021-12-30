package com.jw.log.mapper;


import com.jw.common.entity.log.Log;

import java.util.List;

public interface LogMapper {

    //根据ID查询
    Log select(Long id);

    //查询全部
    List<Log> selectAll();

    //新增
    int insert(Log log);

}

