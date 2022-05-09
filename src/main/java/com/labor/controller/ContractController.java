package com.labor.controller;

import com.labor.entity.Contract;
import com.labor.entity.Subcontract;
import com.labor.entity.User;
import com.labor.service.ContractService;
import com.labor.service.SubcontracService;
import com.labor.utils.DataPage;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    /**
     * 根据id查看合同
     * @param  ID
     */
    @GetMapping(value = "/findContractByID")
    public ResultModel<Contract> findContractByID(@RequestParam(value = "ID", required = true) Long ID){
        logger.info("findContractByID:===>start");
        ResultModel<Contract> resultModel = new ResultModel<>();
        Contract contract=contractService.findContractByID(ID);
        resultModel.setData(contract);
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("findContractByID:===>end");
        return resultModel;
    }



    /**
     * 根据id删除合同
     * @param ids
     */
    @GetMapping(value = "/deleteContract")
    public ResultModel<String> deleteContract(@RequestParam(value = "ids", required = true) List<Long> ids){
        logger.info("deleteContract:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            if(ids.size()>0){
                for (Long id:ids) {
                    contractService.deleteContractID(id);
                }
            }
        } catch (Exception e) {
            logger.info("deleteContract:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_205_TEXT);
            resultModel.setCode(ManageConstants.ERROR_205);
            return resultModel;
        }
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("deleteContract:===>end");
        return resultModel;
    }

}
