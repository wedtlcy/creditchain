/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.sys;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.po.sys.SysMenuRoleRelPo;


/**
 * 
 * 角色菜单关联Dao
 * @version 2018年1月11日上午10:04:41
 * @author wuliu
 */
public interface SysMenuRoleRelDao{
 
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(SysMenuRoleRelPo entity);
    
    /**
     * 查询单个
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public SysMenuRoleRelPo find(SysMenuRoleRelPo entity);
    
    /**
     * 
     * 查询信息
     * @version 2018年1月11日上午10:10:09
     * @author wuliu
     * @param entity
     * @return
     */
    public List<SysMenuRoleRelPo> findAll(SysMenuRoleRelPo entity);
    
    /**
     * 
     * 批量更新状态
     * @version 2018年1月11日上午10:10:16
     * @author wuliu
     * @param params
     * @return
     */
    public int updateStatusBatch(Map<String, Object> params); 
    
    /**
     * 
     * 更新状态
     * @version 2018年2月6日上午9:58:29
     * @author liuheng
     * @param entity
     * @return
     */
    public int updateStatus(SysMenuRoleRelPo entity);
}
