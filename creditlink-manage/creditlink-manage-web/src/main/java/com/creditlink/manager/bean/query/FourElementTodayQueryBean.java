/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean.query;

import java.io.Serializable;

/**
 * 菜单管理查询参数Bean
 * 
 * @version 2017年12月8日下午12:54:31
 * @author wuliu
 */
public class FourElementTodayQueryBean implements Serializable{

    private static final long serialVersionUID = 3123165605538822134L;
    /**
     * 联盟成员编号
     */
    private Long memberId;
    /**
     * 菜单名称
     */
    private String bankCard;
    /**
     * 状态 0:警用；1:启用；
     */
    private String status;
    
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
