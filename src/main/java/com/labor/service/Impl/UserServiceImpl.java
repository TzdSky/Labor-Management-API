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
        String name="";
        Integer phone=0;
        Integer certificateNumber=0;
        String  groupName="";
        String  workType="";
        if(StringUtils.isNotEmpty((String)map.get("name"))){
            name=(String)map.get("name");
        }
        if(StringUtils.isNotEmpty((String)map.get("phone"))){
            phone=(Integer) map.get("phone");
        }
        if(StringUtils.isNotEmpty(map.get("certificateNumber").toString())){
            certificateNumber=(Integer) map.get("certificateNumber");
        }
        if(StringUtils.isNotEmpty((String)map.get("groupName"))){
            groupName=(String) map.get("groupName");
        }
        if(StringUtils.isNotEmpty((String)map.get("workType"))){
            workType=(String)map.get("workType");
        }
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> iPage = userMapper.getUserList(page,name,phone,certificateNumber,groupName,workType);
        return iPage;
    }

    @Override
    public boolean insertNewUser(User user) {
        return userMapper.inertNewUser(user) ==1 ? true:false;
    }

    @Override
    public int getRecordsByCardNumber(int certificate_type, int certificate_number) {
        return userMapper.getRecordsByCardNumber(certificate_type, certificate_number);
    }

    @Override
    public void deleteUserByID(Long id) {
         userMapper.deleteUserByID(id);
    }
}
