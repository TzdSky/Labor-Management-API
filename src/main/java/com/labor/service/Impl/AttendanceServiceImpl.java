package com.labor.service.Impl;

import com.labor.entity.*;
import com.labor.mapper.AttendanceMapper;
import com.labor.mapper.GroupMapper;
import com.labor.service.AttendanceService;
import com.labor.service.GroupService;
import com.labor.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public int deleteAttendanceGroupByID(Long attID){
        return attendanceMapper.deleteAttendanceGroupByID(attID);
    }

    @Override
    public int updateUserGroupID(List<Long> userID, Long attendanceID) {
        return attendanceMapper.updateUserAttendanceID(userID, attendanceID );
    }

    @Override
    public int clearUserAttendanceID(Long attendanceID){
        return attendanceMapper.clearUserAttendanceID(attendanceID);
    }

    @Override
    public int getAttRecordsByAttGroupName(String attGroupName) {
        return attendanceMapper.getAttRecordsByAttGroupName(attGroupName);
    }


    @Override
    public int getAttGroupCount(String attGroupName){
        return attendanceMapper.getAttGroupCount(attGroupName);
    }

    @Override
    public Long insertAttendanceGroup(Attendance attendance){
        return attendanceMapper.insertAttendanceGroup(attendance);
    }

    @Override
    public int updateAttGroup (Attendance attendance){
        return attendanceMapper.updateAttGroup(attendance);
    }


    @Override
    public Page<Attendance> getAttGroupList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String attGroupName = request.getParameter("attGroupName");
        if (StringUtils.isNotEmpty(attGroupName)) {
            queryParams.put("attGroupName", attGroupName);
        }
        Integer total = attendanceMapper.getAttGroupCount(attGroupName);
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        List<Attendance> attGroupList = attendanceMapper.getAttGroupList(queryParams);
        return new PageImpl<>(attGroupList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }


    @Override
    public Page<AttendanceSearch> getAttSearchList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String userName = request.getParameter("userName");
        String groupID = request.getParameter("groupID");
        String principalID = request.getParameter("principalID");
        String companyID = request.getParameter("companyID");
        String workTypeID = request.getParameter("workTypeID");
        String attDate = request.getParameter("attDate");

        if (StringUtils.isNotEmpty(userName)) {
            queryParams.put("userName", userName);
        }
        if (StringUtils.isNotEmpty(groupID)) {
            queryParams.put("groupID", groupID);
        }
        if (StringUtils.isNotEmpty(principalID)) {
            queryParams.put("principalID", principalID);
        }
        if (StringUtils.isNotEmpty(companyID)) {
            queryParams.put("companyID", companyID);
        }
        if (StringUtils.isNotEmpty(workTypeID)) {
            queryParams.put("workTypeID", workTypeID);
        }
        if (StringUtils.isNotEmpty(attDate)) {
            queryParams.put("attDate", attDate);
        }else {
            //默认当月
            Date date = new Date();
            String fomart = "YYYY-MM";
            attDate = DateUtil.dateToString(date, fomart);
            queryParams.put("attDate", DateUtil.dateToString(date, fomart));
        }

        int days= DateUtil.getInputMonthDays(attDate);
        List<Integer> dayList = condition(days);
        Integer total = attendanceMapper.getSearchCount(queryParams);

        queryParams.put("dayList", (dayList));
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        System.err.println("days = "+days);
        System.err.println("attDate = "+queryParams.get("attDate"));
        List<AttendanceSearch> attGroupList = attendanceMapper.getAttSearchList(queryParams);
        if(attGroupList != null && attGroupList.size() > 0) {
            List<Map<String,String>> headers =  setHeader(attDate, days);
            attGroupList.get(0).setHeaders(headers);
        }
        return new PageImpl<>(attGroupList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }



    //将天数 转为天数数组 eg:30 ==> list(1,2,,,,30)
        public static List<Integer> condition (int days){
            List <Integer> dayList = new ArrayList<>();
            for (int i = 1; i <= days; i++) {
                dayList.add(i);
            }
            return dayList;
        }


    //传入年月以及当月天数设置表头
    public static  List<Map<String,String>> setHeader (String yearMonth, Integer days){
        //label放中文 ，value放属性名称 [{}]
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> maps = new LinkedHashMap<>();
        maps.put("label","ID");
        maps.put("value","序号");
        list.add(maps);

        maps = new LinkedHashMap<>();
        maps.put("label","name");
        maps.put("value","名称");
        list.add(maps);

        maps = new LinkedHashMap<>();
        maps.put("label","companyName");
        maps.put("value","所属公司");
        list.add(maps);

        maps = new LinkedHashMap<>();
        maps.put("label","groupName");
        maps.put("value","所在班组");
        list.add(maps);

        String temp ="";
        //小于10的时候前面加个0  eg:2022-11-1 ==>022-11-01
        for (int i = 1; i <= days; i++) {
            maps = new LinkedHashMap<>();
            temp = "-" + i;
            if (i < 10){
                temp = "-0" + i;
            }
            temp = yearMonth + temp;
            maps.put("label","clockStatus"+i);
            maps.put("value",temp);
            list.add(maps);
        }
        return list;
    }


    @Override
    public Attendance findGroupByID(Long ID) {
        Attendance attendance = attendanceMapper.findGroupByID(ID);
        if(attendance != null) {
          List<UserForWorkType> userList =  attendanceMapper.getUserByAttID(ID);
            attendance.setUserList(userList);
        }
        return attendance;
    }




}
