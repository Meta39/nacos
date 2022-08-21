package com.jw.ums.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private static final long serialVersionUID = 879557630328986457L;
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
