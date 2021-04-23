/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.sys;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.sys.SysRoleInfoPo;

/**
 * 
 * 角色信息Dao
 * @version 2018年1月11日上午10:00:04
 * @author wuliu
 */
public interface SysRoleInfoDao{
    
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(SysRoleInfoPo entity);
    
    /**
     * 查询单个
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public SysRoleInfoPo find(SysRoleInfoPo entity);
    
    /**
     * 根据ID更新
     * 
     * @version 2018年1月23日上午10:42:13
     * @author wuliu
     * @param entity
     * @return
     */
    public int update(SysRoleInfoPo entity);
    
    /**
     * 更新状态
     * 
     * @version 2018年1月23日上午10:22:56
     * @author wuliu
     * @param bo
     * @return
     */
    public int updateStatusBatch(UpdateStatusBo bo);
    
    /**
     * 
     * 查询用户角色关联信息
     * @version 2018年1月11日上午10:01:19
     * @author wuliu
     * @param info
     * @return
     */
    public List<SysRoleInfoPo> findAll(SysRoleInfoPo info);
    
    /**
     * 
     * 查询用户角色关联信息
     * @version 2018年1月11日上午10:01:15
     * @author wuliu
     * @param userId
     * @return
     */
    public List<SysRoleInfoPo> getUserRoleInfo(Long userId);

    /**
     * 
     * 角色管理查询数量
     * @version 2018年1月11日上午10:01:11
     * @author wuliu
     * @param map
     * @return
     */
    public Long roleManagerQueryCount(Map<String, Object> map);

    /**
     * 
     * 角色管理查询
     * @version 2018年1月11日上午10:01:07
     * @author wuliu
     * @param map
     * @return
     */
    public List<Map<String, Object>> roleManagerQuery(Map<String, Object> map);
    
}
