package com.jw.ums.dao;

import com.jw.ums.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDao {

    /**
     * 根据ID查询
     */
    User queryByName(@Param("name") String name);
}
