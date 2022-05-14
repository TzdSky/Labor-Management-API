package com.labor.service.Impl;

import com.labor.entity.Group;
import com.labor.entity.User;
import com.labor.entity.UserForWorkType;
import com.labor.mapper.GroupMapper;
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
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public List<Group> getGroupNameByCom(String companyId) {
        return groupMapper.getGroupNameByCom(companyId);
    }

    @Override
    public Page<Group> getGroupList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String companyID = request.getParameter("companyID");
        String groupName = request.getParameter("groupName");
        String groupPrincipal = request.getParameter("groupPrincipal");
        if (StringUtils.isNotEmpty(companyID)) {
            queryParams.put("companyID", companyID);
        }
        if (StringUtils.isNotEmpty(groupName)) {
            queryParams.put("groupName", groupName);
        }
        if (StringUtils.isNotEmpty(groupPrincipal)) {
            queryParams.put("groupPrincipal", groupPrincipal);
        }

        Integer total = groupMapper.getCount(queryParams);
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        List<Group> groupList = groupMapper.getPage(queryParams);
        return new PageImpl<>(groupList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }




    @Override
    public Long insertNewGroup(Group group){
        return groupMapper.insertNewGroup(group);
    };


    @Override
    public void deleteGroupByID(List<Long> ids) {
        groupMapper.deleteByID(ids);
    }

    @Override
    public int getRecordsByGroupInfo(Group group){
        Map<String, Object> queryParams = new HashMap<>();
         queryParams.put("companyID", group.getCompanyId());
        if (StringUtils.isNotEmpty(group.getGroupName())) {
            queryParams.put("groupName", group.getGroupName());
        }
        queryParams.put("principalId", group.getPrincipalId());
        return groupMapper.getCount(queryParams);
    };

    @Override
    public int updateUserGroupID(List<Long> userID, Long groupID) {
        return groupMapper.updateUserGroupID(userID, groupID );
    }

    @Override
    public int updateGroup(Group group) {
        return groupMapper.updateGroup(group);
    }

    @Override
    public List<Group> groupList() {
        return groupMapper.groupList();
    }

    @Override
    public Group findGroupByID(Long ID) {
        Group group = groupMapper.findGroupByID(ID);
        if(group != null) {
            List<UserForWorkType> userList = groupMapper.getGroupUsers(ID);
            group.setUserList(userList);
        }

        return group;
    }

    @Override
    public List<UserForWorkType> getUserByCondition(String condition) {
        return groupMapper.getUserByCondition(condition);
    }


}
