/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.sys;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuRoleRelPo;
import com.creditlink.manager.service.BaseService;

/**
 * SysMenuRoleRelService
 * @version 2017年12月5日下午5:20:14
 * @author wuliu
 */
public interface SysMenuRoleRelService extends BaseService<SysMenuRoleRelPo>{
    
    /**
     * 查询(所有)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public List<SysMenuRoleRelPo> findAll(SysMenuRoleRelPo info);
    
    /**
     * 
     * 更新状态
     * @version 2018年2月6日上午10:59:47
     * @author liuheng
     * @param params
     * @return
     */
    public int updateStatusBatch(Map<String, Object> params);
    
    /**
     * 
     * 保存所有授权菜单
     * @version 2018年2月6日上午9:54:37
     * @author liuheng
     * @param roleId
     * @param menuIdList
     * @param UserName
     * @throws Exception
     */
    public void saveMenuRoleRels(Long roleId, List<Long> menuIdList, BasUserInfoPo userInfo) throws Exception;
}
