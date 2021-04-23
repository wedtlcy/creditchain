/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.index;

import com.creditlink.bean.po.BasePo;

/**
 * BankCardFourElementAddedTempPo
 * 
 * @version 2018年1月19日下午12:26:40
 * @author wuliu
 */
public class BankCardFourElementAddedTempPo extends BasePo{

    private static final long serialVersionUID = 5792648284200429375L;
    
    private Long id;
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
     * status
     */
    private String status;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
