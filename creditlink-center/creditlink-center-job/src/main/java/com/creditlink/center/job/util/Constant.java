/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job.util;

/**
 * 常量
 * 
 * @version 2018年1月5日下午2:51:46
 * @author wuliu
 */
public class Constant {
    
    /** 单个定时任务一次处理数据上限 */
    public static final int TASK_HANDLE_SIZE = 20;
    /**银行卡四要素单次日志处理条数*/
    public static final Integer BANKCARD_FOURELEMENT_LOGHANDLER_10 = 10;
    
    /**
     * 1:可用；0:不可用;
     */
    /**可用*/
    public static final String IS_USE_1 = "1";
    /**不可用*/
    public static final String IS_USE_0 = "0";
    /**
     * 处理状态(10:未处理;20:处理成功;30:处理失败;)
     */
    /**10:未处理*/
    public static final String HANDLER_STATUS_10 = "10";
    /**20:处理成功*/
    public static final String HANDLER_STATUS_20 = "20";
    /**30:处理失败*/
    public static final String HANDLER_STATUS_30 = "30";
    
    /**
     * 状态(1:查询成功；2:查询失败)
     */
    /**1:查询成功*/
    public static final String BANKCARD_FOUR_STATUS_1 = "1";
    /**2:查询失败*/
    public static final String BANKCARD_FOUR_STATUS_2 = "2";
    
    /**0：不可用*/
    public static final String STATUS_0 = "0";
    /**1：可用*/
    public static final String STATUS_1 = "1";
    /**2：移库完成*/
    public static final String STATUS_2 = "2";
    
    /**
     * 状态(10:未处理; 20:已处理；)
     */
    /**10:未处理*/
    public static final String UPDATE_RECORD_STATUS_10 = "10";
    /**20:已处理*/
    public static final String UPDATE_RECORD_STATUS_20 = "20";
}
