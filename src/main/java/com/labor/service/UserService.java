package com.labor.service;

import com.labor.entity.Subcontract;
import com.labor.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author BoCong
 * @date 2022/5/1
 */
public interface UserService {
     Page<User> getUserList(HttpServletRequest request, Pageable page);

     boolean insertNewUser(User user, MultipartFile file);

     int getRecordsByCardNumber(int certificate_type, int certificate_number);

     void deleteUserByID(Long id);

    List<Subcontract> getSubcontractList();

    List<User> getMonitorList();

    /*
    * 获取没有被分配的靓仔
    * */
    List<User> getUserUnassigned();

    /**
     * 班组页面添加或者删除user之后修改 user班组状态
     */
    int updateUserGroupID(Long userID, Long groupID);

}
