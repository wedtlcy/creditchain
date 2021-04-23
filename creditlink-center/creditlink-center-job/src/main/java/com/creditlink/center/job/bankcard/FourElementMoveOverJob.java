/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job.bankcard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditlink.center.job.service.bank.BankCardService;

/**
 * 银行卡四要素移库完成任务(舍弃)
 * 
 * @version 2018年1月15日下午12:33:38
 * @author wuliu
 */
@Component
public class FourElementMoveOverJob {
    
    private static final Logger logger = LoggerFactory.getLogger(FourElementMoveOverJob.class);

    @Autowired
    private BankCardService bankCardService;
    
    public void handler() {
        logger.info("******银行卡四要素移库完成任务开始******");
        try {
            bankCardService.bankCardFourElementMoveOver();
        }
        catch (Throwable e) {
            logger.info("******银行卡四要素移库完成任务系统异常******",e);
        }
        logger.info("******银行卡四要素移库完成任务结束******");
    }
}
