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
    Long insertNewGroup(Group group);

    Page<Group> getGroupList(HttpServletRequest request, Pageable page);

    void deleteGroupByID(List<Long> ids);

    int getRecordsByGroupInfo(Group group);

    /**
     * 班组页面添加或者删除user之后修改 user班组状态
     */
    int updateUserGroupID(List<Long> userID,  Long groupID);

    /**
     * 修改班组内容
     */
    int updateGroup(Group group);

    List<Group> groupList();
}
