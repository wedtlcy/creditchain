/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.creditlink.center.provider.api.consts.BankCardRetCode;
import com.creditlink.center.provider.api.vo.BankCardFourElementAddedReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementAddedResVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateResVo;
import com.creditlink.center.provider.api.vo.BankCardFourLogHandleReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourLogHandleResVo;
import com.creditlink.center.provider.service.bank.BankCardFourElementService;
import com.creditlink.center.provider.service.bank.BankCardService;



/**
 * 银行卡服务
 * 
 * @version 2017年12月29日下午2:36:11
 * @author wuliu
 */
@RestController
@RequestMapping("/bankcard")
public class BankCardApi {
    
    @Autowired
    private BankCardService bankCardService;
    @Autowired
    private BankCardFourElementService bankCardFourElementService;
    
    private static final Logger log = LoggerFactory.getLogger(BankCardApi.class);
    
    @RequestMapping(value = "/fourelement/log", method = RequestMethod.POST)
    public BankCardFourLogHandleResVo fourelementLog(@RequestBody BankCardFourLogHandleReqVo reqVo) {
        BankCardFourLogHandleResVo resVo = new BankCardFourLogHandleResVo();
        log.info("银行卡四要素日志处理服务请求参数:{}",reqVo);
        try {
            resVo = bankCardService.fourelementLog(reqVo);
        }
        catch (Exception e) {
            log.error("xxxxxx系统异常",e);
            resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
        }
        log.info("银行卡四要素日志处理返回信息:{}",resVo);
        return resVo;
    }
    
    @RequestMapping(value = "/fourelement/added", method = RequestMethod.POST)
    public BankCardFourElementAddedResVo fourelementAdded(@RequestBody BankCardFourElementAddedReqVo reqVo) {
        BankCardFourElementAddedResVo resVo = new BankCardFourElementAddedResVo();
        log.info("银行卡四要素新增服务请求参数:{}",reqVo);
        try {
            if(reqVo.getPageNo() == null || reqVo.getPageNo() <= 0){
                reqVo.setPageNo(1);
            }
            resVo = bankCardFourElementService.bankCardFourElementAdded(reqVo);
        }
        catch (Exception e) {
            log.error("xxxxxx系统异常",e);
            resVo.setTotalPage(0L);
            resVo.setData(new ArrayList<Map<String,Object>>());
            resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
        }
        log.info("银行卡四要素新增服务请求返回信息:{}",resVo);
        return resVo;
    }
    
    @RequestMapping(value = "/fourelement/update", method = RequestMethod.POST)
    public BankCardFourElementUpdateResVo fourelementUpdate(@RequestBody BankCardFourElementUpdateReqVo reqVo) {
        BankCardFourElementUpdateResVo resVo = new BankCardFourElementUpdateResVo();
        log.info("银行卡四要素修改记录查询服务请求参数:{}",reqVo);
        try {
            if(reqVo.getPageNo() == null || reqVo.getPageNo() <= 0){
                reqVo.setPageNo(1);
            }
            resVo = bankCardFourElementService.bankCardFourElementUpdate(reqVo);
        }
        catch (Exception e) {
            log.error("xxxxxx系统异常",e);
            resVo.setTotalPage(0L);
            resVo.setData(new ArrayList<Map<String,Object>>());
            resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
        }
        log.info("银行卡四要素修改记录查询服务请求返回信息:{}",resVo);
        return resVo;
    }
}
