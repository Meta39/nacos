package com.jw.ums.mapper;

import com.jw.ums.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserMapper {

    //根据ID查询
    User select(Integer id);
    User selectByUsername(String username);

    //查询全部
    List<User> selectAll();

    //新增
    int insert(User user);

    //更新
    int update(User user);
    int updatePassword(Integer userId,String name,String password);

    //删除
    int delete(Integer id);

    int userImgUpload(Integer userId, String url);
}

