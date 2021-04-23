/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.member.util;

/**
 * 常量
 * 
 * @version 2017年12月26日上午11:04:51
 * @author wuliu
 */
public class Constants {
    
    /**交易成功*/
    public static final String CODE_Y = "Y";
    /**交易成功描述*/
    public static final String MSG_Y = "交易成功";
    /**交易失败*/
    public static final String CODE_N = "N";
    /**交易失败描述*/
    public static final String MSG_N = "交易失败";
    /**交易失败系统异常*/
    public static final String MSG_N_SYS_ERROR = "系统异常";
    /**缺少必要参数*/
    public static final String MSG_N_PARAMS_ERROR = "缺少必要参数";
    /**产品账户错误*/
    public static final String MSG_N_PRODUCT_ACCOUNT_ERROR = "产品账户错误";
    
    public static final String RSP_BODY = "rspBody"; 
    /**认证库无*/
    public static final String AUTH_CODE_0 = "0";
    public static final String AUTH_MSG_0 = "库无";
    /**认证一致*/
    public static final String AUTH_CODE_1 = "1";
    public static final String AUTH_MSG_1 = "一致";
    /**认证不一致*/
    public static final String AUTH_CODE_2 = "2";
    public static final String AUTH_MSG_2 = "不一致";
}
