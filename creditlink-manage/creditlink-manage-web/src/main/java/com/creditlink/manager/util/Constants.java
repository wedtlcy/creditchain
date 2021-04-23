/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.util;

public class Constants {
    public static final int EXPOT_EXCEL_MAX_PAGE_SIZE = 60000;// 导出excel一个页签最大记录数
    
    /**0：不可用*/
    public static final String STATUS_0 = "0";
    /**1：可用*/
    public static final String STATUS_1 = "1";
    /**2：移库完成*/
    public static final String STATUS_2 = "2";
    /**查询全部*/
    public static final String STATUS_ALL = "-1";
    
    /**
     * (银行卡四要素修改记录状态)10:未处理; 20:已处理；
     */
    /**10:未处理*/
    public static final String STATUS_10 = "10";
    /**20:已处理*/
    public static final String STATUS_20 = "20";
    
    /**
     * 是否联盟成员((0:否；1:是；))
     */
    /**0:否*/
    public static final Integer IS_MEMBER_0 = 0;
    /**1:是*/
    public static final Integer IS_MEMBER_1 = 1;
}
