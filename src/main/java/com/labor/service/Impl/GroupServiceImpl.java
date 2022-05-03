package com.labor.service.Impl;

import com.labor.entity.Group;
import com.labor.mapper.GroupMapper;
import com.labor.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
