package com.labor.entity;

/**
 * @author BoCong
 * @date 2022/5/8
 */
public class Project {
    private static final long serialVersionUID = 1L;
    private Long projectId;
    private String projectName;
    private Long companyId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
