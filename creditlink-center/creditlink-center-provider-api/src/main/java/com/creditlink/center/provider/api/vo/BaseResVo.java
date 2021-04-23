/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api.vo;


/**
 * BaseResVo
 * 
 * @version 2018年1月10日上午8:35:41
 * @author wuliu
 */
public class BaseResVo extends BaseVo{
    
    private static final long serialVersionUID = -2949810257391437594L;
    /**
     * 返回代码
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;
    
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
    
    /**
     * 设置返回信息
     * 
     * @version 2018年1月10日上午8:39:16
     * @author wuliu
     * @param code
     * @param msg
     */
    public void setRetInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
}
