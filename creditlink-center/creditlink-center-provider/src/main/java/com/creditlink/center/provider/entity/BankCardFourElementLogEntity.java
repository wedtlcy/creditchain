/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 银行卡四要素认证调用日志
 * 
 * @version 2018年1月2日下午3:31:06
 * @author wuliu
 */
public class BankCardFourElementLogEntity implements Serializable {
    private static final long serialVersionUID = -1915433503121102970L;
    
    private String id;
    /**
     * 索引新增临时表id
     */
    private Long addedTempId;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 联盟成员ID
     */
    private String memberId;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 状态(1:查询成功；2:查询失败)
     */
    private String status;
    /**
     * 1:可用；0:不可用;
     */
    private String isUse;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime;
    
    private String reqSerialNo;
    
    private String orderNo;
    
    public Long getAddedTempId() {
        return addedTempId;
    }

    public void setAddedTempId(Long addedTempId) {
        this.addedTempId = addedTempId;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getBankCard() {
        return bankCard;
    }
    
    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getReqSerialNo() {
        return reqSerialNo;
    }

    public void setReqSerialNo(String reqSerialNo) {
        this.reqSerialNo = reqSerialNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
