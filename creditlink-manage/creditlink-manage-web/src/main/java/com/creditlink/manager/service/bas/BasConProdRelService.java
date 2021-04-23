/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.bas;

import com.creditlink.bean.po.bas.BasConProdRelPo;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;

/**
 * BasConProdRelService
 * 
 * @version 2018年1月31日上午10:14:01
 * @author wuliu
 */
public interface BasConProdRelService {
    
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
     * 合同产品关联管理查询
     * 
     * @version 2017年12月8日下午12:58:37
     * @author wuliu
     * @param res
     * @param req
     */
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req);

    /**
     * 更新合同产品关联状态
     * 
     * @version 2018年1月31日上午11:27:42
     * @author wuliu
     * @param ids
     * @param parseInt
     * @param userId
     */
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean);
    
    /**
     * 查询合同
     * 
     * @version 2018年1月26日上午8:56:51
     * @author wuliu
     * @param temp
     * @return
     */
    public BasConProdRelPo find(BasConProdRelPo temp);
}
