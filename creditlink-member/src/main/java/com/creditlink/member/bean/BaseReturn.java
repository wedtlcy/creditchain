/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.member.bean;

import java.io.Serializable;

/**
 * 返回基类
 * 
 * @version 2017年12月26日上午11:04:18
 * @author wuliu
 */
public class BaseReturn implements Serializable{

    private static final long serialVersionUID = 2966859782485789747L;
    
    /**
     * 返回码
     */
    public String code;
    /**
     * 返回描述
     */
    public String msg;
    /**
     * 返回流水
     */
    public String orderNo;
    /**
     * 返回详细数据
     */
    public Object data;
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
    public String getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    
}
