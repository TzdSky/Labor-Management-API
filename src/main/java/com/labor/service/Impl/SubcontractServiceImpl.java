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
        if(null != fileName && !"".equals(fileName)){
            //?????????
            String fileNames = fileName.getOriginalFilename();
            //????????????
            long size = fileName.getSize();
            //????????????
            String type = fileName.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //??????????????????
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);
                //????????????
                String urlStr = "RequiredFile";
                //???????????????
                String fileNamePrefix = UUID.randomUUID().toString();

                //???????????????????????????
                rootPath = rootPath + urlStr + File.separator;
                StringBuilder fileFullName = new StringBuilder();
                String randomCode = GenerateUtil.randomIn(6);
                fileNamePrefix += randomCode;
                StringBuilder realFileName = new StringBuilder();
                realFileName.append(fileNamePrefix).append(File.separator);
                fileFullName = fileFullName.append(rootPath).append(realFileName);
                String filePath = fileFullName.toString();
                try {
                    //?????????????????????????????????
                    File fileDir = new File(filePath);
                    if (!fileDir.exists()) {
                        fileDir.mkdirs();
                    }
                    FileUtil.uploadFile(fileName.getBytes(), filePath, fileNames);//????????????
                } catch (Exception ex) {
                    logger.info("--??????????????????--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(filePath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--????????????????????????--");
                subcontract.setRequiredFileId(attachmentLog.getID());
            }else{
                logger.info("--????????????????????????--");
            }
        }

        return subcontractMapper.insertSubcontract(subcontract) == 1 ? true : false;
    }

    @Override
    public void deleteByID(Long id) {
        Subcontract subcontract=subcontractMapper.findSubcontractByID(id);
        //????????????1
        if(null!=subcontract.getRequiredFileId()){
            AttachmentLog attachmentLogOne=attachmentLogMapper.selectByConFile(subcontract.getRequiredFileId());
            String requireFileUrl=attachmentLogOne.getSavePath();
            //????????????????????????
            FileUtil.delAllFile(requireFileUrl);
            //???????????????
            Boolean falg=FileUtil.delFolder(requireFileUrl.substring(0,requireFileUrl.length()-1));
            if(falg){
                attachmentLogMapper.deleteByContractFileId(subcontract.getRequiredFileId());
            }
        }
         subcontractMapper.deleteByID(id);
    }

    @Override
    public Subcontract findSubcontractByID(Long id) {
        return subcontractMapper.findSubcontractByID(id);
    }

    @Override
    public boolean updateSubcontract(Subcontract subcontract, MultipartFile fileName) {
        if(null != fileName && !"".equals(fileName)){
            AttachmentLog attachmentLog=new AttachmentLog();
            AttachmentLog requiredFile=attachmentLogMapper.selectByConFile(subcontract.getRequiredFileId());
            if(null==requiredFile){
                logger.info("--??????id??????--");
                return false;
            }
            String rootPath =requiredFile.getSavePath();
//            String requireFileName=requiredFile.getFileName();
//            //?????????????????????
//            FileUtil.deleteDataFile(rootPath,requireFileName);
            FileUtil.delAllFile(rootPath);
            //?????????
            String fileNames = fileName.getOriginalFilename();
            //????????????
            long size = fileName.getSize();
            //????????????
            String type = fileName.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //??????????????????

                try {
                    FileUtil.uploadFile(fileName.getBytes(), rootPath, fileNames);//????????????
                    logger.info("--??????????????????--");
                } catch (Exception ex) {
                    logger.info("--??????????????????--");
                    ex.printStackTrace();
                }
                attachmentLog.setFileName(fileNames);
                attachmentLog.setSavePath(rootPath);
            }
            attachmentLog.setFileSize((int) size);
            attachmentLog.setFileType(type);
            Integer count=attachmentLogMapper.insertAttachLog(attachmentLog);
            if(count>0){
                logger.info("--????????????????????????--");
                attachmentLogMapper.deleteByContractFileId(subcontract.getRequiredFileId());
                subcontract.setRequiredFileId(attachmentLog.getID());

            }else{
                logger.info("--????????????????????????--");
            }

        }
        return subcontractMapper.updateSubcontract(subcontract) == 1 ? true : false;
    }
}
