/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.sys.SysRoleInfoPo;
import com.creditlink.dao.sys.SysRoleInfoDao;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.RetCode;
import com.creditlink.manager.service.sys.SysRoleInfoService;
import com.creditlink.manager.util.BeanUtil;
import com.creditlink.manager.util.ServiceException;
import com.creditlink.manager.util.bean.ServiceCommonUtils;

/**
 * SysRoleInfoServiceImpl
 * 
 * @version 2017年12月5日下午5:22:19
 * @author wuliu
 */
@Service
@Transactional
public class SysRoleInfoServiceImpl implements SysRoleInfoService{
    
    private static final Logger log = LoggerFactory.getLogger(SysRoleInfoServiceImpl.class);
    
    @Autowired
    private SysRoleInfoDao sysRoleInfoDao;

    @Override
    public void insert(SysRoleInfoPo entity) {
        sysRoleInfoDao.insert(entity);
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public SysRoleInfoPo find(SysRoleInfoPo info) {
        return sysRoleInfoDao.find(info);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SysRoleInfoPo> findAll(SysRoleInfoPo info) {
        return sysRoleInfoDao.findAll(info);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SysRoleInfoPo> getUserRoleInfo(Long userId) {
        return sysRoleInfoDao.getUserRoleInfo(userId);
    }

    @Override
    public void update(SysRoleInfoPo info, Boolean limit) {
        int count = sysRoleInfoDao.update(info);
        if(limit){
            if(count != 1){
                throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
            }
        }
    }

    @Override
    public void update(SysRoleInfoPo info, Integer num) {
        int count = sysRoleInfoDao.update(info);
        if(num.intValue() != count){
            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
        }     
    }

    @Override
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req) {
        //默认返回值
        res.setTotal(0L);
        res.setRows(new ArrayList<Map<String,Object>>());
        //查询条件
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = BeanUtil.objectToMapByIntrospector(req.getBean());
        }
        catch (Throwable e) {
            log.error("xxxxxx用户管理查询系统异常",e);
            return;
        }
        map.put("pageStart", req.getPageStart());
        map.put("pageSize", req.getRows());
        //查询数量
        Long total = sysRoleInfoDao.roleManagerQueryCount(map);
        res.setTotal(total);
        //查询数据
        List<Map<String,Object>> list = sysRoleInfoDao.roleManagerQuery(map);
        if(list == null || list.isEmpty()){
            list = new ArrayList<Map<String,Object>>();
        }
        res.setRows(list);
    }

    @Override
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean) {
        int idsLen =  updateStatusBatchBean.getIds().split(Const.IDS_SEPARATOR).length;
        UpdateStatusBo bo = ServiceCommonUtils.getUpdateStatusBo(updateStatusBatchBean);
        int count = sysRoleInfoDao.updateStatusBatch(bo);
        if(idsLen != count){
            log.info("需要更新数量:{},被更新数量:{}", updateStatusBatchBean.getIds(),count);
            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
        }
    }


}
