package com.labor.service.Impl;

import com.labor.entity.Attendance;
import com.labor.entity.AttendanceSearch;
import com.labor.entity.Group;
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
    public int getAttRecordsByAttGroupName(String attGroupName){
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
        System.err.println("page.total() = "+total);
        System.err.println("page.getPageNumber() = "+page.getPageNumber());
        System.err.println("page.getPageSize() = "+page.getPageSize());
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
            String fomart = "YYYY_MM";
            attDate = DateUtil.dateToString(date, fomart);
            queryParams.put("attDate", DateUtil.dateToString(date, fomart));
        }
        String attDates[] = attDate.split("_");

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, Integer.parseInt(attDates[0]));
        a.set(Calendar.MONTH, Integer.parseInt(attDates[0]) -1);//Calendar 0-11
        int days= a.get(Calendar.DATE);
        Integer total = attendanceMapper.getSearchCount(queryParams);

        queryParams.put("days", (days));
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        System.err.println("page.total() = "+total);
        System.err.println("page.getPageNumber() = "+page.getPageNumber());
        System.err.println("page.getPageSize() = "+page.getPageSize());
        List<AttendanceSearch> attGroupList = attendanceMapper.getAttSearchList(queryParams);
        return new PageImpl<>(attGroupList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }
}
