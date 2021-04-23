/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.index;

import com.creditlink.bean.po.BasePo;

/**
 * BankCardFourElementUpdateRecordPo
 * 
 * @version 2018年1月19日下午12:20:43
 * @author wuliu
 */
public class BankCardFourElementUpdateRecordPo extends BasePo {
    
    private static final long serialVersionUID = 1474674027834029456L;
    private Long id;
    /**
     * 四要素索引表ID
     */
    private Long bankCardId;
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
    
    public Long getBankCardId() {
        return bankCardId;
    }
    
    public void setBankCardId(Long bankCardId) {
        this.bankCardId = bankCardId;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
}
