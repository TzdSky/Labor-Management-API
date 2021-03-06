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

         //??????????????????id
         Contract contract=contractMapper.findFileID(id);
         //????????????1
         if(null!=contract.getFileOneId()){
             AttachmentLog attachmentLogOne=attachmentLogMapper.selectByConFile(contract.getFileOneId());
             String FileOneUrl=attachmentLogOne.getSavePath();
             //????????????????????????
             FileUtil.delAllFile(FileOneUrl);
             //???????????????
             Boolean falg=FileUtil.delFolder(FileOneUrl.substring(0,FileOneUrl.length()-1));
             if(falg){
                 attachmentLogMapper.deleteByContractFileId(contract.getFileOneId());
             }
         }
         //????????????2
         if(null!=contract.getFileTwoId()){
             AttachmentLog attachmentLogTwo=attachmentLogMapper.selectByConFile(contract.getFileTwoId());
             //????????????????????????
             FileUtil.delAllFile(attachmentLogTwo.getSavePath());
             String FileTwoUrl=attachmentLogTwo.getSavePath();
             //???????????????
             Boolean falg=FileUtil.delFolder(FileTwoUrl.substring(0,FileTwoUrl.length()-1));
             if(falg){
                 //??????????????????
                 attachmentLogMapper.deleteByContractFileId(contract.getFileTwoId());
             }
         }
        //??????????????????
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
            //?????????
            String fileNames = fileOne.getOriginalFilename();
            //????????????
            long size = fileOne.getSize();
            //????????????
            String type = fileOne.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //??????????????????
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);
                //????????????
                String urlStr = "ContractFile"+File.separator+"FileOne";
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
                    FileUtil.uploadFile(fileOne.getBytes(), filePath, fileNames);//????????????
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
                contract.setFileOneId(attachmentLog.getID());
                contract.setUploadTimeOne(attachmentLog.getCreateAt());
            }else{
                logger.info("--????????????????????????--");
            }
        }

        if(null != fileTwo && !"".equals(fileTwo)){
            //?????????
            String fileNames = fileTwo.getOriginalFilename();
            //????????????
            long size = fileTwo.getSize();
            //????????????
            String type = fileTwo.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //??????????????????
                String rootPath = attachRootPath.replace("\\", File.separator).replace("/", File.separator);
                //????????????
                String urlStr = "ContractFile"+File.separator+"FileTwo";
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
                    FileUtil.uploadFile(fileTwo.getBytes(), filePath, fileNames);//????????????
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
                contract.setFileTwoId(attachmentLog.getID());
                contract.setUploadTimeTwo(attachmentLog.getCreateAt());
            }else{
                logger.info("--????????????????????????--");
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
                logger.info("--??????id??????--");
                return false;
            }
            String rootPath =requiredFile.getSavePath();
            FileUtil.delAllFile(rootPath);
            //?????????
            String fileNames = fileOne.getOriginalFilename();
            //????????????
            long size = fileOne.getSize();
            //????????????
            String type = fileOne.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //??????????????????

                try {
                    FileUtil.uploadFile(fileOne.getBytes(), rootPath, fileNames);//????????????
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
                logger.info("--FileOne????????????????????????--");
                attachmentLogMapper.deleteByContractFileId(contract.getFileOneId());
                contract.setFileOneId(attachmentLog.getID());
                contract.setUploadTimeOne(attachmentLog.getCreateAt());
            }else{
                logger.info("--FileOne????????????????????????--");
            }
        }

        if(null != fileTwo && !"".equals(fileTwo)){
            AttachmentLog attachmentLog=new AttachmentLog();
            AttachmentLog requiredFile=attachmentLogMapper.selectByConFile(contract.getFileTwoId());
            if(null==requiredFile){
                logger.info("--??????id??????--");
                return false;
            }
            String rootPath =requiredFile.getSavePath();
            FileUtil.delAllFile(rootPath);
            //?????????
            String fileNames = fileTwo.getOriginalFilename();
            //????????????
            long size = fileTwo.getSize();
            //????????????
            String type = fileTwo.getContentType();

            if (null != fileNames && !"".equals(fileNames)) {
                //??????????????????

                try {
                    FileUtil.uploadFile(fileTwo.getBytes(), rootPath, fileNames);//????????????
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
                logger.info("--FileOne????????????????????????--");
                attachmentLogMapper.deleteByContractFileId(contract.getFileTwoId());
                contract.setFileTwoId(attachmentLog.getID());
                contract.setUploadTimeTwo(attachmentLog.getCreateAt());
            }else{
                logger.info("--FileOne????????????????????????--");
            }

        }

        return contractMapper.updateContract(contract) == 1 ? true : false;
    }

    @Override
    public List<ProgameCompany> findProgameCompany() {
        return contractMapper.findProgameCompany();
    }


}
