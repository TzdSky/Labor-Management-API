package com.labor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.labor.entity.Subcontract;
import com.labor.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/1
 */
public interface UserService {
    IPage<User> getUserList(Map<String, Object> map);

     boolean insertNewUser(User user);

     int getRecordsByCardNumber(int certificate_type, int certificate_number);

     void deleteUserByID(Long id);

    List<Subcontract> getSubcontractList();
}
