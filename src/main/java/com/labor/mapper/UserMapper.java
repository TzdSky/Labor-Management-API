package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/1
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


    int inertNewUser(User user);

    /**
     * @author Tian
     * @date 2022/5/2
     * @param certificate_type 证件类型
     * @param certificate_number 证件号码
     * 根据证件类型、号码 查重
     */
    int getRecordsByCardNumber(int certificate_type, String certificate_number);
    /**
     * @author Tian
     * @date 2022/5/2
     * @param id 主键id
     * 根据id删除
     */
    void deleteUserByID(Long id);


    /**
     * 获取所有劳务公司 用于下拉框
     */
    List<Subcontract> getSubcontractList();

    Integer getCount(@Param("queryParams") Map<String, Object> queryParams);

    List<User> getPage(@Param("queryParams")Map<String, Object> queryParams);

    /**
     * 获取所有班长信息(用于下拉框)
     */
    List<User> getMonitorList();

    /**
     * 获取没有被分配的靓仔
     **/
    List<User> getUserUnassigned();


    /**
     * 修改用户
     */
    int updateUser(User user);

    /**
     * 查看用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    List<WorkType> getWorkType();
}
