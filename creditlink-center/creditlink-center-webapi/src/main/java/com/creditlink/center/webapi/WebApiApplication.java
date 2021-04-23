/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.webapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//向服务中心注册
@EnableDiscoveryClient
public class WebApiApplication {
    
    private static final Logger log = LoggerFactory.getLogger(WebApiApplication.class);
    
    public static void main(String[] args) {
        log.info("**********中心节点接口中心启动-开始**********");
        try {
            SpringApplication.run(WebApiApplication.class, args);
            log.info("**********中心节点接口中心启动-成功**********");
        }
        catch (Throwable e) {
            log.info("**********中心节点接口中心启动-失败**********");
        }
    }
    
    @Bean//向程序的ioc注入一个bean
    @LoadBalanced//表明这个restRemplate开启负载均衡的功能
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
