package com.std.account.dto.req;

import java.util.Date;

public class XN7020106Req {
    private String parentCode;

    // 编号
    private String code;

    // 标题
    private String title;

    // 内容
    private String content;

    // 序号
    private String orderNo;

    // 父编号
    private String artUrl;

    // 状态
    private String status;

    // 创建者
    private String creator;

    // 创建者
    private Date createDatetime;

    // 更新者
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 发布者
    private String publisher;

    // 发布时间
    private String publisherDatetime;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getArtUrl() {
        return artUrl;
    }

    public void setArtUrl(String artUrl) {
        this.artUrl = artUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherDatetime() {
        return publisherDatetime;
    }

    public void setPublisherDatetime(String publisherDatetime) {
        this.publisherDatetime = publisherDatetime;
    }

}
