package com.labor.entity;

import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/7
 */
public class WorkType {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String workTypeName;

    private List<UserForWorkType> userList;

    public List<UserForWorkType> getUserList() {
        return userList;
    }

    public void setUserList(List<UserForWorkType> userList) {
        this.userList = userList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }
}
