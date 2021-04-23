/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.po;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 银行卡四要素新增PO(分节点db四要素相关字段)
 * 
 * @version 2018年1月8日下午6:17:53
 * @author wuliu
 */
public class BankCardFourElementAddedPo implements Serializable {
    
    private static final long serialVersionUID = 946980494064950027L;
    
    private String bankCardId;
    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 成员ID
     */
    private Integer memberId;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * ip
     */
    private String ip;
    /**
     * path
     */
    private String path;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 查询次数
     */
    private Long count;
    /**
     * 成功次数
     */
    private Long success;
    /**
     * 失败次数
     */
    private Long error;
    /**
     * 0:禁用；1启用；
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    
    public String getBankCardId() {
        return bankCardId;
    }
    
    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }
    
    public Integer getProductId() {
        return productId;
    }
    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    
    public Integer getMemberId() {
        return memberId;
    }
    
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    
    public String getBankCard() {
        return bankCard;
    }
    
    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    
    public Long getCount() {
        return count;
    }
    
    public void setCount(Long count) {
        this.count = count;
    }
    
    public Long getSuccess() {
        return success;
    }
    
    public void setSuccess(Long success) {
        this.success = success;
    }
    
    public Long getError() {
        return error;
    }
    
    public void setError(Long error) {
        this.error = error;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
