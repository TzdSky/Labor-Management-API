package com.labor.service.Impl;

import com.labor.controller.UserController;
import com.labor.entity.AttachmentLog;
import com.labor.entity.Subcontract;
import com.labor.mapper.AttachmentLogMapper;
import com.labor.mapper.SubcontractMapper;
import com.labor.service.SubcontracService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Service
public class SubcontractServiceImpl implements SubcontracService {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private SubcontractMapper subcontractMapper;
    @Value("${attachRootPath}")
    private String attachRootPath;
    @Autowired
    private AttachmentLogMapper attachmentLogMapper;
    @Override
    public Page<Subcontract> subcontractList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String companyName = request.getParameter("companyName");
        String subcontractType = request.getParameter("subcontractType");

        if (StringUtils.isNotEmpty(companyName)) {
            queryParams.put("companyName", companyName);
        }
        if (StringUtils.isNotEmpty(subcontractType)) {
            queryParams.put("subcontractType", Integer.valueOf(subcontractType));
        }

        Integer total = subcontractMapper.getCount(queryParams);
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        List<Subcontract> subcontractList = subcontractMapper.getPage(queryParams);
        return new PageImpl<>(subcontractList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }

    @Override
    public int getRecordsByCompanyName(String companyName) {
        return subcontractMapper.getRecordsByCompanyName(companyName);
    }

    @Override
    public boolean insertSubcontract(Subcontract subcontract, MultipartFile fileName) {
        AttachmentLog attachmentLog=new AttachmentLog();
        if(!fileName.isEmpty()){
            //文件名
            String fileNames = fileName.getOriginalFilename();
            //文件大小
            long size = fileName.getSize();
            //文件类型
            String type = fileName.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);
                //时间格式
                String urlStr = "RequiredFile";
                //文件名前缀
                String fileNamePrefix = UUID.randomUUID().toString();

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
                    FileUtil.uploadFile(fileName.getBytes(), filePath, fileNames);//文件处理
                } catch (Exception ex) {
                    logger.info("--文件上传成功--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(filePath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            attachmentLog.setCreateAt(new Date());
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--文件信息保存成功--");
                subcontract.setRequiredFileId(attachmentLog.getID());
            }else{
                logger.info("--文件信息保存失败--");
            }
        }

        return subcontractMapper.insertSubcontract(subcontract) == 1 ? true : false;
    }

    @Override
    public void deleteByID(Long id) {
         subcontractMapper.deleteByID(id);
    }

    @Override
    public Subcontract findSubcontractByID(Long id) {
        return subcontractMapper.findSubcontractByID(id);
    }

    @Override
    public boolean updateSubcontract(Subcontract subcontract, MultipartFile fileName) {
        AttachmentLog attachmentLog=new AttachmentLog();
        if(!fileName.isEmpty()){
            AttachmentLog requiredFile=attachmentLogMapper.selectByConFile(subcontract.getRequiredFileId());
            if(null==requiredFile){
                logger.info("--文件id错误--");
                return false;
            }
            String rootPath =requiredFile.getSavePath();
//            String requireFileName=requiredFile.getFileName();
//            //清空文件夹文件
//            FileUtil.deleteDataFile(rootPath,requireFileName);
            FileUtil.delAllFile(rootPath);
            //文件名
            String fileNames = fileName.getOriginalFilename();
            //文件大小
            long size = fileName.getSize();
            //文件类型
            String type = fileName.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径

                try {
                    FileUtil.uploadFile(fileName.getBytes(), rootPath, fileNames);//文件处理
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
            attachmentLog.setCreateAt(new Date());
            attachmentLog.setUpdateAt(new Date());
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--文件信息保存成功--");
                attachmentLogMapper.deleteByContractFileId(subcontract.getRequiredFileId());
                subcontract.setRequiredFileId(attachmentLog.getID());

            }else{
                logger.info("--文件信息保存失败--");
            }

        }

        return subcontractMapper.updateSubcontract(subcontract) == 1 ? true : false;
    }
}
