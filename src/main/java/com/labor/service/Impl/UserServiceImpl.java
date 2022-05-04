package com.labor.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.labor.controller.UserController;
import com.labor.entity.AttachmentLog;
import com.labor.entity.Subcontract;
import com.labor.entity.User;
import com.labor.enums.DateStyleEnum;
import com.labor.mapper.AttachmentLogMapper;
import com.labor.mapper.UserMapper;
import com.labor.service.UserService;
import com.labor.utils.DateUtil;
import com.labor.utils.FileUtil;
import com.labor.utils.GenerateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean insertNewUser(User user, MultipartFile fileName) {
        AttachmentLog attachmentLog=new AttachmentLog();
        if(!fileName.isEmpty()){
            //文件名
            String fileNames = fileName.getOriginalFilename();
            //文件大小
            long size = fileName.getSize();
            //文件类型
            String type = fileName.getContentType();
            //根据日期生成目录
            Date date = new Date();
            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);
                //时间格式
                String dateStr = DateUtil.dateToString(date, DateStyleEnum.YYYY_MM_DD);
                //文件名前缀
                String fileNamePrefix = UUID.randomUUID().toString();
                if (null == dateStr) {
                    logger.info("创建附件目录失败...");
                    dateStr = "temp";
                } else {
                    dateStr = dateStr.replace("-", File.separator);
                }
                //最终附件存放的目录
                rootPath = rootPath + dateStr + File.separator;
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
                    FileUtil.uploadFile(fileName.getBytes(), filePath, fileNames);//文件处理
                } catch (Exception ex) {
                    logger.info("--文件上传成功--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileId(GenerateUtil.generateLongId());
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(filePath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            attachmentLog.setCreateAt(new Date());
            Integer count=attachmentLogMapper.insert(attachmentLog);
            if(count>0){
                logger.info("--文件信息保存成功--");
            }else{
                logger.info("--文件信息保存失败--");
            }
        }

            return userMapper.inertNewUser(user) == 1 ? true : false;
        }

    @Override
    public int getRecordsByCardNumber(int certificate_type, int certificate_number) {
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
}
