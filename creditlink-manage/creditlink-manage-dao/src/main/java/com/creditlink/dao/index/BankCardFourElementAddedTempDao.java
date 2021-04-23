/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.index;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.index.BankCardFourElementAddedTempPo;

/**
 * BankCardFourElementAddedTempDao
 * @version 2018年1月23日下午4:16:58
 * @author wuliu
 */
public interface BankCardFourElementAddedTempDao {
    
    /**
     * 批量插入
     * 
     * @version 2018年1月22日上午9:13:26
     * @author wuliu
     * @param addedTempPos
     */
   public void insertBatch(List<BankCardFourElementAddedTempPo> addedTempPos);
   
   /**
    * 查找
    * 
    * @version 2018年1月22日上午10:10:04
    * @author wuliu
    * @param addedTempPo
    * @return
    */
   public BankCardFourElementAddedTempPo find(BankCardFourElementAddedTempPo bankCardFourElementAddedTempPo);
   
   /**
    * 更新状态
    * 
    * @version 2018年1月23日下午3:07:09
    * @author wuliu
    * @param bo
    * @return
    */
   public int updateStatusBatch(UpdateStatusBo bo);
   
    /**
     * 查询今日临时四要素索引数量
     * 
     * @version 2018年1月23日上午11:36:47
     * @author wuliu
     * @param map
     * @return
     */
    public Long fourElementAddedTempTodayQueryCount(Map<String, Object> map);

    /**
     * 查询今日临时四要素索引
     * 
     * @version 2018年1月23日上午11:36:47
     * @author wuliu
     * @param map
     * @return
     */
    public List<Map<String, Object>> fourElementAddedTempTodayQuery(Map<String, Object> map);

    /**
    * 根据ID更新
    * 
    * @version 2018年1月23日上午10:42:13
    * @author wuliu
    * @param entity
    * @return
    */
   public int update(BankCardFourElementAddedTempPo entity);
    
}
