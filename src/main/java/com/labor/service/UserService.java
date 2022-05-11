package com.labor.service;

import com.labor.entity.Project;
import com.labor.entity.Subcontract;
import com.labor.entity.User;
import com.labor.entity.WorkType;
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

     boolean insertNewUser(User user, MultipartFile contractFile,MultipartFile headImg);

     int getRecordsByCardNumber(int certificate_type, String certificateNumber);

     void deleteUserByID(Long id);

    List<Subcontract> getSubcontractList();

    /**
     * 获取所有负责人
     **/
    List<User> getMonitorList();

    /*
    * 获取没有被分配的靓仔
    * */
    List<User> getUserUnassigned();

    /**
     * 修改用户信息
     * @param user
     * @param contractFile
     * @return
     */
    boolean updateUser(User user, MultipartFile contractFile, MultipartFile headImg);

    User findUserById(Long id);

    List<WorkType> getWorkType();

    List<Project> getProjectList();
}
