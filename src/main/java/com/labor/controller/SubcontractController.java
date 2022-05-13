package com.labor.controller;

import com.labor.entity.Subcontract;
import com.labor.entity.User;
import com.labor.service.SubcontracService;
import com.labor.utils.DataPage;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@RestController
@RequestMapping("/system/subcontract")
@CrossOrigin
public class SubcontractController {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private SubcontracService subcontracService;
    @RequestMapping(value = "/getSubcontractList",method = RequestMethod.GET)
    public ResultModel<DataPage> getUserList(HttpServletRequest request, Pageable page) {
        logger.info("getSubcontractList:===>start");
        Page<Subcontract> subcontractList= subcontracService.subcontractList(request,page);
        logger.info("getSubcontractList:===>end");
        return new ResultModel<>(new DataPage<>(subcontractList));
    }

    /**
     * 根据id查看公司
     * @param  ID
     */
    @GetMapping(value = "/findSubcontractByID")
    public ResultModel<Subcontract> findSubcontractByID(@RequestParam(value = "ID", required = true) Long ID){
        logger.info("findSubcontractByID:===>start");
        ResultModel<Subcontract> resultModel = new ResultModel<>();
        Subcontract subcontract=subcontracService.findSubcontractByID(ID);
        resultModel.setData(subcontract);
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("findSubcontractByID:===>end");
        return resultModel;
    }

    /**
     * 新增
     * @param subcontract 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/insertSubcontract")
    public ResultModel<String>  insertSubcontract(Subcontract subcontract,@RequestPart(value = "file") MultipartFile file){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("insertSubcontract:===>start");
        /**
         * 新增前做判断
         */
        if(subcontract != null) {
            int records = subcontracService.getRecordsByCompanyName(subcontract.getCompanyName());
            if(records > 0) {
                resultModel.setText(ManageConstants.ERROR_REPEAT_Company_TEXT);
                resultModel.setCode(ManageConstants.ERROR_500);
            } else {
                resultModel.setData(subcontracService.insertSubcontract(subcontract,file)?"新增成功":"新增失败");
                resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
                resultModel.setCode(ManageConstants.SUCCESS_200);
            }

        }
        logger.info("insertSubcontract:===>end");
        return resultModel;
    }

    /**
     * 根据id删除公司
     * @param  ids
     */
    @GetMapping(value = "/deleteSubcontract")
    public ResultModel<String> deleteSubcontract(@RequestParam(value = "ids", required = true) List<Long> ids){
        logger.info("deleteSubcontract:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            if(ids.size()>0){
                for (Long id:ids) {
                    subcontracService.deleteByID(id);
                }
            }
        } catch (Exception e) {
            logger.info("deleteSubcontract:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_205_TEXT);
            resultModel.setCode(ManageConstants.ERROR_205);
            return resultModel;
        }
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("deleteSubcontract:===>end");
        return resultModel;
    }
    /**
     * 根据id删除公司
     * @param  ID
     */
    @GetMapping(value = "/deleteSubcontractOne")
    public ResultModel<String> deleteSubcontractOne(@RequestParam(value = "ID", required = true) Long ID){
        logger.info("deleteSubcontractOne:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            subcontracService.deleteByID(ID);
        } catch (Exception e) {
            logger.info("deleteSubcontractOne:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_205_TEXT);
            resultModel.setCode(ManageConstants.ERROR_205);
            return resultModel;
        }
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("deleteSubcontractOne:===>end");
        return resultModel;
    }

    /**
     * 修改分包商
     * @param subcontract 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/updateSubcontract")
    public ResultModel<String>  updateSubcontract(Subcontract subcontract, @RequestPart(value = "file",required = false) MultipartFile file){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("updateSubcontract:===>start");
        if(subcontract != null) {
            resultModel.setData(subcontracService.updateSubcontract(subcontract, file) ? "修改成功" : "修改失败");
            resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
            resultModel.setCode(ManageConstants.SUCCESS_200);
        }else {
            resultModel.setData("修改失败");
            resultModel.setText(ManageConstants.ERROR_207_TEXT);
            resultModel.setCode(ManageConstants.ERROR_207);
        }
        logger.info("updateSubcontract:===>end");
        return resultModel;
    }
}
