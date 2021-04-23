/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.sys;

import java.util.List;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuInfoPo;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.Tree;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.service.BaseService;

/**
 * SysMenuInfoService
 * 
 * @version 2017年12月5日下午5:17:27
 * @author wuliu
 */
public interface SysMenuInfoService extends BaseService<SysMenuInfoPo>{
    
    /**
     * 查询菜单
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public List<SysMenuInfoPo> findAll(SysMenuInfoPo info);
    
    /**
     * 查询用户菜单
     * 
     * @version 2017年12月5日下午6:58:33
     * @author wuliu
     * @param userId
     * @return
     */
    public List<SysMenuInfoPo> getUserMenuInfo(Long userId);
    
    /**
     * 修改(单条，或N条)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public void update(SysMenuInfoPo info, Boolean limit);
    
    /**
     * 修改(指定条数)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public void update(SysMenuInfoPo info, Integer num);
    
    /**
     * 更新菜单状态
     * 
     * @version 2017年12月8日下午5:02:14
     * @author wuliu
     * @param ids
     * @param parseInt
     * @param userId
     */
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean);

    /**
     * 菜单管理查询
     * 
     * @version 2017年12月8日下午5:03:04
     * @author wuliu
     * @param res
     * @param req
     */
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req);

    /**
     * 查询用户授权菜单树
     * 
     * @version 2017年12月11日下午2:39:21
     * @author wuliu
     * @param parentId
     * @param id
     * @return
     */
    public List<Tree> queryUserAuthMenu(String parentId, BasUserInfoPo userInfLogin);
    
    /**
     * 
     * 根据父菜单ID查询最大的子节点菜单ID
     * @version 2018年2月5日上午9:57:39
     * @author liuheng
     * @param menuParId
     * @return
     */
    public Long getMaxMenuId(Long menuParId);
    
    /**
     * 
     * 菜单列表转菜单树
     * @version 2018年1月12日上午9:49:36
     * @author liuheng
     * @param menuList
     * @return
     */
    public List<Tree> menuListToTreeList(List<SysMenuInfoPo> menuList) throws Exception;
}
