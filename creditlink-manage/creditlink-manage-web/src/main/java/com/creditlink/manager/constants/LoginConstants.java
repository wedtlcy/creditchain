/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.constants;

/**
 * 登录常量
 * 
 * @version 2017年12月19日下午6:15:29
 * @author wuliu
 */
public class LoginConstants {
    
    /**登录页面地址*/
    public final static String LOGIN_URL = "/login/loginView.do";
    
    /**用户登录*/
    public final static String LOGIN_USER = "login_user_info";
    
    /**登录用户用户名*/
    public final static String LOGIN_USER_NAME = "username";
    
    /**用户验证码*/
    public final static String YZM_CAPTCHA = "yzm_captcha";
    
    /**验证码生成时间*/
    public final static String YZM_START_TIME = "yzm_start_time";
    
    /**验证码保存时间*/
    public final static long YZM_TIME = 60;
    
    /**登录失败key*/
    public static final String LOGIN_ERROR_KEY = "loginError";
    
    /**用户名或密码不能为空*/
    public static final String NULL_USERNAME_OR_PASSWORD = "用户名或密码不能为空";
    
    /**验证码不能为空*/
    public static final String NULL_VERIFY_CODE = "验证码不能为空";
    
    /**用户名或密码错误*/
    public static final String WRONG_USERNAME_OR_PASSWORD = "用户名或密码错误";
    
    /**验证码错误*/
    public static final String WRONG_VERIFY_CODE = "验证码错误";
    
    /**没有权限页面地址*/
    public final static String NO_AUTH_URL = "/main/noAuth.do";
}
