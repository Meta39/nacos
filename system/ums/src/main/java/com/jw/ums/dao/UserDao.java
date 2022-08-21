package com.jw.ums.dao;

import com.jw.ums.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 根据ID查询
     */
    User queryById(@Param("id") Integer id);

    /**
     * 查询全部
     */
    List<User> findAll();

    /**
     * 新增
     */
    int insert(User user);

    /**
     * 更新
     */
    int update(User user);

    /**
     * 删除
     */
    int deleteById(@Param("id") Integer id);

}
