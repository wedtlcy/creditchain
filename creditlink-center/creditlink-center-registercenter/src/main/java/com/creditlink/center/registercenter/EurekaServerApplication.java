/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.registercenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//一个注解@EnableEurekaServer代表启动一个服务注册中心
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
    
    private static final Logger log = LoggerFactory.getLogger(EurekaServerApplication.class);
    
    public static void main(String[] args) {
        log.info("**********中心节点注册中心启动-开始**********");
        try {
            SpringApplication.run(EurekaServerApplication.class, args);
            log.info("**********中心节点注册中心启动-成功**********");
        }
        catch (Throwable e) {
            log.info("**********中心节点注册中心启动-失败**********");
        }
    }
}
