/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.sys.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuRoleRelPo;
import com.creditlink.dao.sys.SysMenuRoleRelDao;
import com.creditlink.manager.service.sys.SysMenuRoleRelService;

/**
 * SysMenuRoleRelServiceImpl
 * 
 * @version 2017年12月5日下午5:20:24
 * @author wuliu
 */
@Service
@Transactional
public class SysMenuRoleRelServiceImpl implements SysMenuRoleRelService {
    
    private static final Logger log = LoggerFactory.getLogger(SysMenuRoleRelServiceImpl.class);

    @Autowired
    private SysMenuRoleRelDao sysMenuRoleRelDao;
    
    @Override
    public void insert(SysMenuRoleRelPo entity) {
        sysMenuRoleRelDao.insert(entity);
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public SysMenuRoleRelPo find(SysMenuRoleRelPo info) {
        return sysMenuRoleRelDao.find(info);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SysMenuRoleRelPo> findAll(SysMenuRoleRelPo info) {
        return sysMenuRoleRelDao.findAll(info);
    }
    
    @Override
    public int updateStatusBatch(Map<String, Object> params) {
        return sysMenuRoleRelDao.updateStatusBatch(params);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public void saveMenuRoleRels(Long roleId, List<Long> menuIdList, BasUserInfoPo userInfo) throws Exception {
        try {
            String ids = "";
            for(Long menuId: menuIdList) {
                ids += String.valueOf(menuId) + ',';
                SysMenuRoleRelPo entity = new SysMenuRoleRelPo();
                entity.setMenuId(menuId);
                entity.setRoleId(roleId);
                SysMenuRoleRelPo relPo = sysMenuRoleRelDao.find(entity);
                if(relPo == null) {
                    entity.setStatus(1);
                    entity.setCreateTime(new Date());
                    entity.setCreateUser(userInfo.getUserName());
                    sysMenuRoleRelDao.insert(entity);
                }
                else {
                    if(relPo.getStatus() == 1) {
                        continue;
                    }
                    entity.setStatus(1);
                    entity.setUpdateTime(new Date());
                    entity.setUpdateUser(userInfo.getUserName());
                    sysMenuRoleRelDao.updateStatus(entity);
                }
            }
            if(StringUtils.isNotBlank(ids)) {
                ids = ids.substring(0, ids.length() - 1);
            }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("roleId", roleId);
            params.put("ids", ids);
            params.put("oldStatus", 1);
            params.put("newStatus", 0);
            params.put("updateUser", userInfo.getUserName());
            params.put("updateTime", new Date());
            sysMenuRoleRelDao.updateStatusBatch(params);
        }
        catch (Exception e) {
            throw new Exception();
        }
    }
}
