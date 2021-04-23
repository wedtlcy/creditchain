/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.bas;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.bas.BasConProdRelPo;
import com.creditlink.bean.po.bas.BasContractInfoPo;


/**
 * 
 * 合同产品关联Dao
 * @version 2018年1月11日上午9:47:02
 * @author wuliu
 */
public interface BasConProdRelDao{
    
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(BasConProdRelPo entity);
    
    /**
     * 合同关联产品管理查询数量
     * 
     * @version 2018年1月25日上午10:49:32
     * @author wuliu
     * @param map
     * @return
     */
    public Long contractProductManagerQueryCount(Map<String, Object> map);
    
    /**
     * 合同关联产品管理查询
     * 
     * @version 2018年1月25日上午10:49:32
     * @author wuliu
     * @param map
     * @return
     */
    public List<Map<String, Object>> contractProductManagerQuery(Map<String, Object> map);

    /**
     * 更新合同产品关联状态
     * 
     * @version 2018年1月31日上午11:28:39
     * @author wuliu
     * @param bo
     * @return
     */
    public int updateStatusBatch(UpdateStatusBo bo);
    
    /**
     * 查询单个
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public BasConProdRelPo find(BasConProdRelPo entity);
}
