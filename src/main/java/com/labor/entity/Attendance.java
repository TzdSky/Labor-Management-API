package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

/**
 * @author Tian
 * @date 2022/5/9
 */
@TableName("t_attendance_group")
@Data
public class Attendance {
    private static final long serialVersionUID = 1L;
    //主键
    private Long ID;

    //考勤组名称
    private String attGroupName;

    //考勤地点
    private String location;

    //修改时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date updateAt;

    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createAt;

    //页面输入修改考勤组的userid集合
    private List<Long> userInAttGroup;

    private String userLists;


    //统计人数
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

    public String getUserLists() {
        return userLists;
    }

    public void setUserLists(String userLists) {
        this.userLists = userLists;
    }
}
