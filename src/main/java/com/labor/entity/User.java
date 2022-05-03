package com.labor.entity;

import java.util.Date;

/**
 * @author BoCong
 * @date 2022/5/1
 */
public class User {
     private static final long serialVersionUID = 1L;
     //主键标识
     private Long ID;

     //姓名
     private String name;

     //证件类型
     private Integer certificateType;

     //证件号码
     private Integer certificateNumber;

     //性别
     private Integer gender;

     //民族
     private String nation;

     //出生日期
     private Date birthday;

     //地址
     private String address;

     //项目名称
     private String projectName;

     //公司名称
     private String company;

     //所属班组
     private String goupName;

     //工种
     private String workType;

     //工人类型
     private String userType;

     //是否生病
     private Integer isSick;

     //手机号码
     private Integer phone;

     //工资卡号
     private Integer cardNumber;

     //是否有合同
     private Integer isContract;

     //合同名称
     private String contractName;

     //合同编号
     private String contractNumber;

     //工龄
     private Integer workAge;

     //认证技能
     private String workCertificate;

     //是否健康 0 健康 1不健康
     private Integer health;

     //危险操作
     private String dangerOperate;

     //违规记录
     private String irregularRecord ;


     //通报批评
     private Integer criticism;

     //缺勤
     private Integer absenceWork;

     //创建者
     private String creater;

     //创建时间
     private Date createAt;

     //修改时间
     private Date updateAt;

     public Long getID() {
          return ID;
     }

     public void setID(Long ID) {
          this.ID = ID;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public Integer getCertificateType() {
          return certificateType;
     }

     public void setCertificateType(Integer certificateType) {
          this.certificateType = certificateType;
     }

     public Integer getCertificateNumber() {
          return certificateNumber;
     }

     public void setCertificateNumber(Integer certificateNumber) {
          this.certificateNumber = certificateNumber;
     }

     public Integer getGender() {
          return gender;
     }

     public void setGender(Integer gender) {
          this.gender = gender;
     }

     public String getNation() {
          return nation;
     }

     public void setNation(String nation) {
          this.nation = nation;
     }

     public Date getBirthday() {
          return birthday;
     }

     public void setBirthday(Date birthday) {
          this.birthday = birthday;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getProjectName() {
          return projectName;
     }

     public void setProjectName(String projectName) {
          this.projectName = projectName;
     }

     public String getCompany() {
          return company;
     }

     public void setCompany(String company) {
          this.company = company;
     }

     public String getGoupName() {
          return goupName;
     }

     public void setGoupName(String goupName) {
          this.goupName = goupName;
     }

     public String getWorkType() {
          return workType;
     }

     public void setWorkType(String workType) {
          this.workType = workType;
     }

     public String getUserType() {
          return userType;
     }

     public void setUserType(String userType) {
          this.userType = userType;
     }

     public Integer getIsSick() {
          return isSick;
     }

     public void setIsSick(Integer isSick) {
          this.isSick = isSick;
     }

     public Integer getPhone() {
          return phone;
     }

     public void setPhone(Integer phone) {
          this.phone = phone;
     }

     public Integer getCardNumber() {
          return cardNumber;
     }

     public void setCardNumber(Integer cardNumber) {
          this.cardNumber = cardNumber;
     }

     public Integer getIsContract() {
          return isContract;
     }

     public void setIsContract(Integer isContract) {
          this.isContract = isContract;
     }

     public String getContractName() {
          return contractName;
     }

     public void setContractName(String contractName) {
          this.contractName = contractName;
     }

     public String getContractNumber() {
          return contractNumber;
     }

     public void setContractNumber(String contractNumber) {
          this.contractNumber = contractNumber;
     }

     public Integer getWorkAge() {
          return workAge;
     }

     public void setWorkAge(Integer workAge) {
          this.workAge = workAge;
     }

     public String getWorkCertificate() {
          return workCertificate;
     }

     public void setWorkCertificate(String workCertificate) {
          this.workCertificate = workCertificate;
     }

     public Integer getHealth() {
          return health;
     }

     public void setHealth(Integer health) {
          this.health = health;
     }

     public String getDangerOperate() {
          return dangerOperate;
     }

     public void setDangerOperate(String dangerOperate) {
          this.dangerOperate = dangerOperate;
     }

     public String getIrregularRecord() {
          return irregularRecord;
     }

     public void setIrregularRecord(String irregularRecord) {
          this.irregularRecord = irregularRecord;
     }

     public Integer getCriticism() {
          return criticism;
     }

     public void setCriticism(Integer criticism) {
          this.criticism = criticism;
     }

     public Integer getAbsenceWork() {
          return absenceWork;
     }

     public void setAbsenceWork(Integer absenceWork) {
          this.absenceWork = absenceWork;
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
