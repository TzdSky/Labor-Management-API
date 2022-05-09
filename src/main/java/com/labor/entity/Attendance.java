package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

/**
 * @author Tian
 * @date 2022/5/9
 */
@TableName("t_attendance_group")
public class Attendance {
    private static final long serialVersionUID = 1L;
    //主键
    private Long ID;

    //考勤组名称
    private String attGroupName;

    //考勤地点
    private String location;

    //修改时间
    private Date updateAt;

    //创建时间
    private Date createAt;

    private List<Long> userInAttGroup;

    private Integer userCounts;

    public Integer getUserCounts() {
        return userCounts;
    }

    public void setUserCounts(Integer userCounts) {
        this.userCounts = userCounts;
    }

    public List<Long> getUserInAttGroup() {
        return userInAttGroup;
    }

    public void setUserInAttGroup(List<Long> userInAttGroup) {
        this.userInAttGroup = userInAttGroup;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getAttGroupName() {
        return attGroupName;
    }

    public void setAttGroupName(String attGroupName) {
        this.attGroupName = attGroupName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
