/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.bas;

import java.util.Date;

import com.creditlink.bean.po.BasePo;

/**
 * 合同表
 * 
 * @version 2017年12月6日下午3:37:22
 * @author wuliu
 */
public class BasContractInfoPo extends BasePo {
    
    private static final long serialVersionUID = -3405295490747416310L;
    
    /**
     * 合同ID
     */
    private Long id;
    /**
     * 合同编号
     */
    private String contractCode;
    /**
     * 合同名称
     */
    private String contractName;
    /**
     * 合同类别
     */
    private String contractType;
    /**
     * 公司类型
     */
    private String contractCategory;
    /**
     * 合作类型
     */
    private String custType;
    /**
     * 客户ID
     */
    private Long userId;
    /**
     * 合同状态(0：失效 1：生效 )
     */
    private String status;
    /**
     * 签订日期
     */
    private Date signDate;
    /**
     * 生效日期
     */
    private Date effDate;
    /**
     * 失效日期
     */
    private Date expDate;
    /**
     * 签订日期
     */
    private String signDateStr;
    /**
     * 生效日期
     */
    private String effDateStr;
    /**
     * 失效日期
     */
    private String expDateStr;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContractCode() {
        return contractCode;
    }
    
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }
    
    public String getContractName() {
        return contractName;
    }
    
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
    
    public String getContractType() {
        return contractType;
    }
    
    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
    
    public String getContractCategory() {
        return contractCategory;
    }
    
    public void setContractCategory(String contractCategory) {
        this.contractCategory = contractCategory;
    }
    
    public String getCustType() {
        return custType;
    }
    
    public void setCustType(String custType) {
        this.custType = custType;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Date getSignDate() {
        return signDate;
    }
    
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
    
    public Date getEffDate() {
        return effDate;
    }
    
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
    
    public Date getExpDate() {
        return expDate;
    }
    
    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getSignDateStr() {
        return signDateStr;
    }

    public void setSignDateStr(String signDateStr) {
        this.signDateStr = signDateStr;
    }

    public String getEffDateStr() {
        return effDateStr;
    }

    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }

    public String getExpDateStr() {
        return expDateStr;
    }

    public void setExpDateStr(String expDateStr) {
        this.expDateStr = expDateStr;
    }
    
}
