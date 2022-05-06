package com.labor.controller;

import com.labor.entity.Subcontract;
import com.labor.entity.User;
import com.labor.service.SubcontracService;
import com.labor.utils.DataPage;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@RestController
@RequestMapping("/system/subcontract")
public class SubcontractController {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private SubcontracService subcontracService;
    @RequestMapping(value = "/getSubcontractList",method = RequestMethod.GET)
    public ResultModel<DataPage> getUserList(HttpServletRequest request, Pageable page) {
        logger.info("findUser:===>start");
        Page<Subcontract> subcontractList= subcontracService.subcontractList(request,page);
        logger.info("findUser:===>end");
        return new ResultModel<>(new DataPage<>(subcontractList));
    }
}
