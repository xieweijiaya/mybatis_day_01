package com.zking.ssm.model;

import lombok.ToString;

import java.util.Date;

@ToString
public class MyFile {
    private Long fileId;

    private String realName;

    private String contentType;

    private Date updateDatetime;

    private String filePath;

    public MyFile(Long fileId, String realName, String contentType, Date updateDatetime, String filePath) {
        this.fileId = fileId;
        this.realName = realName;
        this.contentType = contentType;
        this.updateDatetime = updateDatetime;
        this.filePath = filePath;
    }

    public MyFile() {
        super();
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}