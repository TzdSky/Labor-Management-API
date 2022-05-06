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
public interface GroupService {
    List<Group> getGroupNameByCom(String companyId);

    /**
     *新增组别
     **/
    boolean insertNewGroup(Group group);

    Page<Group> getGroupList(HttpServletRequest request, Pageable page);

    void deleteByID(Long id);

    int getRecordsByGroupInfo(Group group);
}
