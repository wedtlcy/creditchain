/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.service.bank;

import com.creditlink.center.provider.api.vo.BankCardFourLogHandleReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourLogHandleResVo;

/**
 * BankCardService
 * 
 * @version 2018年1月3日下午5:37:49
 * @author wuliu
 */
public interface BankCardService {

    /**
     * 银行卡四要素认证日志处理(分节点日志-中心节点)
     * 
     * @version 2018年1月4日上午8:46:58
     * @author wuliu
     * @param reqVo
     * @return
     */
   public BankCardFourLogHandleResVo fourelementLog(BankCardFourLogHandleReqVo reqVo);
    
}
