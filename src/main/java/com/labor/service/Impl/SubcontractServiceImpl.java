package com.labor.service.Impl;

import com.labor.controller.UserController;
import com.labor.entity.AttachmentLog;
import com.labor.entity.Subcontract;
import com.labor.enums.DateStyleEnum;
import com.labor.mapper.AttachmentLogMapper;
import com.labor.mapper.SubcontractMapper;
import com.labor.service.SubcontracService;
import com.labor.utils.DateUtil;
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

        return subcontractMapper.insert(subcontract) == 1 ? true : false;
    }

    @Override
    public void deleteByID(Long id) {
         subcontractMapper.deleteByID(id);
    }
}