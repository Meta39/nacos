package com.jw.ums.entity;

import java.util.Date;

public class RoleNode {
    /**
     * 角色权限表
     */
    private Long id;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 权限ID
     */
    private Integer nodeId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除，0否1是
     */
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
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

