package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@TableName("t_group")
public class Group {
    private static final long serialVersionUID = 1L;
    /**
     *  主键标识
     */
    private Long ID;
    /**
     * 组名
     */
    private String groupName;

    /**
     * 组负责人
     */
    private String groupPrincipal;

    /**
     * 负责人id
     */
    private Long principalId;

    /**
     * 公司id
     */
    private Long companyId;


    /**
     * 公司名字
     */
    private String companyName;

    /**
     * 进场附件
     */
    private String enterAttachment;

    /**
     * 进场时间
     */
    private Date enterTime;

    /**
     * 出场时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date outTime;

    /**
     * 备注
     */
    private String remark;

    /**
     *级别
     */
    private Integer grade;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createAt;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date updateAt;

    /**
     * 用来存该组别下的工人
     */
    private List<Long> userInGroup;

    private List<UserForWorkType> userList;

    public List<UserForWorkType> getUserList() {
        return userList;
    }

    public void setUserList(List<UserForWorkType> userList) {
        this.userList = userList;
    }

    public List<Long> getUserInGroup() {
        return userInGroup;
    }

    public void setUserInGroup(List<Long> userInGroup) {
        this.userInGroup = userInGroup;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPrincipal() {
        return groupPrincipal;
    }

    public void setGroupPrincipal(String groupPrincipal) {
        this.groupPrincipal = groupPrincipal;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public String getEnterAttachment() {
        return enterAttachment;
    }

    public void setEnterAttachment(String enterAttachment) {
        this.enterAttachment = enterAttachment;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
