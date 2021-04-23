/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * δ������˾��ʽ����ͬ�⣬�����κθ��ˡ����岻��ʹ�á����ơ��޸Ļ򷢲������.
 * ��Ȩ���������������������޹�˾ http://www.credlink.com/
 */
package com.creditlink.manager.service.bas;

import java.util.List;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.service.BaseService;

/**
 * BasUserInfoService
 * 
 * @version 2017年12月5日下午5:14:21
 * @author wuliu
 */
public interface BasUserInfoService extends BaseService<BasUserInfoPo>{
 
    
    /**
     * 查询(所有)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public List<BasUserInfoPo> findAll(BasUserInfoPo info);
    
    /**
     * 修改(单条，或N条)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public void update(BasUserInfoPo info, Boolean limit);
    
    /**
     * 修改(指定条数)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public void update(BasUserInfoPo info, Integer num);

    /**
     * 用户管理查询
     * 
     * @version 2017年12月8日下午12:58:37
     * @author wuliu
     * @param res
     * @param req
     */
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req);
    
    /**
     * 更新用户状态
     * 
     * @version 2017年12月8日下午3:44:09
     * @author wuliu
     * @param ids
     * @param userId
     */
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean);
}
