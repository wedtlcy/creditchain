/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.webapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creditlink.center.provider.api.consts.AuthRetCode;
import com.creditlink.center.provider.api.consts.Constant;
import com.creditlink.center.provider.api.vo.AuthResVo;
import com.creditlink.center.webapi.service.ConfigService;
import com.creditlink.center.webapi.service.RibbonCommonService;
import com.creditlink.center.webapi.util.ConstantKey;


/**
 * UserController
 * @version 2018年1月2日下午7:08:04
 * @author wuliu
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private RibbonCommonService ribbonCommonService;
    @Autowired
    private ConfigService configService;
    
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Object auth(@RequestParam String username, @RequestParam String productPrivateKey, @RequestParam String productFlag){
        AuthResVo resVo = new AuthResVo();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("username", username);
            params.put("productPrivateKey", productPrivateKey);
            params.put("productFlag", productFlag); 
            String authUrl = configService.getUrl(ConstantKey.KEY_CREDITLINK_CENTER_PROVIDER_IP, ConstantKey.KEY_AUTH_URL);
            String result = ribbonCommonService.ribbonPost(params, authUrl);
            if(StringUtils.isNotBlank(result)){
                resVo = com.alibaba.fastjson.JSONObject.parseObject(result, AuthResVo.class);
            }else{
                resVo.setIsLimit(Constant.ISLIMIT_N);
                resVo.setRetInfo(AuthRetCode.CODE_000098, AuthRetCode.CODE_000098);
                return resVo;
            }
            resVo.setIsLimit(Constant.ISLIMIT_Y);
            resVo.setRetInfo(AuthRetCode.CODE_000000, AuthRetCode.CODE_000000);
        }
        catch (Throwable e) {
            log.error("xxxxxx系统异常", e);
            resVo.setIsLimit(Constant.ISLIMIT_N);
            resVo.setRetInfo(AuthRetCode.CODE_000099, AuthRetCode.CODE_000099);
            return resVo;
        }
        log.info("授权接口判断返回信息:{}", resVo);
        return resVo;
   }
}
