/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.webapi.service;

/**
 * ConfigService
 * 
 * @version 2018年1月12日下午1:24:06
 * @author wuliu
 */
public interface ConfigService {
    
    /**
     * 获取值
     * 
     * @version 2018年1月12日下午1:24:45
     * @author wuliu
     * @param key
     * @return
     */
    public String getValue(String key);
    
    /**
     * 获取地址
     * 
     * @version 2018年1月12日下午1:24:45
     * @author wuliu
     * @param key
     * @return
     */
    public String getUrl(String keyIp,String keyPath);
}
