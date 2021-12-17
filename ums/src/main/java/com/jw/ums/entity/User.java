package com.jw.ums.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User {

    private Integer id;//ID
    private String name;//用户名
    @JsonIgnore
    private String password;//密码
    private String url;//用户头像url
    private Boolean disable;//禁用用户，0否1是
    private Date createTime;//创建时间
    private Boolean deleted;//删除用户，0否1是

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}

