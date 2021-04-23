/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.webapi.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.creditlink.center.webapi.service.ConfigService;

/**
 * ConfigServiceImpl
 * 
 * @version 2018年1月12日下午1:25:40
 * @author wuliu
 */
@Service
public class ConfigServiceImpl implements ConfigService{
    
    private static final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);

    @Autowired
    private Environment environment;
    
    @Override
    public String getValue(String key) {
        log.info("获取的键:{}", key);
        if(StringUtils.isBlank(key)){
            return null;
        }
        String value =  environment.getProperty(key);
        if(StringUtils.isNotBlank(value)){
            return value.trim();
        }else{
            return value;
        }
    }

    @Override
    public String getUrl(String keyIp, String keyPath) {
        log.info("获取的键keyIp:{},keyPath:{}", keyIp, keyPath);
        if(StringUtils.isBlank(keyIp) || StringUtils.isBlank(keyPath)){
            return null;
        }
        String valueIp =  environment.getProperty(keyIp);
        String valuePath =  environment.getProperty(keyPath);
        if(StringUtils.isNotBlank(valueIp)){
            valueIp = valueIp.trim();
        }else{
            valueIp = "";
        }
        if(StringUtils.isNotBlank(valuePath)){
            valuePath = valuePath.trim();
        }else{
            valuePath = "";
        }
        return valueIp + valuePath;
    }
    
}
