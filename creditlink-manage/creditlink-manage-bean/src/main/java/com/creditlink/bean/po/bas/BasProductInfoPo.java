/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.bas;

import java.math.BigDecimal;

import com.creditlink.bean.po.BasePo;


/**
 * BasProductInfoPo
 * 
 * @version 2017年12月6日下午1:38:26
 * @author wuliu
 */
public class BasProductInfoPo extends BasePo{

    private static final long serialVersionUID = 71704869062265119L;
    
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品特点
     */
    private String productTrait;
    /**
     * 产品备注
     */
    private String productMark;
    /**
     * 产品类型
     */
    private Integer productType;
    /**
     * 产品标识
     */
    private String productFlag;
    /**
     * 产品状态(1:待发布； 2:已发布；)
     */
    private Integer productStatus;
    /**
     * 一次查询费用
     */
    private BigDecimal queryFee;
    /**
     * 数据分享获得的数币
     */
    private BigDecimal coin;
    
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getProductStatus() {
        return productStatus;
    }
    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
    public String getProductTrait() {
        return productTrait;
    }
    public void setProductTrait(String productTrait) {
        this.productTrait = productTrait;
    }
    public String getProductMark() {
        return productMark;
    }
    public void setProductMark(String productMark) {
        this.productMark = productMark;
    }
    public Integer getProductType() {
        return productType;
    }
    public void setProductType(Integer productType) {
        this.productType = productType;
    }
    public String getProductFlag() {
        return productFlag;
    }
    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }
    public BigDecimal getQueryFee() {
        return queryFee;
    }
    public void setQueryFee(BigDecimal queryFee) {
        this.queryFee = queryFee;
    }
    public BigDecimal getCoin() {
        return coin;
    }
    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }
    
}
