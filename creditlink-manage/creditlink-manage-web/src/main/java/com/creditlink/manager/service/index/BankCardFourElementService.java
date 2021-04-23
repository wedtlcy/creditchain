/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.index;

import com.creditlink.bean.po.index.BankCardFourElementPo;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;


/**
 * BankCardService
 * @version 2018年1月19日下午12:36:59
 * @author wuliu
 */
public interface BankCardFourElementService {

    /**
     * 查询四要素索引信息
     * 
     * @version 2018年1月22日上午10:10:04
     * @author wuliu
     * @param addedTempPo
     * @return
     */
    public BankCardFourElementPo find(BankCardFourElementPo bankCardFourElementPo);
    
    /**
     * 今日银行卡四要素索引信息查询
     * 
     * @version 2018年1月23日上午8:55:30
     * @author wuliu
     * @param res
     * @param req
     */
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req);
    
    /**
     * 更新四要素索引状态
     * 
     * @version 2018年1月23日下午3:04:33
     * @author wuliu
     * @param ids
     * @param parseInt
     * @param userId
     */
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean);
    
    /**
     * 修改(指定条数)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public void update(BankCardFourElementPo info, Boolean limit);
}
