/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean.query;

import java.io.Serializable;

/**
 * 产品管理查询参数Bean
 * 
 * @version 2017年12月8日下午12:54:31
 * @author wuliu
 */
public class ProductManagerQueryBean implements Serializable {
    
    private static final long serialVersionUID = -1979063957845724238L;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品标识
     */
    private String productFlag;
    /**
     * 产品类型
     */
    private String productType;
    /**
     * 状态 0:警用；1:启用；
     */
    private String productStatus;
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductFlag() {
        return productFlag;
    }
    
    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public String getProductStatus() {
        return productStatus;
    }
    
    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
    
}
