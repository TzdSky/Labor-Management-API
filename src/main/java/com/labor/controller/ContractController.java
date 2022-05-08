package com.labor.controller;

import com.labor.entity.Contract;
import com.labor.entity.Subcontract;
import com.labor.service.ContractService;
import com.labor.service.SubcontracService;
import com.labor.utils.DataPage;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@RestController
@RequestMapping("/system/contract")
@CrossOrigin
public class ContractController {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private ContractService contractService;
    @RequestMapping(value = "/getContractList",method = RequestMethod.GET)
    public ResultModel<DataPage> getContractList(HttpServletRequest request, Pageable page) {
        logger.info("getSubcontractList:===>start");
        Page<Contract> contractList= contractService.getContractList(request,page);
        logger.info("getSubcontractList:===>end");
        return new ResultModel<>(new DataPage<>(contractList));
    }


}
