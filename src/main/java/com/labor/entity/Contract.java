package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

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
     * 文件名称
     */
    private String contractName;

    /**
     *合同类型
     */
    private Integer contractType;
    /**
     *  公司Id
     */
    private Long companyId;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 签署公司
     */
    private String signCompany;

    /**
     * 附件id
     */
    private Long fileId;

    /**
     * 上传时间
     */
    private Date uploadTime;

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
    private Date createAt;
    /**
     * 修改时间
     */
    private Date updateAt;

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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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
}
