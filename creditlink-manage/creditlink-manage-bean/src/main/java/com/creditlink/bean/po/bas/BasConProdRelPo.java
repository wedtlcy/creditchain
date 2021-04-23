/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.bas;

import com.creditlink.bean.po.BasePo;

/**
 * 合同产品关系表
 * 
 * @version 2017年12月6日下午3:38:10
 * @author wuliu
 */
public class BasConProdRelPo extends BasePo {
    
    private static final long serialVersionUID = 9103530459532637972L;
    
    private Long id;
    /**
     * 产品ID
     */
    private Long prodId;
    /**
     * 合同ID
     */
    private Long contractId;
    /**
     * 状态
     */
    private Integer status;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getProdId() {
        return prodId;
    }
    
    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }
    
    public Long getContractId() {
        return contractId;
    }
    
    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
