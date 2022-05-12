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
     private String certificateNumber;

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
     private String companyName;

     //所属班组
     private String groupName;

     //工种
     private Integer workTypeId;


     //项目名称 ID外键
     private Long projectID;

     //公司名称 ID外键
     private Long companyID;

     //所属班组 ID外键
     private Long goupID;


     //工人类型
     private String userType;

     /**
      * 考勤组id
      */
     private String attGroupId;


     //是否生病
     private Integer isSick;

     //手机号码
     private Integer phone;

     //工资卡号
     private Integer cardNumber;

     //是否有合同
     private Integer isContract;

     //头像文件id
     private Long headImgId;

     //合同文件id
     private Long contractFileId;

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

     //年龄
     private Integer age;

     //籍贯
     private String nativePlace;

     private Integer approachStatus;

     //头像路径
     private String headPath;
     //头像名称
     private String headName;
     //文件路径
     private String filePath;
     //文件名称
     private String fileName;

     public String getHeadPath() {
          return headPath;
     }

     public void setHeadPath(String headPath) {
          this.headPath = headPath;
     }

     public String getHeadName() {
          return headName;
     }

     public void setHeadName(String headName) {
          this.headName = headName;
     }

     public String getFilePath() {
          return filePath;
     }

     public void setFilePath(String filePath) {
          this.filePath = filePath;
     }

     public String getFileName() {
          return fileName;
     }

     public void setFileName(String fileName) {
          this.fileName = fileName;
     }

     public String getAttGroupId() {
          return attGroupId;
     }

     public void setAttGroupId(String attGroupId) {
          this.attGroupId = attGroupId;
     }

     public Integer getApproachStatus() {
          return approachStatus;
     }

     public void setApproachStatus(Integer approachStatus) {
          this.approachStatus = approachStatus;
     }

     public Integer getWorkTypeId() {
          return workTypeId;
     }

     public void setWorkTypeId(Integer workTypeId) {
          this.workTypeId = workTypeId;
     }

     public String getNativePlace() {
          return nativePlace;
     }

     public void setNativePlace(String nativePlace) {
          this.nativePlace = nativePlace;
     }

     public Integer getAge() {
          return age;
     }

     public void setAge(Integer age) {
          this.age = age;
     }

     public Long getProjectID() {
          return projectID;
     }

     public void setProjectID(Long projectID) {
          this.projectID = projectID;
     }

     public Long getCompanyID() {
          return companyID;
     }

     public void setCompanyID(Long companyID) {
          this.companyID = companyID;
     }

     public Long getGoupID() {
          return goupID;
     }

     public void setGoupID(Long goupID) {
          this.goupID = goupID;
     }


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

     public String getCertificateNumber() {
          return certificateNumber;
     }

     public void setCertificateNumber(String certificateNumber) {
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

     public String getCompanyName() {
          return companyName;
     }

     public void setCompanyName(String companyName) {
          this.companyName = companyName;
     }

     public String getGroupName() {
          return groupName;
     }

     public void setGroupName(String groupName) {
          this.groupName = groupName;
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

     public Long getHeadImgId() {
          return headImgId;
     }

     public void setHeadImgId(Long headImgId) {
          this.headImgId = headImgId;
     }

     public Long getContractFileId() {
          return contractFileId;
     }

     public void setContractFileId(Long contractFileId) {
          this.contractFileId = contractFileId;
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
