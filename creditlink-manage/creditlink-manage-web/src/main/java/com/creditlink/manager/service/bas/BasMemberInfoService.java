/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.bas;

import com.creditlink.bean.po.bas.BasMemberInfoPo;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;

/**
 * BasMemberInfoService
 * @version 2018年1月19日下午12:50:18
 * @author wuliu
 */
public interface BasMemberInfoService {
    
    /**
     * 联盟成员管理查询
     * 
     * @version 2017年12月8日下午12:58:37
     * @author wuliu
     * @param res
     * @param req
     */
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req);
    
    /**
     * 更新联盟成员状态
     * 
     * @version 2018年1月23日下午3:04:33
     * @author wuliu
     * @param ids
     * @param parseInt
     * @param userId
     */
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean);
    
    /**
     * 查询联盟成员
     * 
     * @version 2018年1月26日上午8:56:51
     * @author wuliu
     * @param temp
     * @return
     */
    public BasMemberInfoPo find(BasMemberInfoPo temp);

    /**
     * 修改联盟成员
     * 
     * @version 2018年1月26日上午9:23:02
     * @author wuliu
     * @param po
     * @param updateLimit1
     */
    public void update(BasMemberInfoPo po, Boolean limit);
    
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(BasMemberInfoPo entity);
}
