/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.member.dao;

import com.creditlink.member.entity.BankCardFourElement;

/**
 * 银行卡授权
 * 
 * @version 2017年12月26日上午10:49:00
 * @author wuliu
 */
public interface  BankAuthDao {

    /**
     * 查询银行卡四要素信息
     * 
     * @version 2017年12月26日上午11:17:39
     * @author wuliu
     * @param bankCardFourElement
     * @return
     */
    public BankCardFourElement find(BankCardFourElement bankCardFourElement);
    
}
