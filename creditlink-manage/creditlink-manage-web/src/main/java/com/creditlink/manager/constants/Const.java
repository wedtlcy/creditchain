/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * δ������˾��ʽ����ͬ�⣬�����κθ��ˡ����岻��ʹ�á����ơ��޸Ļ򷢲������.
 * ��Ȩ���������������������޹�˾ http://www.credlink.com/
 */
package com.creditlink.manager.constants;

/**
 * Const
 * 
 * @version 2017年12月5日下午6:13:46
 * @author wuliu
 */
public class Const {
    
    /**
     * ajax(Y:成功；N:失败；)
     */
    /**处理成功*/
    public final static String AJAX_SUCCESS_CODE_Y = "Y";
    public final static String AJAX_SUCCESS_CODE_Y_MSG = "操作成功";
    /**处理失败*/
    public final static String AJAX_ERROR_CODE_N = "N";
    public final static String AJAX_ERROR_CODE_N_MSG = "操作失败";
    /**系统异常*/
    public final static String AJAX_SYS_ERROR_N_MSG = "系统异常";
    
    /**
     * 更新限制条数(1:YES;n:NO)
     */
    /**只能更新一条*/
    public final static Boolean UPDATE_LIMIT_1 = true;
    /**可以更新N条*/
    public final static Boolean UPDATE_LIMIT_N = false;
    /**ids分隔符*/
    public final static String IDS_SEPARATOR = ",";
    
    /** 单个定时任务一次处理数据上限 */
    public static final int TASK_HANDLE_SIZE = 2000;
    
}
