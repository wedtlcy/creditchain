/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.service.bank;

import com.creditlink.center.provider.api.vo.BankCardFourElementAddedReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementAddedResVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateResVo;


/**
 * 银行卡四要素service
 * 
 * @version 2018年1月8日下午5:14:13
 * @author wuliu
 */
public interface BankCardFourElementService {
    
    /**
     * 银行卡四要素新增(昨日)
     * 
     * @version 2018年1月8日下午5:32:27
     * @author wuliu
     * @param req
     * @return
     */
    public BankCardFourElementAddedResVo bankCardFourElementAdded(BankCardFourElementAddedReqVo reqVo);

    /**
     * 银行卡四要素修改
     * 
     * @version 2018年1月8日下午5:32:27
     * @author wuliu
     * @param req
     * @return
     */
    public BankCardFourElementUpdateResVo bankCardFourElementUpdate(BankCardFourElementUpdateReqVo reqVo);
}
