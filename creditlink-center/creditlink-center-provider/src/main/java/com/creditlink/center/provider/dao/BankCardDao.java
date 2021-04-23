/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.dao;

import java.util.List;
import java.util.Map;

import com.creditlink.center.provider.entity.BankCardFourElementLogEntity;

/**
 * 银行卡dao
 * 
 * @version 2018年1月4日上午8:47:57
 * @author wuliu
 */
public interface BankCardDao {
    
    /***********************************银行卡四要素start***********************************/
    /**
     * 银行卡四要素日志(单条)
     * 
     * @version 2018年1月4日上午8:51:15
     * @author wuliu
     * @param entity
     */
    public void insertBankCardFourElementLog(BankCardFourElementLogEntity entity);
    
    /**
     * 银行卡四要素日志(批量)
     * 
     * @version 2018年1月4日上午8:51:15
     * @author wuliu
     * @param entity
     */
    public void insertBatchBankCardFourElementLog(List<BankCardFourElementLogEntity> list);
    
    /**
     * 当日新增四要素数量
     * 
     * @version 2018年1月8日下午5:23:07
     * @author wuliu
     * @param map
     * @return
     */
    public Long addedBankCardFourElementCount(Map<String,Object> map);
    
    /**
     * 当日新增四要素
     * 
     * @version 2018年1月8日下午5:23:07
     * @author wuliu
     * @param map
     * @return
     */
    public List<Map<String,Object>> addedBankCardFourElement(Map<String,Object> map);
    
    /**
     * 当日新增四要素(全部)
     * 
     * @version 2018年1月8日下午5:23:07
     * @author wuliu
     * @param map
     * @return
     */
    public List<Map<String,Object>> addedBankCardFourElementAll(Map<String,Object> map);

    /**
     * 银行卡四要素修改记录数量
     * 
     * @version 2018年1月10日下午1:46:49
     * @author wuliu
     * @param params
     * @return
     */
    public Long bankCardFourElementUpdateCount(Map<String, Object> params);

    /**
     * 银行卡四要素修改记录
     * 
     * @version 2018年1月10日下午1:47:18
     * @author wuliu
     * @param params
     * @return
     */
    public List<Map<String, Object>> bankCardFourElementUpdate(Map<String, Object> params);
    
    /***********************************银行卡四要素end***********************************/
}
