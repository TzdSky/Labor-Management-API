package com.labor.service.Impl;

import com.labor.entity.Subcontract;
import com.labor.entity.User;
import com.labor.mapper.SubcontractMapper;
import com.labor.service.SubcontracService;
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
public class SubcontracServiceImpl implements SubcontracService {
    @Autowired
    private SubcontractMapper subcontractMapper;
    @Override
    public Page<Subcontract> subcontractList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String companyName = request.getParameter("companyName");
        String subcontractType = request.getParameter("subcontractType");

        if (StringUtils.isNotEmpty(companyName)) {
            queryParams.put("companyName", companyName);
        }
        if (StringUtils.isNotEmpty(subcontractType)) {
            queryParams.put("subcontractType", Integer.valueOf(subcontractType));
        }

        Integer total = subcontractMapper.getCount(queryParams);
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        List<Subcontract> subcontractList = subcontractMapper.getPage(queryParams);
        return new PageImpl<>(subcontractList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }
}
