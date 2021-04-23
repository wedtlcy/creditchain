/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.member.dao.BankAuthDao;
import com.creditlink.member.entity.BankCardFourElement;
import com.creditlink.member.service.BankAuthService;

/**
 * 银行卡授权service实现
 * 
 * @version 2017年12月26日上午10:48:20
 * @author wuliu
 */
@Service
@Transactional
public class BankAuthServiceImpl implements BankAuthService{

    @Autowired
    private BankAuthDao bankAuthDao;
    
    @Override
    public BankCardFourElement find(BankCardFourElement bankCardFourElement) {
        return bankAuthDao.find(bankCardFourElement);
    }
    
}
