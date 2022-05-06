package com.labor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.Group;
import com.labor.entity.User;
import com.labor.service.GroupService;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/getGrouprList",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel<IPage<Group>> getGrouprList(@RequestBody Map<String,Object> map) {
        ResultModel<IPage<Group>> result = new ResultModel<>();
        logger.info("getGrouprList:===>start");
        IPage<Group> page=groupService.getCompanyInfoByNameAndPrincipal(map);
        int totalNum =  (int)page.getTotal();
        int totalPageNum = (int)page.getPages();
        result.setCode(ManageConstants.SUCCESS_200);
        result.setText(ManageConstants.SUCCESS_200_TEXT);
        result.setData(page);
        logger.info("getGrouprList:===>end");
        return result;
    }
}
