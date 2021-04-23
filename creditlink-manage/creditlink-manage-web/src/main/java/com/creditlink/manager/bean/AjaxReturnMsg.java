/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean;

import java.io.Serializable;

/**
 * ajax请求返回数据
 * 
 * @version 2017年12月8日下午3:28:25
 * @author wuliu
 */
public class AjaxReturnMsg implements Serializable{
    
    private static final long serialVersionUID = -1180291705444885523L;
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回描述
     */
    private String msg;
    /**
     * 对象
     */
    private Object result;
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
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }
    
}
