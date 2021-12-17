package com.jw.common.entity.ums;

import java.util.Date;

public class Node {
    /**
     * 权限ID
     */
    private Integer id;
    /**
     * 菜单层级
     */
    private Integer pId;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String icon;
    /**
     * 类型：0菜单1按钮
     */
    private Boolean type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 权限值
     */
    private String nodeValue;
    /**
     * 组件
     */
    private String component;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue == null ? null : nodeValue.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", nodeValue='" + nodeValue + '\'' +
                ", component='" + component + '\'' +
                '}';
    }
}

