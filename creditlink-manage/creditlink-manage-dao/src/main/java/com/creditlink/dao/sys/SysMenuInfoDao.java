/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuInfoPo;

/**
 * 
 * 菜单信息Dao
 * @version 2018年1月11日上午10:09:57
 * @author wuliu
 */
public interface SysMenuInfoDao{
    
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(SysMenuInfoPo entity);
    
    /**
     * 查询单个
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public SysMenuInfoPo find(SysMenuInfoPo entity);
    
    /**
     * 根据ID更新
     * 
     * @version 2018年1月23日上午10:42:13
     * @author wuliu
     * @param entity
     * @return
     */
    public int update(SysMenuInfoPo entity);
    
    /**
     * 
     * 查询菜单信息
     * @version 2018年1月11日上午10:10:09
     * @author wuliu
     * @param info
     * @return
     */
    public List<SysMenuInfoPo> findAll(SysMenuInfoPo info);
    
    /**
     * 
     * 批量更新菜单状态
     * @version 2018年1月11日上午10:10:16
     * @author wuliu
     * @param bo
     * @return
     */
    public int updateStatusBatch(UpdateStatusBo bo);
    
    
    /**
     * 
     * 查询用户菜单信息
     * @version 2018年1月11日上午10:10:13
     * @author wuliu
     * @param bo
     * @return
     */
    public List<SysMenuInfoPo> getUserMenuInfo(BasUserInfoPo bo);
    
    /**
     * 
     * 用户管理查询数量
     * @version 2018年1月11日上午10:10:20
     * @author wuliu
     * @param map
     * @return
     */
    public Long menuManagerQueryCount(Map<String, Object> map);

    /**
     * 
     * 用户管理查询
     * @version 2018年1月11日上午10:10:23
     * @author wuliu
     * @param map
     * @return
     */
    public List<Map<String, Object>> menuManagerQuery(Map<String, Object> map);

    /**
     * 
     * 查询用户授权菜单树 
     * @version 2018年1月11日上午10:10:33
     * @author wuliu
     * @param userInfLogin
     * @return
     */
    public List<SysMenuInfoPo> queryUserAuthMenu(BasUserInfoPo userInfLogin);

    /**
     * 获取用户授权url
     * 
     * @version 2018年2月2日上午10:27:28
     * @author wuliu
     * @param map
     * @return
     */
    public List<String> getUserAuthUrl(HashMap<String, Object> map);
    
    /**
     * 
     * 根据父菜单ID查询最大的子节点菜单ID
     * @version 2018年2月5日上午9:07:07
     * @author liuheng
     * @param menuParId
     * @return
     */
    public Long getMaxMenuId(Long menuParId);
    
}
