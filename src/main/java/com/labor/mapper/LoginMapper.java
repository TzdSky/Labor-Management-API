package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.labor.entity.Account;
import com.labor.entity.Contract;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Tian
 * @date 2022/5/11
 */
@Mapper
public interface LoginMapper extends BaseMapper<Account> {
    /**
     * 登录验证
     * @param account
     * @return
     */
    int login(Account account);
}
