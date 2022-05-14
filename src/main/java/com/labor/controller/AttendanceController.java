package com.labor.controller;

import com.labor.entity.Attendance;
import com.labor.entity.AttendanceSearch;
import com.labor.entity.Group;
import com.labor.service.AttendanceService;
import com.labor.service.GroupService;
import com.labor.utils.DataPage;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Tian
 * @date 2022/5/8
 */
@RestController
@RequestMapping("/system/attendance")
@CrossOrigin
public class AttendanceController {
    private Logger logger = Logger.getLogger(AttendanceController.class);
    @Autowired
    private GroupService groupService;


    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(value = "/getAttGroupList",method = RequestMethod.GET)
    public ResultModel<DataPage> getAttGroupList(HttpServletRequest request, Pageable page) {
        logger.info("getAttGroupList:===>start");
        Page<Attendance> groupList= attendanceService.getAttGroupList(request,page);
        logger.info("getAttGroupList:===>end");
        return new ResultModel<>(new DataPage<>(groupList));
    }

    /**
     * 根据id删除考勤组
     * @param  id
     */
    @GetMapping(value = "/deleteAttendanceGroupByID")
    public ResultModel<String> deleteAttendanceGroupByID(@RequestParam(value = "id", required = true) Long id){
        logger.info("deleteAttendanceGroupByID:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            attendanceService.deleteAttendanceGroupByID(id);
            attendanceService.clearUserAttendanceID(id);
        } catch (Exception e) {
            logger.info("deleteAttendanceGroupByID:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_209_TEXT);
            resultModel.setCode(ManageConstants.ERROR_209);
            return resultModel;
        }
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("deleteAttendanceGroupByID:===>end");
        return resultModel;
    }


    /**
     * 新增
     * @param attendance 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/insertAttendanceGroup")
    public ResultModel<String>  insertAttendanceGroup(@RequestBody Attendance attendance){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("insertAttendanceGroup:===>start");
        if(attendance != null) {
            int records = 0 ;
            if(StringUtils.isNotEmpty(attendance.getAttGroupName())) {
                //考勤组名称不为空的时候查询数据库是不是相同的记录 去重
                records = attendanceService.getAttRecordsByAttGroupName(attendance.getAttGroupName());
            }
            if(records > 0) {
                resultModel.setText(ManageConstants.ERROR_REPEAT_TEXT);
                resultModel.setCode(ManageConstants.ERROR_500);
            } else {
                Long group1 = attendanceService.insertAttendanceGroup(attendance);
                if(attendance.getUserInAttGroup() != null && attendance.getUserInAttGroup().size() > 0){
                    //新增完，根据返回的id批量修改user表的user考勤组id
                    attendanceService.updateUserGroupID(attendance.getUserInAttGroup(),attendance.getID());
                }
                resultModel.setData(group1 != null?"新增成功":"新增失败");
                resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
                resultModel.setCode(ManageConstants.SUCCESS_200);
            }

        }
        logger.info("insertAttendanceGroup:===>end");
        return resultModel;
    }


    /**
     * 修改考勤班组
     * @param attendance 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/updateAttendanceGroup")
    public ResultModel<String> updateAttendanceGroup(@RequestBody Attendance attendance){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("updateAttendanceGroup:===>start");
        int records = 0 ;
        if(StringUtils.isNotEmpty(attendance.getAttGroupName())) {
            //修改后的考勤组名称都不为空的时候查询数据库是不是相同的记录 去重
            records = attendanceService.getAttRecordsByAttGroupName(attendance.getAttGroupName());
        }
        if (records > 0){
            resultModel.setText(ManageConstants.ERROR_REPEAT_ATT_TEXT);
            resultModel.setCode(ManageConstants.ERROR_500);
        } else {
            int queryStatus = attendanceService.updateAttGroup(attendance);
            if(queryStatus == 1 && attendance.getUserInAttGroup() != null && attendance.getUserInAttGroup().size() > 0){
                //修改成功了考勤组，就顺势修改user状态
                attendanceService.updateUserGroupID(attendance.getUserInAttGroup(),attendance.getID());
            }
            resultModel.setData(queryStatus == 1?"修改成功":"修改失败");
            resultModel.setText(queryStatus == 1?ManageConstants.SUCCESS_200_TEXT:"失败");
            resultModel.setCode(ManageConstants.SUCCESS_200);
        }
        logger.info("updateAttendanceGroup");
        return resultModel;
    }

    @RequestMapping(value = "/getAttSearchList",method = RequestMethod.GET)
    public ResultModel<DataPage> getAttSearchList(HttpServletRequest request, Pageable page) {
        logger.info("getAttSearchList:===>start");
        Page<AttendanceSearch> attGroupList= attendanceService.getAttSearchList(request,page);
        logger.info("getAttSearchList:===>end");
        return new ResultModel<>(new DataPage<>(attGroupList));
    }

    /**
     * 根据id查看考勤组
     * @param  ID
     */
    @GetMapping(value = "/findAttendByID")
    public ResultModel<Attendance> findGroupByID(@RequestParam(value = "ID", required = true) Long ID){
        logger.info("findAttendByID:===>start");
        ResultModel<Attendance> resultModel = new ResultModel<>();
        Attendance attendance=attendanceService.findGroupByID(ID);
        resultModel.setData(attendance);
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("findAttendByID:===>end");
        return resultModel;
    }

}
