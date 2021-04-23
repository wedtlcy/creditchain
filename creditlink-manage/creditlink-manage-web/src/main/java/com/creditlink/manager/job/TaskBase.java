package com.creditlink.manager.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditlink.manager.constants.ClientType;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.util.WebUtil;

/**
 * 任务抽象父类
 * TODO:类功能介绍
 * @version 2017年12月18日下午12:57:03
 * @author wuliu
 */
public abstract class TaskBase {
    
    /**
     * 日志对象
     */
    public Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 客户端IP
     */
    public String clientIp = WebUtil.getLocalIp();
    
    /**
     * 客户端类型
     */
    public Integer clientType = ClientType.SM;
    
    /**
     * 页数
     */
    public Integer pageNo = 1;
    
    /**
     * 每页数据条数
     */
    public Integer pageSize = Const.TASK_HANDLE_SIZE;
    
    /**
     * 描述：执行方法
     * 
     * @version 2017年12月18日下午1:01:10
     * @author wuliu
     */
    public abstract void executeInternal();
    
}
