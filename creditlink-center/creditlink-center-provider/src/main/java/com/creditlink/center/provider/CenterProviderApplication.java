/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;


/**
 * CenterProviderApplication
 * @version 2018年1月3日下午5:35:48
 * @author wuliu
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.creditlink.center.provider.dao")  
@RestController
public class CenterProviderApplication {
    
    private static final Logger log = LoggerFactory.getLogger(CenterProviderApplication.class);
    
    public static void main(String[] args) {
        log.info("**********中心节点服务提供者启动-开始**********");
        try {
            SpringApplication.run(CenterProviderApplication.class, args);
            log.info("**********中心节点服务提供者启动-成功**********");
        }
        catch (Throwable e) {
            log.info("**********中心节点服务提供者启动-失败**********");
        }
    }
    
}
