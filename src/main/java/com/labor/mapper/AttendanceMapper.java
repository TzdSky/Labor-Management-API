package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labor.entity.Attendance;
import com.labor.entity.AttendanceSearch;
import com.labor.entity.UserForWorkType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Tian
 * @date 2022/5/8
 */
@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {
    /**
     * 根据id删除考勤组
     * @param id
     */
    int deleteAttendanceGroupByID(@Param("id") Long id);

    /**
     *  @param userID 用户id
     * @param attendanceID  组别id
     * 班组页面添加或者删除user之后修改 user考勤组状态
     */
    int updateUserAttendanceID(@Param("userIDs")List<Long> userID, @Param("attendanceID")Long attendanceID);

    /**
     * 删除考勤组后清空绑定的user
     * @return
     */
    int clearUserAttendanceID(@Param("attGroupId") Long attGroupId );

    /**
     * 根据考勤组名字查重(精确查询)
     * @param attGroupName
     * @return
     */
    int getAttRecordsByAttGroupName(@Param("attGroupName") String attGroupName);

    /**
     * 根据考勤组名字查重(模糊查询用于分页)
     * @param attGroupName
     * @return
     */
    int getAttGroupCount(@Param("attGroupName") String attGroupName);


    /**
     *新增考勤组
     **/
    Long insertAttendanceGroup(Attendance attendance);

    /**
     * 修改考勤组
     */
    int updateAttGroup (Attendance attendance);

    /**
     * 根据考勤组名称查询考勤组列表
     */
    List<Attendance> getAttGroupList(@Param("queryParams")Map<String, Object> queryParams);


    /**
     * 获取查询考勤列表的总数
     * @param queryParams
     * @return
     */
    int getSearchCount(@Param("queryParams")Map<String, Object> queryParams);

    /**
     * 根据考勤组名称查询考勤列表  按日显示
     */
    List<AttendanceSearch> getAttSearchList(@Param("queryParams")Map<String, Object> queryParams);

    Attendance findGroupByID (@Param("ID") Long ID);

    List<UserForWorkType>  getUserByAttID(@Param("ID") Long ID);




}
