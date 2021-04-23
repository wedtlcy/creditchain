/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean;

import java.io.Serializable;

/**
 * 权限判断beak
 * 
 * @version 2018年1月30日上午9:17:51
 * @author wuliu
 */
public class AuthCheckBean implements Serializable {
    
    private static final long serialVersionUID = 2351437263946826473L;
    
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 操作地址
     */
    private String url;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
}
