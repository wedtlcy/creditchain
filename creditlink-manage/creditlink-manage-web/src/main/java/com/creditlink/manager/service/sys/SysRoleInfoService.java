/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.sys;

import java.util.List;

import com.creditlink.bean.po.sys.SysRoleInfoPo;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.service.BaseService;

/**
 * SysRoleInfoService
 * @version 2017年12月5日下午5:22:07
 * @author wuliu
 */
public interface SysRoleInfoService extends BaseService<SysRoleInfoPo>{
    
    
    /**
     * 查询(所有)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public List<SysRoleInfoPo> findAll(SysRoleInfoPo info);
    
    /**
     * 获取用户角色信息
     *
     * @version 2017年12月5日下午7:20:41
     * @author wuliu
     * @param userId
     * @return
     */
    public List<SysRoleInfoPo> getUserRoleInfo(Long userId);
    
    /**
     * 修改(单条，或N条)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public void update(SysRoleInfoPo info, Boolean limit);
    
    /**
     * 修改(指定条数)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public void update(SysRoleInfoPo info, Integer num);

    /**
     * 角色管理查询
     * 
     * @version 2017年12月8日下午2:41:46
     * @author wuliu
     * @param res
     * @param req
     */
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req);
    
    /**
     * 批量更新角色状态
     * 
     * @version 2017年12月8日下午4:42:59
     * @author wuliu
     * @param ids
     * @param parseInt
     * @param userId
     */
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean);
}
