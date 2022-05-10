package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labor.entity.Contract;
import com.labor.entity.ProgameCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Mapper
public interface ContractMapper extends BaseMapper<Contract> {
    Integer getCount(@Param("queryParams") Map<String, Object> queryParams);

    List<Contract> getPage(@Param("queryParams") Map<String, Object> queryParams);

    Contract findContractByID(@Param("ID") Long id);

    void deleteContractID(@Param("ID") Long id);

    int getRecordsByName(@Param("contractName")String contractName);

    int insertContract(Contract contract);

    Contract findFileID(@Param("ID") Long id);

    int updateContract(Contract contract);

    List<ProgameCompany> findProgameCompany();
}
