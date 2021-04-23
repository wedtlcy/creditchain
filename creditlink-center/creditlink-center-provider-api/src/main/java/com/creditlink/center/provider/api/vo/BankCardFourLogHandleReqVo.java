/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api.vo;


/**
 * BankCardFourLogHandleReqVo
 * 
 * @version 2018年1月4日上午8:38:56
 * @author wuliu
 */
public class BankCardFourLogHandleReqVo extends BaseReqVo{

    private static final long serialVersionUID = -5804627264396006998L;
    
    /**
     * 日志内容
     */
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
}
