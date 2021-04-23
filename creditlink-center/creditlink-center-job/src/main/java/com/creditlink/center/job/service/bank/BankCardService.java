/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job.service.bank;

/**
 * BankCardService
 * 
 * @version 2018年1月5日下午4:21:43
 * @author wuliu
 */
public interface BankCardService {

    /**
     * 银行卡四要素日志处理
     * 
     * @version 2018年1月8日上午9:13:54
     * @author wuliu
     */
    public void bankCardFourElementLog();

    /**
     * 银行卡四要素索引移库处理
     * 
     * @version 2018年1月15日上午8:34:24
     * @author wuliu
     */
    public void bankCardFourElementAddOverDue();
    
    /**
     * 银行卡四要素移库完成任务
     * 
     * @version 2018年1月15日下午12:34:48
     * @author wuliu
     */
    public void bankCardFourElementMoveOver();
    
    /**
     * 银行卡四要素修改记录过期处理
     * 
     * @version 2018年1月16日上午8:42:49
     * @author wuliu
     */
    public void bankCardFourElementUpdateOverDue();
}
