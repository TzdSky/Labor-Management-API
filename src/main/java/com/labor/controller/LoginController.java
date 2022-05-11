package com.labor.controller;

import com.labor.entity.Account;
import com.labor.service.LoginService;
import com.labor.utils.ManageConstants;
import com.labor.utils.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@RestController
@RequestMapping("/system/admin")
@CrossOrigin
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;


    /**
     * 登录
     * @param account 传参对象
     * @return 返回结果
     */
    @PostMapping(value="/login")
    public ResultModel<String>  login(@RequestBody Account account){
        ResultModel<String> resultModel = new ResultModel<>();
        logger.info("Login:===>start");
        if(account != null) {
            int result = loginService.login(account);
            if (result > 0) {
                resultModel.setText(ManageConstants.SUCCESS_200_TEXT);
                resultModel.setCode(ManageConstants.SUCCESS_200);
            } else {
                resultModel.setText(ManageConstants.ERROR_402_TEXT);
                resultModel.setCode(ManageConstants.ERROR_402);
            }
        } else {
            resultModel.setText(ManageConstants.ERROR_403_TEXT);
            resultModel.setCode(ManageConstants.ERROR_403);
        }
        logger.info("login:===>end");
        return resultModel;
    }





}
