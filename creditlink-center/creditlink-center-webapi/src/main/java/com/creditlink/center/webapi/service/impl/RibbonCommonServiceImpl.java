/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.webapi.service.impl;

import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.creditlink.center.provider.api.vo.BaseReqVo;
import com.creditlink.center.webapi.service.RibbonCommonService;

/**
 * RibbonCommonService(ribbon+restTemplate服务调用方式)实现
 * 
 * @version 2018年1月4日下午12:45:07
 * @author wuliu
 */
@Service
public class RibbonCommonServiceImpl implements RibbonCommonService{
    
    private static final Logger log = LoggerFactory.getLogger(RibbonCommonServiceImpl.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public String ribbonPost(BaseReqVo baseReqVo, String url) {
        log.info("请求服务地址:{}", url);
        HttpHeaders headers = new HttpHeaders();
        MediaType type =  MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.set("Connection", "Close");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = JSONObject.fromObject(baseReqVo);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        return result;
    }
    
    @Override
    public String ribbonPost(Map<String, Object> params, String url) {
        log.info("请求服务地址:{}", url);
        HttpHeaders headers = new HttpHeaders();
        MediaType type =  MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.set("Connection", "Close");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject jsonObj = JSONObject.fromObject(params);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonObj.toString(), headers);
        String result = restTemplate.postForObject(url, formEntity, String.class);
        return result;
    }

}
