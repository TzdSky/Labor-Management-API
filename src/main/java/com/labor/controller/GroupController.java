package com.labor.controller;

import com.labor.entity.Group;
import com.labor.service.GroupService;
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
@RequestMapping("/system/group")
@CrossOrigin
public class GroupController {
    private Logger logger = Logger.getLogger(GroupController.class);
    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/getGrouprList",method = RequestMethod.GET)
    public ResultModel<DataPage> getGrouprList(HttpServletRequest request, Pageable page) {
        logger.info("getGrouprList:===>start");
        Page<Group> groupList= groupService.getGroupList(request,page);
        logger.info("getGrouprList:===>end");
        return new ResultModel<>(new DataPage<>(groupList));
    }



    /**
     * 根据id 批量删除组别
     * @param  ids
     */
    @GetMapping(value = "/deleteGroupList")
    public ResultModel<String> deleteGroupList(@RequestParam(value = "ids", required = true) List<Long> ids){
        logger.info("deleteGroupList:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            if(ids.size()>0){
                    groupService.deleteGroupByID(ids);
            }
        } catch (Exception e) {
            logger.info("deleteGroupList:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_209_TEXT);
            resultModel.setCode(ManageConstants.ERROR_209);
            return resultModel;
        }
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("deleteGroupList:===>end");
        return resultModel;
    }


    /**
     * 新增
     * @param group 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/insertNewGroup")
    public ResultModel<String>  insertNewGroup(@RequestBody Group group){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("insertNewGroup:===>start");
        if(group != null) {
            boolean hasRecords = false;
            int records = 0 ;
            if(group.getGroupName() != null && group.getPrincipalId()!= null && group.getCompanyId()!= null) {
                //三条记录都不为空的时候查询数据库是不是相同的记录 去重
                 records = groupService.getRecordsByGroupInfo(group);
                hasRecords =  records > 0 ? true : false;
            }

            if(hasRecords) {
                resultModel.setText(ManageConstants.ERROR_REPEAT_TEXT);
                resultModel.setCode(ManageConstants.ERROR_500);
            } else {
                Long group1 = groupService.insertNewGroup(group);
                if(group.getUserInGroup() != null && group.getUserInGroup().size() > 0){
                    //新增完，根据返回的id批量修改user表的 user班组id
                    groupService.updateUserGroupID(group.getUserInGroup(),group.getID());
                }
                resultModel.setData(group1 != null?"新增成功":"新增失败");
                resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
                resultModel.setCode(ManageConstants.SUCCESS_200);
            }

        }
        logger.info("insertNewGroup:===>end");
        return resultModel;
    }



    /**
     * 根据id 批量修改user的班组状态
     * @param  ids 要修改的user id集合
     * @param  status 0删除(修改user的班组goup_id为null)，1 添加（修改user的班组goup_id为当前组别的id）
     */
    @GetMapping(value = "/addOrRemoveUserInGroup")
    public ResultModel<String> addOrRemoveUserInGroup(@RequestParam(value = "ids", required = true) List<Long> ids,
                                               @RequestParam(value = "status", required = true) Long status,
                                               @RequestParam(value = "groupID", required = true)Long groupID){
        logger.info("addOrRemoveUserInGroup:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            if(ids != null && status != null && groupID != null){
                if(ids.size() > 0){

                    if(status == 0){
                        groupID = null; //如果是删除user就设置为空
                    }
                    groupService.updateUserGroupID(ids,groupID);
                }
            } else {
                resultModel.setText(ManageConstants.ERROR_405_TEXT);
                resultModel.setCode(ManageConstants.ERROR_405);
                return resultModel;
            }
        } catch (Exception e) {
            logger.info("addOrRemoveUserInGroup:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_207_TEXT);
            resultModel.setCode(ManageConstants.ERROR_207);
            return resultModel;
        }
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("addOrRemoveUserInGroup:===>end");
        return resultModel;
    }


    /**
     * 修改班组
     * @param group 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/updateGroup")
    public ResultModel<String> updateGroup(@RequestBody Group group){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("updateGroup:===>start");
        int queryStatus = groupService.updateGroup(group);
        resultModel.setData(queryStatus == 1?"修改成功":"修改失败");
        resultModel.setText(queryStatus == 1?ManageConstants.SUCCESS_200_TEXT:"失败");
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("updateGroup:===>end");
        return resultModel;
    }

    /**
     * 根据id查看合同
     * @param  ID
     */
    @GetMapping(value = "/findGroupByID")
    public ResultModel<Group> findGroupByID(@RequestParam(value = "ID", required = true) Long ID){
        logger.info("findContractByID:===>start");
        ResultModel<Group> resultModel = new ResultModel<>();
        Group group=groupService.findGroupByID(ID);
        resultModel.setData(group);
        resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
        resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("findContractByID:===>end");
        return resultModel;
    }

}
