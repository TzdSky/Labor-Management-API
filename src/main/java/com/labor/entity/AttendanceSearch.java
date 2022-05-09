package com.labor.entity;

import java.util.Date;
import java.util.List;

/**
 * @author Tian
 * @date 2022/5/9
 */
public class AttendanceSearch {
     private static final long serialVersionUID = 1L;
     //主键标识
     private Long ID;

     //姓名
     private String name;

     //公司名称
     private String companyName;

     //所属班组
     private String groupName;

     //工种
     private Integer workTypeID;

     // 负责人id
     private Long principalID;

     //公司名称 ID外键
     private Long companyID;

     //所属班组 ID外键
     private Long groupID;

     //考勤组id
     private String attGroupID;

     //考勤月份 页面传过来
     private Date attDate;

     //打卡状态 搜索月份第1天
     private String clockStatus1;

     //打卡状态 搜索月份第2天
     private String clockStatus2;

     //打卡状态 搜索月份第3天
     private String clockStatus3;

     //打卡状态 搜索月份第4天
     private String clockStatus4;

     //打卡状态 搜索月份第5天
     private String clockStatus5;

     //打卡状态 搜索月份第6天
     private String clockStatus6;

     //打卡状态 搜索月份第7天
     private String clockStatus7;

     //打卡状态 搜索月份第8天
     private String clockStatus8;

     //打卡状态 搜索月份第9天
     private String clockStatus9;

     //打卡状态 搜索月份第10天
     private String clockStatus10;

     //打卡状态 搜索月份第11天
     private String clockStatus11;

     //打卡状态 搜索月份第12天
     private String clockStatus12;

     //打卡状态 搜索月份第13天
     private String clockStatus13;

     //打卡状态 搜索月份第14天
     private String clockStatus14;

     //打卡状态 搜索月份第15天
     private String clockStatus15;

     //打卡状态 搜索月份第16天
     private String clockStatus16;

     //打卡状态 搜索月份第17天
     private String clockStatus17;

     //打卡状态 搜索月份第18天
     private String clockStatus18;

     //打卡状态 搜索月份第19天
     private String clockStatus19;

     //打卡状态 搜索月份第20天
     private String clockStatus20;

     //打卡状态 搜索月份第21天
     private String clockStatus21;

     //打卡状态 搜索月份第22天
     private String clockStatus22;

     //打卡状态 搜索月份第23天
     private String clockStatus23;

     //打卡状态 搜索月份第24天
     private String clockStatus24;

     //打卡状态 搜索月份第25天
     private String clockStatus25;

     //打卡状态 搜索月份第26天
     private String clockStatus26;

     //打卡状态 搜索月份第27天
     private String clockStatus27;

     //打卡状态 搜索月份第28天
     private String clockStatus28;

     //打卡状态 搜索月份第29天
     private String clockStatus29;

     //打卡状态 搜索月份第30天
     private String clockStatus30;

     //打卡状态 搜索月份第31天
     private String clockStatus31;

     private List<String> headers;

     public List<String> getHeaders() {
          return headers;
     }

     public void setHeaders(List<String> headers) {
          this.headers = headers;
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

     public Integer getWorkTypeID() {
          return workTypeID;
     }

     public void setWorkTypeID(Integer workTypeID) {
          this.workTypeID = workTypeID;
     }

     public Long getPrincipalID() {
          return principalID;
     }

     public void setPrincipalID(Long principalID) {
          this.principalID = principalID;
     }

     public Long getCompanyID() {
          return companyID;
     }

     public void setCompanyID(Long companyID) {
          this.companyID = companyID;
     }

     public Long getGroupID() {
          return groupID;
     }

     public void setGroupID(Long groupID) {
          this.groupID = groupID;
     }

     public String getAttGroupID() {
          return attGroupID;
     }

     public void setAttGroupID(String attGroupID) {
          this.attGroupID = attGroupID;
     }

     public Date getAttDate() {
          return attDate;
     }

     public void setAttDate(Date attDate) {
          this.attDate = attDate;
     }

     public String getClockStatus1() {
          return clockStatus1;
     }

     public void setClockStatus1(String clockStatus1) {
          this.clockStatus1 = clockStatus1;
     }

     public String getClockStatus2() {
          return clockStatus2;
     }

     public void setClockStatus2(String clockStatus2) {
          this.clockStatus2 = clockStatus2;
     }

     public String getClockStatus3() {
          return clockStatus3;
     }

     public void setClockStatus3(String clockStatus3) {
          this.clockStatus3 = clockStatus3;
     }

     public String getClockStatus4() {
          return clockStatus4;
     }

     public void setClockStatus4(String clockStatus4) {
          this.clockStatus4 = clockStatus4;
     }

     public String getClockStatus5() {
          return clockStatus5;
     }

     public void setClockStatus5(String clockStatus5) {
          this.clockStatus5 = clockStatus5;
     }

     public String getClockStatus6() {
          return clockStatus6;
     }

     public void setClockStatus6(String clockStatus6) {
          this.clockStatus6 = clockStatus6;
     }

     public String getClockStatus7() {
          return clockStatus7;
     }

     public void setClockStatus7(String clockStatus7) {
          this.clockStatus7 = clockStatus7;
     }

     public String getClockStatus8() {
          return clockStatus8;
     }

     public void setClockStatus8(String clockStatus8) {
          this.clockStatus8 = clockStatus8;
     }

     public String getClockStatus9() {
          return clockStatus9;
     }

     public void setClockStatus9(String clockStatus9) {
          this.clockStatus9 = clockStatus9;
     }

     public String getClockStatus10() {
          return clockStatus10;
     }

     public void setClockStatus10(String clockStatus10) {
          this.clockStatus10 = clockStatus10;
     }

     public String getClockStatus11() {
          return clockStatus11;
     }

     public void setClockStatus11(String clockStatus11) {
          this.clockStatus11 = clockStatus11;
     }

     public String getClockStatus12() {
          return clockStatus12;
     }

     public void setClockStatus12(String clockStatus12) {
          this.clockStatus12 = clockStatus12;
     }

     public String getClockStatus13() {
          return clockStatus13;
     }

     public void setClockStatus13(String clockStatus13) {
          this.clockStatus13 = clockStatus13;
     }

     public String getClockStatus14() {
          return clockStatus14;
     }

     public void setClockStatus14(String clockStatus14) {
          this.clockStatus14 = clockStatus14;
     }

     public String getClockStatus15() {
          return clockStatus15;
     }

     public void setClockStatus15(String clockStatus15) {
          this.clockStatus15 = clockStatus15;
     }

     public String getClockStatus16() {
          return clockStatus16;
     }

     public void setClockStatus16(String clockStatus16) {
          this.clockStatus16 = clockStatus16;
     }

     public String getClockStatus17() {
          return clockStatus17;
     }

     public void setClockStatus17(String clockStatus17) {
          this.clockStatus17 = clockStatus17;
     }

     public String getClockStatus18() {
          return clockStatus18;
     }

     public void setClockStatus18(String clockStatus18) {
          this.clockStatus18 = clockStatus18;
     }

     public String getClockStatus19() {
          return clockStatus19;
     }

     public void setClockStatus19(String clockStatus19) {
          this.clockStatus19 = clockStatus19;
     }

     public String getClockStatus20() {
          return clockStatus20;
     }

     public void setClockStatus20(String clockStatus20) {
          this.clockStatus20 = clockStatus20;
     }

     public String getClockStatus21() {
          return clockStatus21;
     }

     public void setClockStatus21(String clockStatus21) {
          this.clockStatus21 = clockStatus21;
     }

     public String getClockStatus22() {
          return clockStatus22;
     }

     public void setClockStatus22(String clockStatus22) {
          this.clockStatus22 = clockStatus22;
     }

     public String getClockStatus23() {
          return clockStatus23;
     }

     public void setClockStatus23(String clockStatus23) {
          this.clockStatus23 = clockStatus23;
     }

     public String getClockStatus24() {
          return clockStatus24;
     }

     public void setClockStatus24(String clockStatus24) {
          this.clockStatus24 = clockStatus24;
     }

     public String getClockStatus25() {
          return clockStatus25;
     }

     public void setClockStatus25(String clockStatus25) {
          this.clockStatus25 = clockStatus25;
     }

     public String getClockStatus26() {
          return clockStatus26;
     }

     public void setClockStatus26(String clockStatus26) {
          this.clockStatus26 = clockStatus26;
     }

     public String getClockStatus27() {
          return clockStatus27;
     }

     public void setClockStatus27(String clockStatus27) {
          this.clockStatus27 = clockStatus27;
     }

     public String getClockStatus28() {
          return clockStatus28;
     }

     public void setClockStatus28(String clockStatus28) {
          this.clockStatus28 = clockStatus28;
     }

     public String getClockStatus29() {
          return clockStatus29;
     }

     public void setClockStatus29(String clockStatus29) {
          this.clockStatus29 = clockStatus29;
     }

     public String getClockStatus30() {
          return clockStatus30;
     }

     public void setClockStatus30(String clockStatus30) {
          this.clockStatus30 = clockStatus30;
     }

     public String getClockStatus31() {
          return clockStatus31;
     }

     public void setClockStatus31(String clockStatus31) {
          this.clockStatus31 = clockStatus31;
     }
}
