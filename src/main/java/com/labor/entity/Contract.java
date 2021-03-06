package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@TableName("t_contract")
public class Contract {
    private static final long serialVersionUID = 1L;
    /**
     *  主键标识
     */
    private Long ID;
    /**
     * 合同名称
     */
    private String contractName;

    /**
     *合同类型 1 劳务合同 2租赁合同
     */
    private Integer contractType;
    /**
     *  公司Id
     */
    private Long companyId;

    /**
     * 开始时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date beginDate;

    /**
     * 结束时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    /**
     * 签署公司
     */
    private String signCompany;



    /**
     * 文件1上传时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date uploadTimeOne;

    /**
     *  文件2上传时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date uploadTimeTwo;

    /**
     * 合同状态(0 生效 1失效
     */
    private Integer status;

    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createAt;
    /**
     * 修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date updateAt;

    /**
     *  公司名称
     */
    private String companyName;

    private Long fileOneId;

    private Long fileTwoId;

    private String fileOneName;

    private String fileOnePath;

    private String fileTwoName;

    private String fileTwoPath;


    public String getFileOneName() {
        return fileOneName;
    }

    public void setFileOneName(String fileOneName) {
        this.fileOneName = fileOneName;
    }

    public String getFileOnePath() {
        return fileOnePath;
    }

    public void setFileOnePath(String fileOnePath) {
        this.fileOnePath = fileOnePath;
    }

    public String getFileTwoName() {
        return fileTwoName;
    }

    public void setFileTwoName(String fileTwoName) {
        this.fileTwoName = fileTwoName;
    }

    public String getFileTwoPath() {
        return fileTwoPath;
    }

    public void setFileTwoPath(String fileTwoPath) {
        this.fileTwoPath = fileTwoPath;
    }

    public Long getFileOneId() {
        return fileOneId;
    }

    public void setFileOneId(Long fileOneId) {
        this.fileOneId = fileOneId;
    }

    public Long getFileTwoId() {
        return fileTwoId;
    }

    public void setFileTwoId(Long fileTwoId) {
        this.fileTwoId = fileTwoId;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSignCompany() {
        return signCompany;
    }

    public void setSignCompany(String signCompany) {
        this.signCompany = signCompany;
    }



    public Date getUploadTimeOne() {
        return uploadTimeOne;
    }

    public void setUploadTimeOne(Date uploadTimeOne) {
        this.uploadTimeOne = uploadTimeOne;
    }

    public Date getUploadTimeTwo() {
        return uploadTimeTwo;
    }

    public void setUploadTimeTwo(Date uploadTimeTwo) {
        this.uploadTimeTwo = uploadTimeTwo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
