/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.webapi.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.creditlink.center.provider.api.consts.BankCardRetCode;
import com.creditlink.center.provider.api.consts.Constant;
import com.creditlink.center.provider.api.vo.BankCardFourElementAddedReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementAddedResVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateResVo;
import com.creditlink.center.provider.api.vo.BankCardFourLogHandleReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourLogHandleResVo;
import com.creditlink.center.webapi.service.ConfigService;
import com.creditlink.center.webapi.service.RibbonCommonService;
import com.creditlink.center.webapi.util.BeanUtil;
import com.creditlink.center.webapi.util.ConstantKey;

/**
 * BankcardController
 * 
 * @version 2018年1月3日下午5:14:39
 * @author wuliu
 */
@RestController
@RequestMapping("/bankcard")
public class BankCardController {
    
    private static final Logger log = LoggerFactory.getLogger(BankCardController.class);
    
    @Autowired
    private RibbonCommonService ribbonCommonService;
    
    @Autowired
    private ConfigService configService;
    
    @RequestMapping(value = "/fourelement/log", method = RequestMethod.POST)
    public Object fourelementLog(HttpServletRequest request, @RequestParam Map<String, Object> map){
        BankCardFourLogHandleResVo resVo = new  BankCardFourLogHandleResVo();
        try {
            BankCardFourLogHandleReqVo reqVo = (BankCardFourLogHandleReqVo) BeanUtil.mapToReqVo(map,BankCardFourLogHandleReqVo.class);
            if(reqVo == null){
                log.error("xxxxxx转换异常");
                resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
                return resVo;
            }
            log.info("银行卡四要素日志处理服务请求参数:{}", reqVo);
            String bankcardFourelementLogUrl = configService.getUrl(ConstantKey.KEY_CREDITLINK_CENTER_PROVIDER_IP,
                    ConstantKey.KEY_BANKCARDFOURELEMENT_LOG_URL);
            String result = ribbonCommonService.ribbonPost(reqVo, bankcardFourelementLogUrl);
            log.info("银行卡四要素日志处理服务请求返回信息:{}", result);
            if(StringUtils.isNotBlank(result)){
                resVo = JSONObject.parseObject(result, BankCardFourLogHandleResVo.class);
            }else{
                resVo.setRetInfo(Constant.SUCCESS, Constant.SUCCESS_MSG);
                return resVo;
            }
        }
        catch (Throwable e) {
            log.error("xxxxxx银行卡四要素日志处理系统异常", e);
            resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
        }
        log.info("银行卡四要素日志处理返回信息:{}", resVo);
        return resVo;
   }
    
    @RequestMapping(value = "/fourelement/added", method = RequestMethod.POST)
    public Object fourelementAdded(HttpServletRequest request, @RequestParam Map<String, Object> map){
        BankCardFourElementAddedResVo resVo = new  BankCardFourElementAddedResVo();
        try {
            BankCardFourElementAddedReqVo reqVo = (BankCardFourElementAddedReqVo) BeanUtil.mapToReqVo(map,BankCardFourElementAddedReqVo.class);
            if(reqVo == null){
                log.error("xxxxxx转换异常");
                resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
                return resVo;
            }
            String pageNo = String.valueOf(map.get("pageNo"));
            try {
                reqVo.setPageNo(Integer.parseInt(pageNo));
            }
            catch (Throwable e) {
                reqVo.setPageNo(1);
            }
            log.info("银行卡四要素新增索引请求参数:{}", reqVo);
            String bankcardFourelementAddedUrl = configService.getUrl(ConstantKey.KEY_CREDITLINK_CENTER_PROVIDER_IP,
                    ConstantKey.KEY_BANKCARDFOURELEMENT_ADDED_URL);
            String result = ribbonCommonService.ribbonPost(reqVo, bankcardFourelementAddedUrl);
            log.info("银行卡四要素新增索引请求返回信息:{}", result);
            if(StringUtils.isNotBlank(result)){
                resVo = JSONObject.parseObject(result, BankCardFourElementAddedResVo.class);
            }else{
                resVo.setTotalPage(0L);
                resVo.setRetInfo(Constant.SUCCESS, Constant.SUCCESS_MSG);
                return resVo;
            }
        }
        catch (Throwable e) {
            log.error("xxxxxx银行卡四要素新增索引系统异常", e);
            resVo.setTotalPage(0L);
            resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
        }
        log.info("银行卡四要素新增索引返回信息:{}", resVo);
        return resVo;
   }
    
    @RequestMapping(value = "/fourelement/update", method = RequestMethod.POST)
    public Object fourelementUpdate(HttpServletRequest request, @RequestParam Map<String, Object> map){
        BankCardFourElementUpdateResVo resVo = new  BankCardFourElementUpdateResVo();
        try {
            BankCardFourElementUpdateReqVo reqVo = (BankCardFourElementUpdateReqVo) BeanUtil.mapToReqVo(map,BankCardFourElementUpdateReqVo.class);
            if(reqVo == null){
                log.error("xxxxxx转换异常");
                resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
                return resVo;
            }
            String pageNo = String.valueOf(map.get("pageNo"));
            try {
                reqVo.setPageNo(Integer.parseInt(pageNo));
            }
            catch (Throwable e) {
                reqVo.setPageNo(1);
            }
            log.info("银行卡四要素修改记录查询索引请求参数:{}", reqVo);
            String bankcardFourelementUpdateUrl = configService.getUrl(ConstantKey.KEY_CREDITLINK_CENTER_PROVIDER_IP,
                    ConstantKey.KEY_BANKCARDFOURELEMENT_UPDATE_URL);
            String result = ribbonCommonService.ribbonPost(reqVo, bankcardFourelementUpdateUrl);
            log.info("银行卡四要素修改记录查询索引请求返回信息:{}", result);
            if(StringUtils.isNotBlank(result)){
                resVo = JSONObject.parseObject(result, BankCardFourElementUpdateResVo.class);
            }else{
                resVo.setTotalPage(0L);
                resVo.setRetInfo(Constant.SUCCESS, Constant.SUCCESS_MSG);
                return resVo;
            }
        }
        catch (Throwable e) {
            log.error("xxxxxx银行卡四要素修改记录查询索引系统异常", e);
            resVo.setTotalPage(0L);
            resVo.setRetInfo(BankCardRetCode.CODE_000099, BankCardRetCode.MSG_000099);
        }
        log.info("银行卡四要素修改记录返回信息:{}", resVo);
        return resVo;
   }
}
