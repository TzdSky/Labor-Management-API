package com.labor.service;

import com.labor.entity.Group;

import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/3
 */
public interface GroupService {
    List<Group> getGroupNameByCom(String companyId);
}
