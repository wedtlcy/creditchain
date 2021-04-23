/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.bas;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.bas.BasContractInfoPo;


/**
 * 
 * 合同信息Dao
 * @version 2018年1月11日上午9:47:09
 * @author wuliu
 */
public interface BasContractInfoDao{
    
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(BasContractInfoPo entity);
    
    /**
     * 查询单个
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public BasContractInfoPo find(BasContractInfoPo entity);

    /**
     * 产品管理查询数量
     * 
     * @version 2018年1月25日上午10:49:32
     * @author wuliu
     * @param map
     * @return
     */
    public Long contractManagerQueryCount(Map<String, Object> map);
    
    /**
     * 产品管理查询
     * 
     * @version 2018年1月25日上午10:49:32
     * @author wuliu
     * @param map
     * @return
     */
    public List<Map<String, Object>> contractManagerQuery(Map<String, Object> map);
    
    /**
     * 根据ID更新
     * 
     * @version 2018年1月23日上午10:42:13
     * @author wuliu
     * @param entity
     * @return
     */
    public int update(BasContractInfoPo entity);
    
    
    /**
     * 更新状态
     * 
     * @version 2018年1月23日上午10:22:56
     * @author wuliu
     * @param bo
     * @return
     */
    public int updateStatusBatch(UpdateStatusBo bo);
}
