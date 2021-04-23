/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.member.bean;

import java.io.Serializable;

/**
 * 银行卡四要素认证返回结果信息
 * 
 * @version 2017年12月26日上午11:28:20
 * @author wuliu
 */
public class BankAuthFourElementData implements Serializable{
    
    private static final long serialVersionUID = 1614522076800563037L;
    /**
     * 认证结果
     */
    public String result;
    
    public String getResult() {
        return result;
    }
    
    public void setResult(String result) {
        this.result = result;
    }
}
