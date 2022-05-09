package com.labor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.*;
import com.labor.service.GroupService;
import com.labor.service.UserService;
import com.labor.utils.DataPage;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @author BoCong
 * @date 2022/5/1
 */
@RestController
@RequestMapping("/system/user")
@CrossOrigin
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
     *  获取工种
     * @return
     */
    @GetMapping("/getWorkType")
    public ResultModel<List<WorkType>> getWorkType() {
        ResultModel<List<WorkType>> result = new ResultModel<>();
        List<WorkType> workTypeList=userService.getWorkType();
        result.setCode(ManageConstants.SUCCESS_200);
        result.setText(ManageConstants.SUCCESS_200_TEXT);
        result.setData(workTypeList);
        return result;
    }

    /**
     *  获取工种
     * @return
     */
    @GetMapping("/getProject")
    public ResultModel<List<Project>> getProject() {
        ResultModel<List<Project>> result = new ResultModel<>();
        List<Project> projectList=userService.getProjectList();
        result.setCode(ManageConstants.SUCCESS_200);
        result.setText(ManageConstants.SUCCESS_200_TEXT);
        result.setData(projectList);
        return result;
    }

    /**
     *  获取组
     * @return
     */
    @GetMapping("/getGroup")
    public ResultModel<List<Group>> getGroup() {
        ResultModel<List<Group>> result = new ResultModel<>();
        List<Group> groupList=groupService.groupList();
        result.setCode(ManageConstants.SUCCESS_200);
        result.setText(ManageConstants.SUCCESS_200_TEXT);
        result.setData(groupList);
        return result;
    }

    /**
     *  通过公司id获取组
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
     * 根据id查看用户
     * @param ID
     */
    @GetMapping(value = "/findUserById")
    public ResultModel<User> findUserById(@RequestParam(value = "ID", required = true) Long ID){
        logger.info("findUser:===>start");
        ResultModel<User> resultModel = new ResultModel<>();
        User user=userService.findUserById(ID);
        resultModel.setData(user);
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("findUser:===>end");
        return resultModel;
    }

    /**
     * 根据id删除用户
     * @param ids
     */
    @GetMapping(value = "/deleteUser")
    public ResultModel<String> deleteUser(@RequestParam(value = "ids", required = true) List<Long> ids){
        logger.info("deleteUser:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            if(ids.size()>0){
                for (Long id:ids) {
                    userService.deleteUserByID(id);
                }
            }
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

    /**
     * 修改用户
     * @param user 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/updateUser")
    public ResultModel<String>  updateUser(User user, @RequestPart(value = "file") MultipartFile file){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("updateUser:===>start");
        if(user != null) {
            user.setCreateAt(new Date());
            resultModel.setData(userService.updateUser(user, file) ? "修改成功" : "修改失败");
            resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
            resultModel.setCode(ManageConstants.SUCCESS_200);
        }else {
            resultModel.setData("修改失败");
            resultModel.setText(ManageConstants.ERROR_207_TEXT);
            resultModel.setCode(ManageConstants.ERROR_207);
        }
        logger.info("updateGroup:===>end");
        return resultModel;
    }



}
