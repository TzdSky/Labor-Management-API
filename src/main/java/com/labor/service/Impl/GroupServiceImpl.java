package com.labor.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.entity.Group;
import com.labor.entity.User;
import com.labor.mapper.GroupMapper;
import com.labor.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public IPage<Group> getCompanyInfoByNameAndPrincipal(Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        String groupName = null;
        String groupPrincipal = null;
        if(map.containsKey("groupName")){
            groupName = map.get("groupName") == null ? null:(String)map.get("groupName");
        }
        if(map.containsKey("groupPrincipal")){
            groupPrincipal = map.get("groupPrincipal") == null ? null:(String)map.get("groupPrincipal");
        }
        Page<Group> page = new Page<>(pageNum, pageSize);
        return groupMapper.getCompanyInfoByNameAndPrincipal(page, groupName, groupPrincipal);
    }

    @Override
    public int insertNewGroup(Group group){
        return groupMapper.insertNewGroup(group);
    };
}
