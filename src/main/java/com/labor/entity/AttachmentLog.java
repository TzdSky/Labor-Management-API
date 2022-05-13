package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

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
    private Long ID;
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
     @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
     private Date createAt;
     /**
     * 修改时间
     */
     @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
     private Date updateAt;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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
