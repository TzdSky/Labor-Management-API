package com.labor.service.Impl;

import com.labor.entity.Contract;
import com.labor.entity.Subcontract;
import com.labor.mapper.ContractMapper;
import com.labor.service.ContractService;
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
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper contractMapper;
    @Override
    public Page<Contract> getContractList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String contractName = request.getParameter("contractName");
        String contractType = request.getParameter("contractType");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (StringUtils.isNotEmpty(contractName)) {
            queryParams.put("contractName", contractName);
        }
        if (StringUtils.isNotEmpty(contractType)) {
            queryParams.put("contractType", Integer.valueOf(contractType));
        }
        if (StringUtils.isNotEmpty(beginTime)) {
            queryParams.put("beginTime", beginTime);
        }
        if (StringUtils.isNotEmpty(endTime)) {
            queryParams.put("endTime", endTime);
        }
        Integer total = contractMapper.getCount(queryParams);
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        List<Contract> contractMapperList = contractMapper.getPage(queryParams);
        return new PageImpl<>(contractMapperList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }


}
