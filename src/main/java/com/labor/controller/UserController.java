package com.labor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.User;
import com.labor.service.UserService;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import org.springframework.http.MediaType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


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
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel<IPage<User>> getUserList(@RequestBody Map<String,Object> map) {
        ResultModel<IPage<User>> result = new ResultModel<>();
          IPage<User> page=userService.getUserList(map);
          result.setCode(ManageConstants.SUCCESS_200);
          result.setText(ManageConstants.SUCCESS_200_TEXT);
          result.setData(page);
          return result;
    }



    /**
     * 新增
     * @param user 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/insertUser")
    public ResultModel<String>  insertUser(@RequestBody User user){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("insertUser:===>start");
         user.setCreateAt(new Date());
         resultModel.setData(userService.insertNewUser(user)?"新增成功":"新增失败");
         resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
         resultModel.setCode(ManageConstants.SUCCESS_200);
        logger.info("insertUser:===>end");
        return resultModel;
    }
}
