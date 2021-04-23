/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.cfg.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.cfg.CfgDictPo;
import com.creditlink.dao.cfg.CfgDictDao;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.RetCode;
import com.creditlink.manager.service.cfg.CfgDictService;
import com.creditlink.manager.util.BeanUtil;
import com.creditlink.manager.util.ServiceException;
import com.creditlink.manager.util.bean.ServiceCommonUtils;

/**
 * CfgDictServiceImpl
 * @version 2017年12月8日上午9:43:48
 * @author wuliu
 */
@Service
@Transactional
public class CfgDictServiceImpl implements CfgDictService{

    private static final Logger log = LoggerFactory.getLogger(CfgDictServiceImpl.class);
    
    @Autowired
    private CfgDictDao cfgDictDao;
    
//    @Override
//    public void insert(CfgDictPo entity) {
//        cfgDictDao.insert(entity);
//    }
//    
//    @Override
//    public CfgDictPo find(CfgDictPo entity) {
//        return cfgDictDao.find(entity);
//    }
//
//    @Override
//    public List<CfgDictPo> findAll(CfgDictPo info) {
//        return cfgDictDao.findAll(info);
//    }
//
//    @Override
//    public void update(CfgDictPo entity, Boolean limit) {
//        int count = cfgDictDao.update(entity);
//        if(limit){
//            if(count != 1){
//                throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
//            }
//        }
//    }
//
//    @Override
//    public void update(CfgDictPo entity, Integer num) {
//        int count = cfgDictDao.update(entity);
//        if(num.intValue() != count){
//            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
//        }     
//    }
//
//    @Override
//    public void loadDataGrid(DataGridResBean res, DataGridReqBean req) {
//        //默认返回值
//        res.setTotal(0L);
//        res.setRows(new ArrayList<Map<String,Object>>());
//        //查询条件
//        Map<String, Object> map = new HashMap<String, Object>();
//        try {
//            map = BeanUtil.objectToMapByIntrospector(req.getBean());
//        }
//        catch (Throwable e) {
//            log.error("xxxxxx用户管理查询系统异常",e);
//            return;
//        }
//        map.put("pageStart", req.getPageStart());
//        map.put("pageSize", req.getRows());
//        //查询数量
//        Long total = cfgDictDao.dictManagerQueryCount(map);
//        res.setTotal(total);
//        //查询数据
//        List<Map<String,Object>> list = cfgDictDao.dictManagerQuery(map);
//        if(list == null || list.isEmpty()){
//            list = new ArrayList<Map<String,Object>>();
//        }
//        res.setRows(list);
//    }
//
//    @Override
//    public void updateStatusBatch(String ids, int status, Long userId) {
//        int idsLen = ids.split(Const.IDS_SEPARATOR).length;
//        UpdateStatusBo bo = ServiceCommonUtils.getUpdateStatusBo(ids, status, userId);
//        int count = cfgDictDao.updateStatusBatch(bo);
//        if(idsLen != count){
//            log.info("需要更新数量:{},被更新数量:{}",ids,count);
//            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
//        }
//    }

}
