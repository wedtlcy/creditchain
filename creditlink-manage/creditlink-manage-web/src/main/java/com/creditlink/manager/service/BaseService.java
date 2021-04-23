/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service;




/**
 * BaseService
 * 
 * @version 2017年12月8日上午10:38:15
 * @author wuliu
 */
public interface BaseService<T> {
    
    /**
     * 查询(单条单表)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public T find(T entity);
    
    /**
     * 添加
     * 
     * @version 2017年12月8日下午4:32:47
     * @author wuliu
     * @param entity
     */
    public void insert(T entity);
    
}
