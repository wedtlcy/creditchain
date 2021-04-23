/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.index;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.index.BankCardFourElementPo;

/**
 * BankCardDao
 * 
 * @version 2018年1月19日下午12:31:15
 * @author wuliu
 */
public interface BankCardFourElementDao {

    /**
     * 查询四要素索引信息
     * 
     * @version 2018年1月22日上午10:10:04
     * @author wuliu
     * @param addedTempPo
     * @return
     */
    public BankCardFourElementPo find(BankCardFourElementPo entity);
    
   /**
    * 更新银行卡四要素索引状态
    * 
    * @version 2018年1月23日下午3:07:09
    * @author wuliu
    * @param bo
    * @return
    */
   public int updateStatusBatch(UpdateStatusBo bo);
    
   /**
    * 查询四要素索引数量
    * 
    * @version 2018年1月23日上午11:36:47
    * @author wuliu
    * @param map
    * @return
    */
   public Long fourElementQueryCount(Map<String, Object> map);

   /**
    * 查询四要素索引
    * 
    * @version 2018年1月23日上午11:36:47
    * @author wuliu
    * @param map
    * @return
    */
   public List<Map<String, Object>> fourElementQuery(Map<String, Object> map);
   
   /**
    * 根据ID更新
    * 
    * @version 2018年1月23日上午10:42:13
    * @author wuliu
    * @param entity
    * @return
    */
   public int update(BankCardFourElementPo entity);
}
