package com.labor.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@TableName("t_subcontract")
public class Subcontract {
    private static final long serialVersionUID = 1L;
    /**
     *  主键标识
     */
    private Long ID;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 负责人标识
     */
    private Long principalId;

    /**
     * 手机号码
     */
    private Integer phone;

    /**
     * 分包内容
     */
    private String subcontracContent;

    /**
     * 资质文件id
     */
    private Long requiredFileId;

    /**
     * 注册地址
     */
    private String address;

    /**
     *电话
     */
    private Integer telephone;

    /**
     * 开户银行
     */
    private String accountBank;

    /**
     * 纳税人识别号
     */
    private Integer identificationNumber;

    /**
     * 类型
     */
    private Integer subcontractType;

    /**
     * 备注
     */
    private String remark;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Long getPrincipalId() {
        return principalId;
    }

    public void setPrincipalId(Long principalId) {
        this.principalId = principalId;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getSubcontracContent() {
        return subcontracContent;
    }

    public void setSubcontracContent(String subcontracContent) {
        this.subcontracContent = subcontracContent;
    }

    public Long getRequiredFileId() {
        return requiredFileId;
    }

    public void setRequiredFileId(Long requiredFileId) {
        this.requiredFileId = requiredFileId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public Integer getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(Integer identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Integer getSubcontractType() {
        return subcontractType;
    }

    public void setSubcontractType(Integer subcontractType) {
        this.subcontractType = subcontractType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
