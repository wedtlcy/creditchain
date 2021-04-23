/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.bas.impl;

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
import com.creditlink.bean.po.bas.BasProductInfoPo;
import com.creditlink.dao.bas.BasProductInfoDao;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.RetCode;
import com.creditlink.manager.service.bas.BasProductInfoService;
import com.creditlink.manager.util.BeanUtil;
import com.creditlink.manager.util.ServiceException;
import com.creditlink.manager.util.bean.ServiceCommonUtils;


/**
 * BasProductInfoServiceImpl
 * 
 * @version 2018年1月19日下午12:50:25
 * @author wuliu
 */
@Service
@Transactional
public class BasProductInfoServiceImpl implements BasProductInfoService{
    
    private static final Logger log = LoggerFactory.getLogger(BasProductInfoServiceImpl.class);
    
    @Autowired
    private BasProductInfoDao basProductInfoDao;
    
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
            log.error("xxxxxx查询系统异常",e);
            return;
        }
        map.put("pageStart", req.getPageStart());
        map.put("pageSize", req.getRows());
        //查询数量
        Long total = basProductInfoDao.productManagerQueryCount(map);
        res.setTotal(total);
        if(total != null && total.longValue() == 0){
            return;
        }
        //查询数据
        List<Map<String,Object>> list = basProductInfoDao.productManagerQuery(map);
        if(list == null || list.isEmpty()){
            list = new ArrayList<Map<String,Object>>();
        }
        res.setRows(list);       
    }

    @Override
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean) {
        int idsLen = updateStatusBatchBean.getIds().split(Const.IDS_SEPARATOR).length;
        UpdateStatusBo bo = ServiceCommonUtils.getUpdateStatusBo(updateStatusBatchBean);
        int count = basProductInfoDao.updateStatusBatch(bo);
        if(idsLen != count){
            log.info("需要更新数量:{},被更新数量:{}",updateStatusBatchBean.getIds(),count);
            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
        }
    }

    @Override
    public BasProductInfoPo find(BasProductInfoPo temp) {
        return basProductInfoDao.find(temp);
    }

    @Override
    public void update(BasProductInfoPo info, Boolean limit) {
        int count = basProductInfoDao.update(info);
        if(limit){
            if(count != 1){
                throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
            }
        }
    }

    @Override
    public void insert(BasProductInfoPo entity) {
        basProductInfoDao.insert(entity);
    }
}
