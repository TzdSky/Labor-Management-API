package com.labor.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.User;
import com.labor.service.UserService;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/1
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
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
}
