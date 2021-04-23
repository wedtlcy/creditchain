/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


/**
 * 定时任务
 * 
 * @version 2017年12月29日下午2:05:05
 * @author wuliu
 */
@SpringBootApplication
@MapperScan("com.creditlink.center.job.dao")  
@ImportResource("quartz.xml")
public class JobApplication {
    
    private static final Logger Logger = LoggerFactory.getLogger(JobApplication.class);
    
    public static void main(String[] args) {
        Logger.info("**********中心节点定时任务服务启动-开始**********");
        try {
            SpringApplication.run(JobApplication.class, args);
            Logger.info("**********中心节点定时任务服务启动-成功**********");
        }
        catch (Throwable e) {
            Logger.info("**********中心节点定时任务服务启动-失败**********");
        }
    }
    
    
}
