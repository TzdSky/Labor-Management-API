package com.labor.service;

import com.labor.entity.Attendance;
import com.labor.entity.AttendanceSearch;
import com.labor.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Tian
 * @date 2022/5/8
 */
public interface AttendanceService {

    /**
     * 班组页面添加或者删除user之后修改 user考勤组状态
     */
    int updateUserGroupID(List<Long> userID,  Long attendanceID);


    /**
     * 根据id删除考勤组
     */
    int deleteAttendanceGroupByID(Long attID);


    /**
     * 删除考勤组后清空绑定的user
     * @return
     */
    int clearUserAttendanceID(Long attID);

    /**
     * 根据考勤组名字查重(精确查询)
     * @param attGroupName
     * @return
     */
    int getAttRecordsByAttGroupName(String attGroupName);

    /**
     * 根据考勤组名字查重(模糊查询用于分页)
     * @param attGroupName
     * @return
     */
    int getAttGroupCount(String attGroupName);


    /**
     *新增考勤组
     **/
    Long insertAttendanceGroup(Attendance attendance);

    /**
     * 修改考勤组
     */
    int updateAttGroup (Attendance attendance);


    /**
     * 获取考勤组列表
     */
    Page<Attendance> getAttGroupList(HttpServletRequest request, Pageable page);


    /**
     * 根据考勤组名称查询考勤列表  按日显示
     */
    Page<AttendanceSearch> getAttSearchList(HttpServletRequest request, Pageable page);


    Attendance findGroupByID (Long ID);

}
