package com.labor.entity;

import java.util.Date;

/**
 * @author Tian
 * @date 2022/5/11
 */
public class Account {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String password;

    private Date createDate;

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
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
