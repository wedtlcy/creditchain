/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean.query;

import java.io.Serializable;

/**
 * 合同产品关联
 * 
 * @version 2018年1月31日上午11:02:28
 * @author wuliu
 */
public class ContractProductManagerQueryBean implements Serializable {
    
    private static final long serialVersionUID = -6240463983922087540L;
    /**
     * 合同ID
     */
    private Long contractId;
    /**
     * 产品名称
     */
    private String productName;
    
    public Long getContractId() {
        return contractId;
    }
    
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
}
