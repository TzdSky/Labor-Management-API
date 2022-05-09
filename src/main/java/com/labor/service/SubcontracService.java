package com.labor.service;

import com.labor.entity.Subcontract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BoCong
 * @date 2022/5/3
 */
public interface SubcontracService {
    Page<Subcontract> subcontractList(HttpServletRequest request, Pageable page);

    int getRecordsByCompanyName(String companyName);

    boolean insertSubcontract(Subcontract subcontract, MultipartFile file);

    void deleteByID(Long id);

    Subcontract findSubcontractByID(Long id);

    boolean updateSubcontract(Subcontract subcontract, MultipartFile file);
}
