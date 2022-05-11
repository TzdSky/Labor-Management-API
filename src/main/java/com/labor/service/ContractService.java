package com.labor.service;

import com.labor.entity.Contract;
import com.labor.entity.ProgameCompany;
import com.labor.entity.Subcontract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/3
 */
public interface ContractService {
    Page<Contract> getContractList(HttpServletRequest request, Pageable page);

    Contract findContractByID(Long id);

    void deleteContractID(Long id);

    int getRecordsByName(String contractName);

    boolean insertContract(Contract contract, MultipartFile fileOne,MultipartFile fileTwo);

    boolean updateContract(Contract contract, MultipartFile fileOne,MultipartFile fileTwo);

    List<ProgameCompany> findProgameCompany();
}
