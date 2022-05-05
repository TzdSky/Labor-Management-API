package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.entity.AttachmentLog;
import com.labor.entity.Subcontract;
import com.labor.entity.User;
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
    int getRecordsByCardNumber(int certificate_type, int certificate_number);
    /**
     * @author Tian
     * @date 2022/5/2
     * @param id 主键id
     * 根据id删除
     */
    void deleteUserByID(Long id);

    IPage<User> getUserList(Page<User> page, @Param("name")String name, @Param("phone")Integer phone, @Param("certificateNumber")Integer certificateNumber, @Param("goupID")String goupID,@Param("workType") String workType);

    List<Subcontract> getSubcontractList();

    Integer getCount(@Param("queryParams") Map<String, Object> queryParams);

    List<User> getPage(@Param("queryParams")Map<String, Object> queryParams);
}
