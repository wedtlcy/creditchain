/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行卡四要素索引信息
 * 
 * @version 2018年1月8日下午5:15:16
 * @author wuliu
 */
public class BankCardFourElementEntity implements Serializable{

    private static final long serialVersionUID = 7890713148795138302L;
    
    private Integer id;
    private Long addedTempId;
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
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private String updateUser;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Long getAddedTempId() {
        return addedTempId;
    }
    public void setAddedTempId(Long addedTempId) {
        this.addedTempId = addedTempId;
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
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
}
