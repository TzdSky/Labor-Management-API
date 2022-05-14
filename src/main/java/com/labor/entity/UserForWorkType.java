package com.labor.entity;

/**
 * @author Tian
 * @date 2022/5/14
 */
public class UserForWorkType {
    private static final long serialVersionUID = 1L;

    private Long userID;
    private String userName;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
