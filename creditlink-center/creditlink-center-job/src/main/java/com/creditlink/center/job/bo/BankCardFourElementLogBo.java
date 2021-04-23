/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job.bo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 银行卡四要素日志BO
 * 
 * @version 2018年1月8日上午11:22:12
 * @author wuliu
 */
public class BankCardFourElementLogBo implements Serializable {
    
    private static final long serialVersionUID = -2129245722154789238L;
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
     * 处理状态(10:未处理;20:处理成功;30:处理失败;)
     */
    private String handlerStatus;
    /**
     * 成功次数
     */
    private Integer success;
    /**
     * 错误次数
     */
    private Integer error;
    
    public Long getAddedTempId() {
        return addedTempId;
    }

    public void setAddedTempId(Long addedTempId) {
        this.addedTempId = addedTempId;
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
    
    public String getIsUse() {
        return isUse;
    }
    
    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getHandlerStatus() {
        return handlerStatus;
    }

    public void setHandlerStatus(String handlerStatus) {
        this.handlerStatus = handlerStatus;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
