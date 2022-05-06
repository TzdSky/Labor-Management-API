package com.labor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.Group;
import com.labor.entity.User;
import com.labor.service.GroupService;
import com.labor.utils.DataPage;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@RestController
@RequestMapping("/system/group")
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
     * 根据id删除组别
     * @param  ids
     */
    @GetMapping(value = "/deleteGroupList")
    public ResultModel<String> deleteGroupList(@RequestParam(value = "ids", required = true) List<Long> ids){
        logger.info("deleteGroupList:===>start");
        ResultModel<String> resultModel = new ResultModel<>();
        try {
            if(ids.size()>0){
                for (Long id:ids) {
                    groupService.deleteByID(id);
                }
            }
        } catch (Exception e) {
            logger.info("deleteGroupList:===>error ："+e);
            e.printStackTrace();
            resultModel.setText(ManageConstants.ERROR_205_TEXT);
            resultModel.setCode(ManageConstants.ERROR_205);
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
    public ResultModel<String>  insertNewGroup(Group group){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("insertUser:===>start");
        /**
         * 新增前做判断 根据班组名称 负责人公司去重
         */
        if(group != null) {
            int records = groupService.getRecordsByGroupInfo(group);
            if(records > 0) {
                resultModel.setText(ManageConstants.ERROR_REPEAT_TEXT);
                resultModel.setCode(ManageConstants.ERROR_500);
            } else {
                group.setCreateAt(new Date());
                resultModel.setData(groupService.insertNewGroup(group)?"新增成功":"新增失败");
                resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
                resultModel.setCode(ManageConstants.SUCCESS_200);
            }

        }
        logger.info("insertUser:===>end");
        return resultModel;
    }
}
