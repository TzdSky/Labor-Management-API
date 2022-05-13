package com.labor.service.Impl;


import com.labor.controller.UserController;
import com.labor.entity.*;
import com.labor.mapper.AttachmentLogMapper;
import com.labor.mapper.UserMapper;
import com.labor.service.UserService;
import com.labor.utils.FileUtil;
import com.labor.utils.GenerateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author BoCong
 * @date 2022/5/1
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserMapper userMapper;
    @Value("${attachRootPath}")
    private String attachRootPath;
    @Autowired
    private AttachmentLogMapper attachmentLogMapper;

    @Override
    public Page<User> getUserList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String certificateNumber = request.getParameter("certificateNumber");
        String goupID = request.getParameter("goupID");
        String workTypeId = request.getParameter("workTypeId");
        if (StringUtils.isNotEmpty(name)) {
            queryParams.put("name", name);
        }
        if (StringUtils.isNotEmpty(phone)) {
            queryParams.put("phone", Integer.valueOf(phone));
        }
        if (StringUtils.isNotEmpty(certificateNumber)) {
            queryParams.put("certificateNumber", Integer.valueOf(certificateNumber));
        }
        if (StringUtils.isNotEmpty(goupID)) {
            queryParams.put("goupID", goupID);
        }
        if (StringUtils.isNotEmpty(workTypeId)) {
            queryParams.put("workTypeId", workTypeId);
        }
        Integer total = userMapper.getCount(queryParams);
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        List<User> userList = userMapper.getPage(queryParams);
        return new PageImpl<>(userList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertNewUser(User user, MultipartFile contractFile,MultipartFile headImg) {
        if(null!=contractFile&&!contractFile.equals("")){
            AttachmentLog attachmentLog=new AttachmentLog();
            //文件名
            String fileNames = contractFile.getOriginalFilename();
            //文件大小
            long size = contractFile.getSize();
            //文件类型
            String type = contractFile.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);

                //文件名前缀
                String fileNamePrefix = UUID.randomUUID().toString();
                //文件存放文件夹
                String urlStr = "UserFile"+File.separator+"ContractFile";
                //最终附件存放的目录
                rootPath = rootPath + urlStr + File.separator;
                StringBuilder fileFullName = new StringBuilder();
                String randomCode = GenerateUtil.randomIn(6);
                fileNamePrefix += randomCode;
                StringBuilder realFileName = new StringBuilder();
                realFileName.append(fileNamePrefix).append(File.separator);
                fileFullName = fileFullName.append(rootPath).append(realFileName);
                String filePath = fileFullName.toString();
                try {
                    //若文件夹不存在则先创建
                    File fileDir = new File(filePath);
                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }
                    //文件处理
                    FileUtil.uploadFile(contractFile.getBytes(), filePath, fileNames);
                    logger.info("--文件上传成功--");
                } catch (Exception ex) {
                    logger.info("--文件上传失败--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(filePath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--文件信息保存成功--");
                user.setContractFileId(attachmentLog.getID());
            }else{
                logger.info("--文件信息保存失败--");
            }
        }
        if(null!=headImg&&!headImg.equals("")){
            AttachmentLog attachmentLog=new AttachmentLog();
            //文件名
            String fileNames = headImg.getOriginalFilename();
            //文件大小
            long size = headImg.getSize();
            //文件类型
            String type = headImg.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);

                //文件名前缀
                String fileNamePrefix = UUID.randomUUID().toString();
                //文件存放文件夹
                String urlStr = "UserFile"+File.separator+"HeadImgFile";
                //最终附件存放的目录
                rootPath = rootPath + urlStr + File.separator;
                StringBuilder fileFullName = new StringBuilder();
                String randomCode = GenerateUtil.randomIn(6);
                fileNamePrefix += randomCode;
                StringBuilder realFileName = new StringBuilder();
                realFileName.append(fileNamePrefix).append(File.separator);
                fileFullName = fileFullName.append(rootPath).append(realFileName);
                String filePath = fileFullName.toString();
                try {
                    //若文件夹不存在则先创建
                    File fileDir = new File(filePath);
                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }
                    //文件处理
                    FileUtil.uploadFile(headImg.getBytes(), filePath, fileNames);
                    logger.info("--图片上传成功--");
                } catch (Exception ex) {
                    logger.info("--图片上传失败--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(filePath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--文件信息保存成功--");
                user.setHeadImgId(attachmentLog.getID());
            }else{
                logger.info("--文件信息保存失败--");
            }
        }
            return userMapper.inertNewUser(user) == 1 ? true : false;
        }

    @Override
    public int getRecordsByCardNumber(int certificate_type, String certificate_number) {
        return userMapper.getRecordsByCardNumber(certificate_type, certificate_number);
    }

    @Override
    public void deleteUserByID(Long id) {
         userMapper.deleteUserByID(id);
    }

    @Override
    public List<Subcontract> getSubcontractList() {
        return userMapper.getSubcontractList();
    }

    @Override
    public  List<User> getMonitorList() {return userMapper.getMonitorList();};

    @Override
    public  List<User> getUserUnassigned() {return userMapper.getUserUnassigned();};


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(User user, MultipartFile contractFile, MultipartFile headImg) {
        AttachmentLog attachmentLog=new AttachmentLog();
        if(null!=contractFile&&!contractFile.equals("")){
            AttachmentLog contractAtt=attachmentLogMapper.selectByConFile(user.getContractFileId());
            String rootPath =contractAtt.getSavePath();
            //清空文件夹文件
            //String requireFileName=requiredFile.getFileName();
            FileUtil.delAllFile(rootPath);
            //文件名
            String fileNames = contractFile.getOriginalFilename();
            //文件大小
            long size = contractFile.getSize();
            //文件类型
            String type = contractFile.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径

                try {
                    FileUtil.uploadFile(contractFile.getBytes(), rootPath, fileNames);//文件处理
                    logger.info("--文件上传成功--");
                } catch (Exception ex) {
                    logger.info("--文件上传失败--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(rootPath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--文件信息保存成功--");
                attachmentLogMapper.deleteByContractFileId(user.getContractFileId());
                user.setContractFileId(attachmentLog.getID());

            }else{
                logger.info("--文件信息保存失败--");
            }

        }

        if(null!=headImg&&!headImg.equals("")){
            AttachmentLog contractAtt=attachmentLogMapper.selectByConFile(user.getHeadImgId());
            String rootPath =contractAtt.getSavePath();
            //清空文件夹文件
            //String requireFileName=requiredFile.getFileName();
            FileUtil.delAllFile(rootPath);
            //文件名
            String fileNames = headImg.getOriginalFilename();
            //文件大小
            long size = headImg.getSize();
            //文件类型
            String type = headImg.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径

                try {
                    FileUtil.uploadFile(headImg.getBytes(), rootPath, fileNames);//文件处理
                    logger.info("--文件上传成功--");
                } catch (Exception ex) {
                    logger.info("--文件上传失败--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(rootPath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--文件信息保存成功--");
                attachmentLogMapper.deleteByContractFileId(user.getContractFileId());
                user.setHeadImgId(attachmentLog.getID());

            }else{
                logger.info("--文件信息保存失败--");
            }

        }

        return userMapper.updateUser(user) == 1 ? true : false;
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<WorkType> getWorkType() {
        return userMapper.getWorkType();
    }

    @Override
    public List<Project> getProjectList() {
        return userMapper.getProjectList();
    }

}
