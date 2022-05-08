package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labor.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Tian
 * @date 2022/5/8
 */
@Mapper
public interface AttendanceMapper extends BaseMapper<Group> {
    List<Group> getGroupNameByCom(@Param("companyId") String companyId);

    /**
     * @author Tian
     * @date 2022/5/4
     * 根据公司名、负责人查询班组信息
     */
    List<Group> getPage(@Param("queryParams")Map<String, Object> queryParams);
    /**
     *新增组别
     **/
    Long insertNewGroup(Group group);

    Integer getCount(@Param("queryParams") Map<String, Object> queryParams);

    /**
     * 根据id删除
     * @param ids
     */
    void deleteByID(@Param("ids") List<Long> ids);

    /**
     *  @param userID 用户id
     * @param attendanceID  组别id
     * 班组页面添加或者删除user之后修改 user考勤组状态
     */
    int updateUserAttendanceID(@Param("userIDs")List<Long> userID, @Param("attendanceID")Long attendanceID);



}
