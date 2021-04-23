/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.index.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.bas.BasProductInfoPo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.index.BankCardFourElementAddedTempPo;
import com.creditlink.bean.po.index.BankCardFourElementPo;
import com.creditlink.dao.index.BankCardFourElementAddedTempDao;
import com.creditlink.dao.index.BankCardFourElementDao;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.RetCode;
import com.creditlink.manager.service.index.BankCardFourElementAddedTempService;
import com.creditlink.manager.util.BeanUtil;
import com.creditlink.manager.util.ServiceException;
import com.creditlink.manager.util.bean.ServiceCommonUtils;

/**
 * BankCardServiceImpl
 * @version 2018年1月19日下午12:37:24
 * @author wuliu
 */
@Service
@Transactional
public class BankCardFourElementAddedTempServiceImpl implements BankCardFourElementAddedTempService{
    
    private static final Logger log = LoggerFactory.getLogger(BankCardFourElementAddedTempServiceImpl.class);

    @Autowired
    private BankCardFourElementAddedTempDao bankCardFourElementAddedTempDao;
    @Autowired
    private BankCardFourElementDao bankCardFourElementDao;
    
    @Override
    public int fourElementImport(List<Map> list, BasProductInfoPo basProductInfoPo,BasUserInfoPo userInfLogin) {
        int n = 0;
        List<BankCardFourElementAddedTempPo> addedTempPos = new ArrayList<BankCardFourElementAddedTempPo>();
        int size = list.size();
        Map<String, String> map = new HashMap<String, String>();
        for(int i = 0;i < size; i ++){
            Map mapList = list.get(i);
            BankCardFourElementAddedTempPo addedTempPo = new BankCardFourElementAddedTempPo();
            addedTempPo.setProductId(basProductInfoPo.getProductId());
            String memberId = mapList.get("0") + "";
            addedTempPo.setMemberId(Long.parseLong(memberId));
            String bankCard = mapList.get("1") + "";
            addedTempPo.setBankCard(bankCard.trim());
            if(StringUtils.isNotBlank(map.get(bankCard))){
                log.info("银行卡四要素导入数据中存在重复索引信息:{},不允许重复数据,继续加载其它索引信息.",bankCard);
                n++;
                continue;
            }else{
                map.put(bankCard, bankCard);
            }
            String ip = mapList.get("2") + "";
            addedTempPo.setIp(ip.trim());
            String path = mapList.get("3") + "";
            addedTempPo.setPath(path.trim());
            addedTempPo.setCreateTime(new Date());
            addedTempPo.setCreateUser(userInfLogin.getUserName());
            //查询临时表状态为1可用
            BankCardFourElementAddedTempPo po = new BankCardFourElementAddedTempPo();
            po.setBankCard(bankCard);
            po.setStatus(com.creditlink.manager.util.Constants.STATUS_1);
            BankCardFourElementAddedTempPo returnTempPo = bankCardFourElementAddedTempDao.find(po);
            if(returnTempPo != null){
                log.info("银行卡四要素临时表中存在索引信息:{},不支持重复操作记为失败,继续加载其它索引信息.",bankCard);
                n++;
                continue;
            }
            //查询索引表(条件目前只有索引+状态)
            BankCardFourElementPo fourElementPo = new BankCardFourElementPo();
            fourElementPo.setBankCard(bankCard);
            fourElementPo.setStatus(com.creditlink.manager.util.Constants.STATUS_1);
            BankCardFourElementPo returnFourElementPo = bankCardFourElementDao.find(fourElementPo);
            if(returnFourElementPo != null){
                log.info("银行卡四要素表中存在索引信息:{},不支持重复操作记为失败,继续加载其它索引信息.",bankCard);
                n++;
                continue;
            }
            addedTempPos.add(addedTempPo);
        }
        if(addedTempPos != null && !addedTempPos.isEmpty()){
            bankCardFourElementAddedTempDao.insertBatch(addedTempPos);
        }
        log.info("此次需要导入数据量:{},导入失败数据量:{},预计成功导入数据量:{}", size, n, (size - n));
        return n;
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
            log.error("xxxxxx查询系统异常",e);
            return;
        }
        map.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        map.put("pageStart", req.getPageStart());
        map.put("pageSize", req.getRows());
        //查询数量
        Long total = bankCardFourElementAddedTempDao.fourElementAddedTempTodayQueryCount(map);
        res.setTotal(total);
        if(total != null && total.longValue() == 0){
            return;
        }
        //查询数据
        List<Map<String,Object>> list = bankCardFourElementAddedTempDao.fourElementAddedTempTodayQuery(map);
        if(list == null || list.isEmpty()){
            list = new ArrayList<Map<String,Object>>();
        }
        res.setRows(list);        
    }

    @Override
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean) {
        int idsLen = updateStatusBatchBean.getIds().split(Const.IDS_SEPARATOR).length;
        UpdateStatusBo bo = ServiceCommonUtils.getUpdateStatusBo(updateStatusBatchBean);
        int count = bankCardFourElementAddedTempDao.updateStatusBatch(bo);
        if(idsLen != count){
            log.info("需要更新数量:{},被更新数量:{}",updateStatusBatchBean.getIds(),count);
            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
        }
    }

    @Override
    public BankCardFourElementAddedTempPo find(BankCardFourElementAddedTempPo bankCardFourElementAddedTempPo) {
        return bankCardFourElementAddedTempDao.find(bankCardFourElementAddedTempPo);
    }

    @Override
    public void update(BankCardFourElementAddedTempPo info, Boolean limit) {
        int count = bankCardFourElementAddedTempDao.update(info);
        if(limit){
            if(count != 1){
                throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
            }
        }
    }

}
