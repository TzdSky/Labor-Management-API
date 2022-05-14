package com.labor.entity;

/**
 * @author Tian
 * @date 2022/5/14
 */
public class UserForWorkType {
    private static final long serialVersionUID = 1L;

    private Long userID;
    private String userName;

    //头像路径
    private String headPath;
    //头像名称
    private String headName;
    //文件路径
    private String filePath;
    //文件名称
    private String fileName;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
}
