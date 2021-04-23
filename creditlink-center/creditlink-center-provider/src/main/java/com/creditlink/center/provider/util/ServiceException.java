/*
 * Copyright (C) 2015 ShenZhen xinguodu Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳合时代金融服务有限公司 www.xinguodu.com.
 */
package com.creditlink.center.provider.util;

/**
 * Service层异常定义
 * 
 * @version 2017年11月22日上午10:00:01
 * @author wuliu
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = -7095405321885617911L;
    
    private String code;
    private String msg;
    
    public ServiceException(String code, String msg) {
        super("Service层异常: code = " + code + ", msg = " + msg);
        this.code = code;
        this.msg = msg;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    @Override
    public String toString() {
        return "Service层异常: code = " + this.code + ", msg = " + this.msg;
    }
    
}
