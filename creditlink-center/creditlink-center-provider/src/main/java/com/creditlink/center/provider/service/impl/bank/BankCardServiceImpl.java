/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.service.impl.bank;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.center.provider.api.consts.Constant;
import com.creditlink.center.provider.api.vo.BankCardFourLogHandleReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourLogHandleResVo;
import com.creditlink.center.provider.dao.BankCardDao;
import com.creditlink.center.provider.entity.BankCardFourElementLogEntity;
import com.creditlink.center.provider.service.bank.BankCardService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * TODO:类功能介绍
 * @version 2018年1月3日下午5:38:18
 * @author wuliu
 */
@Service
@Transactional
public class BankCardServiceImpl implements BankCardService{

    @Autowired
    private BankCardDao bankCardDao;
    
    @SuppressWarnings("serial")
    @Override
    public BankCardFourLogHandleResVo fourelementLog(BankCardFourLogHandleReqVo reqVo) {
        BankCardFourLogHandleResVo resVo = new BankCardFourLogHandleResVo();
        //参数校验
        if(StringUtils.isBlank(reqVo.getBody())){
            resVo.setRetInfo(Constant.CODE_000097, Constant.MSG_000097);
            return resVo;
        }
        Gson gson = new Gson(); 
        String body = reqVo.getBody();
        List<BankCardFourElementLogEntity> list = new ArrayList<BankCardFourElementLogEntity>();
        if(StringUtils.isNotBlank(body)){
            list = gson.fromJson(body,new TypeToken<List<BankCardFourElementLogEntity>>(){}.getType());  
        }
        if(list != null && !list.isEmpty()){
            bankCardDao.insertBatchBankCardFourElementLog(list);
        }
        resVo.setCode(Constant.SUCCESS);
        resVo.setMsg(Constant.SUCCESS_MSG);
        return resVo;
    }
    
}
