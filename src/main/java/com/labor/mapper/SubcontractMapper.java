package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labor.entity.Subcontract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Mapper
public interface SubcontractMapper extends BaseMapper<Subcontract> {
    Integer getCount(@Param("queryParams") Map<String, Object> queryParams);

    List<Subcontract> getPage(@Param("queryParams")Map<String, Object> queryParams);

    int getRecordsByCompanyName(@Param("companyName")String companyName);

    void deleteByID(@Param("id") Long id);

    int insertSubcontract(Subcontract subcontract);

    Subcontract findSubcontractByID(@Param("id")Long id);

    int updateSubcontract(Subcontract subcontract);
}
