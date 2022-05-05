package com.labor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.Group;
import com.labor.entity.Subcontract;
import com.labor.entity.User;
import com.labor.service.GroupService;
import com.labor.service.UserService;
import com.labor.utils.DataPage;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @author BoCong
 * @date 2022/5/1
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public ResultModel<DataPage> getUserList(HttpServletRequest request, Pageable page) {
        logger.info("findUser:===>start");
        Page<User> userList= userService.getUserList(request,page);
        logger.info("findUser:===>end");
        return new ResultModel<>(new DataPage<>(userList));
    }

    /**
     *  获取公司
     * @return
     */
    @GetMapping("/getCompany")
    public ResultModel<List<Subcontract>> getCompany() {
        ResultModel<List<Subcontract>> result = new ResultModel<>();
        List<Subcontract> subcontractList=userService.getSubcontractList();
        result.setCode(ManageConstants.SUCCESS_200);
        result.setText(ManageConstants.SUCCESS_200_TEXT);
        result.setData(subcontractList);
        return result;
    }

    /**
     *  获取组
     * @return
     */
    @GetMapping("/getGroupName")
    public ResultModel<List<Group>> getGroupName(String companyId) {
        ResultModel<List<Group>> result = new ResultModel<>();
        List<Group> groupList=groupService.getGroupNameByCom(companyId);
        result.setCode(ManageConstants.SUCCESS_200);
        result.setText(ManageConstants.SUCCESS_200_TEXT);
        result.setData(groupList);
        return result;
    }



    /**
     * 新增
     * @param user 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/insertUser")
    public ResultModel<String>  insertUser(User user,@RequestPart(value = "file") MultipartFile file){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("insertUser:===>start");
        /**
         * 新增前做判断
         */
        if(user != null) {
            int records = userService.getRecordsByCardNumber(user.getCertificateType(), user.getCertificateNumber());
            if(records > 0) {
                resultModel.setText(ManageConstants.ERROR_REPEAT_TEXT);
                resultModel.setCode(ManageConstants.ERROR_500);
            } else {
                user.setCreateAt(new Date());
                resultModel.setData(userService.insertNewUser(user,file)?"新增成功":"新增失败");
                resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
                resultModel.setCode(ManageConstants.SUCCESS_200);
            }

        }
        logger.info("insertUser:===>end");
        return resultModel;
    }

    /**
     * 根据id删除用户
     * @param id
     */
    @GetMapping(value = "/deleteUser")
    public ResultModel<String> deleteUser(@Param("id") long id){
        logger.info("deleteUser:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            userService.deleteUserByID(id);
        } catch (Exception e) {
            logger.info("deleteUser:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_205_TEXT);
            resultModel.setCode(ManageConstants.ERROR_205);
            return resultModel;
        }
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("deleteUser:===>end");
        return resultModel;
    }



}
