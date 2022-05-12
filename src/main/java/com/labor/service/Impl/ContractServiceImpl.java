package com.labor.service.Impl;

import com.labor.controller.UserController;
import com.labor.entity.AttachmentLog;
import com.labor.entity.Contract;
import com.labor.entity.ProgameCompany;
import com.labor.entity.Subcontract;
import com.labor.mapper.AttachmentLogMapper;
import com.labor.mapper.ContractMapper;
import com.labor.service.ContractService;
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
import java.util.*;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@Service
public class ContractServiceImpl implements ContractService {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private ContractMapper contractMapper;
    @Value("${attachRootPath}")
    private String attachRootPath;
    @Autowired
    private AttachmentLogMapper attachmentLogMapper;
    @Override
    public Page<Contract> getContractList(HttpServletRequest request, Pageable page) {
        Map<String, Object> queryParams = new HashMap<>();
        String contractName = request.getParameter("contractName");
        String contractType = request.getParameter("contractType");
        String beginTime = request.getParameter("beginDate");
        String endTime = request.getParameter("endDate");
        if (StringUtils.isNotEmpty(contractName)) {
            queryParams.put("contractName", contractName);
        }
        if (StringUtils.isNotEmpty(contractType)) {
            queryParams.put("contractType", Integer.valueOf(contractType));
        }
        if (StringUtils.isNotEmpty(beginTime)) {
            queryParams.put("beginTime", beginTime);
        }
        if (StringUtils.isNotEmpty(endTime)) {
            queryParams.put("endTime", endTime);
        }
        Integer total = contractMapper.getCount(queryParams);
        queryParams.put("page", (page.getPageNumber() - 1) * page.getPageSize());
        queryParams.put("size", page.getPageSize());
        List<Contract> contractMapperList = contractMapper.getPage(queryParams);
        return new PageImpl<>(contractMapperList, PageRequest.of(page.getPageNumber() - 1, page.getPageSize()), total);
    }

    @Override
    public Contract findContractByID(Long id) {
        return contractMapper.findContractByID(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteContractID(Long id) {

         //查询所有文件id
         Contract contract=contractMapper.findFileID(id);
         //删除文件1
         if(null!=contract.getFileOneId()){
             AttachmentLog attachmentLogOne=attachmentLogMapper.selectByConFile(contract.getFileOneId());
             String FileOneUrl=attachmentLogOne.getSavePath();
             //删除文件夹内文件
             FileUtil.delAllFile(FileOneUrl);
             //删除文件夹
             Boolean falg=FileUtil.delFolder(FileOneUrl.substring(0,FileOneUrl.length()-1));
             if(falg){
                 attachmentLogMapper.deleteByContractFileId(contract.getFileOneId());
             }
         }
         //删除文件2
         if(null!=contract.getFileTwoId()){
             AttachmentLog attachmentLogTwo=attachmentLogMapper.selectByConFile(contract.getFileTwoId());
             //删除文件夹内文件
             FileUtil.delAllFile(attachmentLogTwo.getSavePath());
             String FileTwoUrl=attachmentLogTwo.getSavePath();
             //删除文件夹
             Boolean falg=FileUtil.delFolder(FileTwoUrl.substring(0,FileTwoUrl.length()-1));
             if(falg){
                 //删除文件信息
                 attachmentLogMapper.deleteByContractFileId(contract.getFileTwoId());
             }
         }
        //合同信息删除
        contractMapper.deleteContractID(id);
    }

    @Override
    public int getRecordsByName(String contractName) {
        return contractMapper.getRecordsByName(contractName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertContract(Contract contract, MultipartFile fileOne,MultipartFile fileTwo) {
        AttachmentLog attachmentLog=new AttachmentLog();
        if(null != fileOne && !"".equals(fileOne)){
            //文件名
            String fileNames = fileOne.getOriginalFilename();
            //文件大小
            long size = fileOne.getSize();
            //文件类型
            String type = fileOne.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);
                //文件存放
                String urlStr = "ContractFile"+File.separator+"FileOne";
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
                    FileUtil.uploadFile(fileOne.getBytes(), filePath, fileNames);//文件处理
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
                contract.setFileOneId(attachmentLog.getID());
                contract.setUploadTimeOne(attachmentLog.getCreateAt());
            }else{
                logger.info("--文件信息保存失败--");
            }
        }

        if(null != fileTwo && !"".equals(fileTwo)){
            //文件名
            String fileNames = fileTwo.getOriginalFilename();
            //文件大小
            long size = fileTwo.getSize();
            //文件类型
            String type = fileTwo.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);
                //文件存放
                String urlStr = "ContractFile"+File.separator+"FileTwo";
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
                    FileUtil.uploadFile(fileTwo.getBytes(), filePath, fileNames);//文件处理
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
                contract.setFileTwoId(attachmentLog.getID());
                contract.setUploadTimeTwo(attachmentLog.getCreateAt());
            }else{
                logger.info("--文件信息保存失败--");
            }
        }

        return contractMapper.insertContract(contract) == 1 ? true : false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateContract(Contract contract, MultipartFile fileOne,MultipartFile fileTwo) {
        if(null != fileOne && !"".equals(fileOne)){
            AttachmentLog attachmentLog=new AttachmentLog();
            AttachmentLog requiredFile=attachmentLogMapper.selectByConFile(contract.getFileOneId());
            if(null==requiredFile){
                logger.info("--文件id错误--");
                return false;
            }
            String rootPath =requiredFile.getSavePath();
            FileUtil.delAllFile(rootPath);
            //文件名
            String fileNames = fileOne.getOriginalFilename();
            //文件大小
            long size = fileOne.getSize();
            //文件类型
            String type = fileOne.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径

                try {
                    FileUtil.uploadFile(fileOne.getBytes(), rootPath, fileNames);//文件处理
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
                logger.info("--FileOne文件信息保存成功--");
                attachmentLogMapper.deleteByContractFileId(contract.getFileOneId());
                contract.setFileOneId(attachmentLog.getID());
                contract.setUploadTimeOne(attachmentLog.getCreateAt());
            }else{
                logger.info("--FileOne文件信息保存失败--");
            }
        }

        if(null != fileTwo && !"".equals(fileTwo)){
            AttachmentLog attachmentLog=new AttachmentLog();
            AttachmentLog requiredFile=attachmentLogMapper.selectByConFile(contract.getFileTwoId());
            if(null==requiredFile){
                logger.info("--文件id错误--");
                return false;
            }
            String rootPath =requiredFile.getSavePath();
            FileUtil.delAllFile(rootPath);
            //文件名
            String fileNames = fileTwo.getOriginalFilename();
            //文件大小
            long size = fileTwo.getSize();
            //文件类型
            String type = fileTwo.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //文件存放路径

                try {
                    FileUtil.uploadFile(fileTwo.getBytes(), rootPath, fileNames);//文件处理
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
                logger.info("--FileOne文件信息保存成功--");
                attachmentLogMapper.deleteByContractFileId(contract.getFileTwoId());
                contract.setFileTwoId(attachmentLog.getID());
                contract.setUploadTimeTwo(attachmentLog.getCreateAt());
            }else{
                logger.info("--FileOne文件信息保存失败--");
            }

        }

        return contractMapper.updateContract(contract) == 1 ? true : false;
    }

    @Override
    public List<ProgameCompany> findProgameCompany() {
        return contractMapper.findProgameCompany();
    }


}
