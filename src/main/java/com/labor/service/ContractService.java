package com.labor.service;

import com.labor.entity.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BoCong
 * @date 2022/5/3
 */
public interface ContractService {
    Page<Contract> getContractList(HttpServletRequest request, Pageable page);

    Contract findContractByID(Long id);

    void deleteContractID(Long id);
}
