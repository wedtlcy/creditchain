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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuRoleRelPo;
import com.creditlink.bean.po.sys.SysUserRoleRelPo;
import com.creditlink.dao.sys.SysUserRoleRelDao;
import com.creditlink.manager.service.sys.SysUserRoleRelService;

/**
 * SysUserRoleRelServiceImpl
 * @version 2017年12月5日下午5:24:20
 * @author wuliu
 */
@Service
@Transactional
public class SysUserRoleRelServiceImpl implements SysUserRoleRelService{
    
    @Autowired
    private SysUserRoleRelDao sysUserRoleRelDao;

    @Override
    public void insert(SysUserRoleRelPo entity) {
        sysUserRoleRelDao.insert(entity);
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public SysUserRoleRelPo find(SysUserRoleRelPo info) {
        return sysUserRoleRelDao.find(info);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SysUserRoleRelPo> findAll(SysUserRoleRelPo info) {
        return sysUserRoleRelDao.findAll(info);
    }

    @Override
    public void saveUserRoleRel(Long userId, String roleIds, BasUserInfoPo loginUser) {
        String[] roleIdsArr = roleIds.split(",");
        for(int i = 0; i < roleIdsArr.length; i++){
            SysUserRoleRelPo po = new SysUserRoleRelPo();
            po.setUserId(userId);
            po.setRoleId(Long.parseLong(roleIdsArr[i]));
            SysUserRoleRelPo relPo = find(po);
            if(relPo == null){
                po.setStatus(1);
                po.setCreateTime(new Date());
                po.setCreateUser(loginUser.getUserName());
                insert(po);
            }else{
                if(relPo.getStatus().intValue() == 1){
                    continue;
                }else{
                    SysUserRoleRelPo updateStatus = new SysUserRoleRelPo();
                    updateStatus.setUserId(userId);
                    updateStatus.setRoleId(Long.parseLong(roleIdsArr[i]));
                    updateStatus.setStatus(1);
                    updateStatus.setUpdateTime(new Date());
                    updateStatus.setUpdateUser(loginUser.getUserName());
                    sysUserRoleRelDao.updateStatus(updateStatus);
                }
            }
        }
    }
    
//    @Override
//    public void saveUserRoleRel(SysUserRoleRelPo entity, BasUserInfoPo userInfo) {
//        SysUserRoleRelPo relPo = find(entity);
//        if(relPo == null) {
//            entity.setStatus(1);
//            entity.setCreateTime(new Date());
//            entity.setCreateUser(userInfo.getUserName());
//            insert(entity);
//        }
//        else {
//            if(relPo.getStatus().intValue() == 1) {
//                return;
//            }
//            entity.setStatus(1);
//            entity.setUpdateTime(new Date());
//            entity.setUpdateUser(userInfo.getUserName());
//            sysUserRoleRelDao.updateStatus(entity);
//        }
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("userId", entity.getUserId());
//        params.put("roleId", entity.getRoleId());
//        params.put("oldStatus", 1);
//        params.put("newStatus", 0);
//        params.put("updateUser", userInfo.getUserName());
//        params.put("updateTime", new Date());
//        sysUserRoleRelDao.updateStatusBatch(params);
//    }
}
