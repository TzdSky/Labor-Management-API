package com.labor.service.Impl;

import com.labor.entity.Group;
import com.labor.mapper.AttendanceMapper;
import com.labor.mapper.GroupMapper;
import com.labor.service.AttendanceService;
import com.labor.service.GroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;


    @Override
    public int updateUserGroupID(List<Long> userID, Long groupID) {
        return attendanceMapper.updateUserAttendanceID(userID, groupID );
    }


}
