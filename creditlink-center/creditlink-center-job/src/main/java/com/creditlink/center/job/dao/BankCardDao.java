/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job.dao;

import java.util.List;
import java.util.Map;

import com.creditlink.center.job.bo.BankCardFourElementLogBo;
import com.creditlink.center.job.entity.bank.BankCardFourElementAddedTempEntity;
import com.creditlink.center.job.entity.bank.BankCardFourElementEntity;
import com.creditlink.center.job.entity.bank.BankCardFourElementLogEntity;

/**
 * BankCardDao
 * @version 2018年1月5日下午4:15:56
 * @author wuliu
 */
public interface BankCardDao {


    /**
     * 更新银行卡四要素次数
     * 
     * @version 2018年1月8日下午2:21:29
     * @author wuliu
     * @param bankCardFourElementLogBo
     * @return
     */
    public int updateBankCardFourCount(BankCardFourElementLogBo bankCardFourElementLogBo);
    
   /**
    * 银行卡四要素日志查询
    * 
    * @version 2018年1月8日下午2:03:11
    * @author wuliu
    * @param bo
    * @return
    */
   public List<BankCardFourElementLogEntity> findBankCardFourLog(BankCardFourElementLogBo bo);

   /**
    * 更新银行四要素日志处理状态
    * 
    * @version 2018年1月8日下午2:35:26
    * @author wuliu
    * @param bankCardFourLogEntity
    * @return
    */
   public int updateBankCardFourLogHandlerstatus(Map<String,Object> map);

   /**
    * 新增移库
    * 
    * @version 2018年1月15日上午8:45:19
    * @author wuliu
    * @param map
    * @return
    */
   public List<BankCardFourElementAddedTempEntity> findFourElementAddTemp(Map<String, Object> map);

   /**
    * 插入索引信息
    * 
    * @version 2018年1月15日上午9:14:06
    * @author wuliu
    * @param fourElementEntity
    */
   public void insertFourElement(BankCardFourElementEntity fourElementEntity);

   /**
    * 查询四要素索引信息
    * 
    * @version 2018年1月15日上午9:33:25
    * @author wuliu
    * @param entity
    * @return
    */
   public BankCardFourElementEntity findFourElement(BankCardFourElementEntity entity);

   /**
    * 银行卡四要素移库完成
    * 
    * @version 2018年1月15日下午12:41:22
    * @author wuliu
    * @param map
    */
   public Long updateFourElementAddedStatus(Map<String, Object> map);

   /**
    * 银行卡四要素修改记录过期
    * 
    * @version 2018年1月16日上午8:48:12
    * @author wuliu
    * @param map
    * @return
    */
   public Long updateFourElementUpdateRecordStatus(Map<String, Object> map);
}
