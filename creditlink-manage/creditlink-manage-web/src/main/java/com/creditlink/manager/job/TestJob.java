/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * JOB测试
 * 
 * @version 2017年12月18日下午1:02:19
 * @author wuliu
 */
@Component("testJob")
public class TestJob {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public void run() {
        logger.info(">>>>>>>>>>xxxxxx定时任务开始>>>>>>>>>>>>>>");
        long startTime = System.currentTimeMillis(); // 获取开始时间
        try {
           System.out.println("----------------1------------------");
        }
        catch (Throwable e) {
            logger.error("xxxxxx定时任务异常:", e);
        }
        finally {
            long endTime = System.currentTimeMillis(); // 获取结束时间
            logger.info("xxxxxx定时任务运行时间：{}ms", endTime - startTime);
        }
        logger.info("<<<<<<<<<<xxxxxx定时任务结束<<<<<<<<<<<<<<<");
    }
}
