/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api.vo;


/**
 * AuthReqVo
 * 
 * @version 2018年1月2日下午7:04:41
 * @author wuliu
 */
public class AuthReqVo extends BaseReqVo{
    
    private static final long serialVersionUID = 1431944002594690668L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 产品密钥
     */
    private String productPrivateKey;
    /**
     * 产品标识
     */
    private String productFlag;
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getProductPrivateKey() {
        return productPrivateKey;
    }
    public void setProductPrivateKey(String productPrivateKey) {
        this.productPrivateKey = productPrivateKey;
    }
    public String getProductFlag() {
        return productFlag;
    }
    public void setProductFlag(String productFlag) {
        this.productFlag = productFlag;
    }
    
}
