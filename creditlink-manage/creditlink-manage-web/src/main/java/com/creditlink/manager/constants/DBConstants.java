/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.constants;

/**
 * Constants
 * @version 2017年12月5日下午6:04:47
 * @author wuliu
 */
public class DBConstants {
    
    /**禁用*/
    public static final Integer STATUS_0 = 0;
    /**启用*/
    public static final Integer STATUS_1 = 1;
    
    /**禁用*/
    public static final String STATUS_STR_0 = "0";
    /**启用*/
    public static final String STATUS_STR_1 = "1";
    /**
     * 审核状态(审核状态 01:审核中 02:审核通过 03:审核不通过)
     */
    /** 01:审核中*/
    public static final String AUDIT_STATUS_01 = "01";
    /** 02:审核通过 */
    public static final String AUDIT_STATUS_02 = "02";
    /** 03:审核不通过*/
    public static final String AUDIT_STATUS_03 = "03";
    
    
    /**
     * 用户信息表
     * @version 2017年12月5日下午6:28:58
     * @author wuliu
     */
    public static final class BasUserInfo{
        /**1:锁定*/
        public static final Integer USER_LOCKED_0 = 0;
        /**2:未锁定*/
        public static final Integer USER_LOCKED_1 = 1;
    }
}
