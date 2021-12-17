package com.jw.common.entity.log;


public class Log {
    /**
     * 日志ID
     */
    private Long id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 操作内容
     */
    private String content;
    /**
     * 结果，0失败，1成功
     */
    private Boolean success;

    public Log(){}
    public Log(String userName, Long userId, String content, Boolean success) {
        this.userName = userName;
        this.userId = userId;
        this.content = content;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
