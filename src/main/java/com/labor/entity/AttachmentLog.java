package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@TableName("t_attachment_log")
public class AttachmentLog {
    private static final long serialVersionUID = 1L;
     /**
     * 文件id
     */
    private Long fileId;
     /**
     * 文件名称
     */
     private String fileName;
     /**
     * 文件类型
     */
     private String fileType;
     /**
     * 文件大小
     */
     private Integer fileSize;
     /**
     * 保存路径
     */
     private String savePath;
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

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
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