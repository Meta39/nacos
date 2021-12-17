package com.jw.common.entity.ums;

import java.util.Date;

public class Role {
    /**
     * 角色ID
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 禁用角色，0否1是
     */
    private Boolean disable;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 删除角色，0否1是
     */
    private Boolean deleted;

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

