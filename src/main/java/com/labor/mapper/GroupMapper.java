package com.labor.mapper;

import com.labor.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Mapper
public interface GroupMapper {
    List<Group> getGroupNameByCom(@Param("companyId") String companyId);
}
