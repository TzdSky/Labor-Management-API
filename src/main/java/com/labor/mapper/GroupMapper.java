package com.labor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.entity.Group;
import com.labor.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Mapper
public interface GroupMapper extends BaseMapper<Group> {
    List<Group> getGroupNameByCom(@Param("companyId") String companyId);

    /**
     * @author Tian
     * @date 2022/5/4
     * 根据公司名、负责人查询班组信息
     */
    List<Group> getPage(@Param("queryParams")Map<String, Object> queryParams);
    /**
     *新增组别
     **/
    Long insertNewGroup(Group group);

    Integer getCount(@Param("queryParams") Map<String, Object> queryParams);

    /**
     * 根据id删除
     * @param ids
     */
    void deleteByID(@Param("ids") List<Long> ids);

    /**
     *  @param userID 用户id
     * @param groupID  组别id
     * 班组页面添加或者删除user之后修改 user班组状态
     */
    int updateUserGroupID(@Param("userIDs")List<Long> userID, @Param("groupID")Long groupID);


    /**
     * 修改班组内容
     */
    int updateGroup(Group group);

    List<Group> groupList();


    Group findGroupByID(@Param("ID")Long ID);
}
