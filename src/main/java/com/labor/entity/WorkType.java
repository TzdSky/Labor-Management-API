package com.labor.entity;

/**
 * @author BoCong
 * @date 2022/5/7
 */
public class WorkType {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String workTypeName;

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
