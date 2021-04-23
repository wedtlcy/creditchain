/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job.entity.bank;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 银行卡四要素认证全局索引表
 * 
 * @version 2018年1月2日上午10:49:07
 * @author wuliu
 */
public class BankCardFourElementEntity implements Serializable {
    
    private static final long serialVersionUID = 7313037277216089174L;

    private Long id;
    
    private Long addedTempId;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 联盟成员ID
     */
    private Long memberId;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 请求地址
     */
    private String path;
    /**
     * 请求IP
     */
    private String ip;
    /**
     * 被查询次数
     */
    private Long count;
    /**
     * 成功次数
     */
    private Long success;
    /**
     * 错误次数
     */
    private Long error;
    /**
     * 0:禁用；1启用；
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddedTempId() {
        return addedTempId;
    }

    public void setAddedTempId(Long addedTempId) {
        this.addedTempId = addedTempId;
    }

    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public Long getMemberId() {
        return memberId;
    }
    
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    
    public String getBankCard() {
        return bankCard;
    }
    
    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
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
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getCreateUser() {
        return createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public String getUpdateUser() {
        return updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
