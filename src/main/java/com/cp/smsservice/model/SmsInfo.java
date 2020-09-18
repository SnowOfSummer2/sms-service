package com.cp.smsservice.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class SmsInfo {
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(required = true)
    private String smsTitle;

    @ApiModelProperty(required = true)
    private String smsInfo;

    @ApiModelProperty(hidden = true)
    private Integer status;

    @ApiModelProperty(hidden = true)
    private String failReason;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(required = false,dataType = "Date",example = "2020/01/01 20:00:00")
    private Date excuteTime;

    @ApiModelProperty(required = true)
    private String sendPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmsTitle() {
        return smsTitle;
    }

    public void setSmsTitle(String smsTitle) {
        this.smsTitle = smsTitle == null ? null : smsTitle.trim();
    }

    public String getSmsInfo() {
        return smsInfo;
    }

    public void setSmsInfo(String smsInfo) {
        this.smsInfo = smsInfo == null ? null : smsInfo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(Date excuteTime) {
        this.excuteTime = excuteTime;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone == null ? null : sendPhone.trim();
    }
}