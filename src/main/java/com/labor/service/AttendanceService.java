package com.labor.service;

import com.labor.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/3
 */
public interface AttendanceService {

    /**
     * 班组页面添加或者删除user之后修改 user考勤组状态
     */
    int updateUserGroupID(List<Long> userID,  Long attendanceID);


}
