package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.entity.Group;
import com.labor.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Mapper
public interface GroupMapper extends BaseMapper<Group> {
    List<Group> getGroupNameByCom(@Param("companyId") String companyId);

    /**
     * @author Tian
     * @date 2022/5/4
     * @param groupName 公司名称
     * @param groupPrincipal 负责人
     * 根据公司名、负责人查询班组信息
     */
    IPage<Group> getCompanyInfoByNameAndPrincipal(Page<Group> page, @Param("groupName") String groupName, @Param("groupPrincipal") String groupPrincipal);
}
