/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.member.service;

import com.creditlink.member.entity.BankCardFourElement;

/**
 * 银行卡授权service
 * 
 * @version 2017年12月26日上午10:47:53
 * @author wuliu
 */
public interface BankAuthService {
    
    public BankCardFourElement find(BankCardFourElement bankCardFourElement); 
}
