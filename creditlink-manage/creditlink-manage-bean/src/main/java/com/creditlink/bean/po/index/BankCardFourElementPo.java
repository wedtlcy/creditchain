/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.index;

import com.creditlink.bean.po.BasePo;

/**
 * BankCardFourElementPo
 * 
 * @version 2018年1月19日下午12:20:43
 * @author wuliu
 */
public class BankCardFourElementPo extends BasePo{

    private static final long serialVersionUID = -4552750883576816612L;
    
    private Long id;
    /**
     * 新增临时表ID
     */
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
     * IP
     */
    private String ip;
    /**
     * PATH
     */
    private String path;
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
     * status
     */
    private String status;
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
    
}
