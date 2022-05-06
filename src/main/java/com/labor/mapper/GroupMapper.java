package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.entity.Group;
import com.labor.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * 根据公司名、负责人查询班组信息
     */
    List<Group> getPage(@Param("queryParams")Map<String, Object> queryParams);
    /**
     *新增组别
     **/
    int insertNewGroup(Group group);

    Integer getCount(@Param("queryParams") Map<String, Object> queryParams);

    void deleteByID(@Param("id") Long id);


}
