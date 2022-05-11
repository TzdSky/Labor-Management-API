package com.labor.service.Impl;

import com.labor.entity.Account;
import com.labor.mapper.LoginMapper;
import com.labor.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tian
 * @date 2022/5/11
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public int login(Account account) {
        return loginMapper.login(account);
    }

}
