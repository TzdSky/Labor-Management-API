package com.labor.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.entity.User;
import com.labor.mapper.UserMapper;
import com.labor.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/1
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public IPage<User> getUserList(Map<String, Object> map) {

        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = userMapper.getUserList(page);
        return iPage;
    }

    @Override
    public boolean insertNewUser(User user) {
        return userMapper.inertNewUser(user) ==1 ? true:false;
    }
}
