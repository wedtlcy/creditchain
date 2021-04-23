/*
 * Copyright (C) 2015 ShenZhen xinguodu Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api.consts;

/**
 * 常量
 * 
 * @version 2017年3月23日上午11:21:36
 * @author wuliu
 */
public class Constant {
    
   public static final String RETURN_CODE_KEY = "code";
   public static final String RETURN_MSG_KEY = "msg";
   
   /**查询成功*/
   public static final String SUCCESS = "000000";
   /**查询成功描述*/
   public static final String SUCCESS_MSG = "交易成功";
   /**没有权限*/
   public static final String CODE_000001 = "000001";
   /**没有权限*/
   public static final String MSG_000001 = "没有权限";
   /**缺少必要参数*/
   public static final String CODE_000097 = "000097";
   /**缺少必要参数描述*/
   public static final String MSG_000097 = "交易失败";
   /**交易失败*/
   public static final String CODE_000098 = "000098";
   /**交易失败描述*/
   public static final String MSG_000098 = "交易失败";
   /**系统异常*/
   public static final String CODE_000099 = "000099";
   /**系统异常描述*/
   public static final String MSG_000099 = "系统异常";
   
   
   /**可以使用(有权限)*/
   public static final String  ISLIMIT_Y = "Y";
   /**不可以使用(没有权限)*/
   public static final String ISLIMIT_N = "N";
   
   /**查询成功*/
   public static final String SUCCESS_1 = "1";
   /**查询失败*/
   public static final String FAIL_2 = "2";
   
}
