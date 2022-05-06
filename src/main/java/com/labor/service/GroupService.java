package com.labor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/3
 */
public interface GroupService {
    List<Group> getGroupNameByCom(String companyId);

    IPage<Group> getCompanyInfoByNameAndPrincipal(Map<String, Object> map);

    /**
     *新增组别
     **/
    int insertNewGroup(Group group);
}
