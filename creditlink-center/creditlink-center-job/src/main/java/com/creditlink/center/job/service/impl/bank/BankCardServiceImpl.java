/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.job.service.impl.bank;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.center.job.bo.BankCardFourElementLogBo;
import com.creditlink.center.job.dao.BankCardDao;
import com.creditlink.center.job.entity.bank.BankCardFourElementAddedTempEntity;
import com.creditlink.center.job.entity.bank.BankCardFourElementEntity;
import com.creditlink.center.job.entity.bank.BankCardFourElementLogEntity;
import com.creditlink.center.job.service.bank.BankCardService;
import com.creditlink.center.job.util.Constant;

/**
 * BankCardServiceImpl
 * @version 2018年1月5日下午4:21:57
 * @author wuliu
 */
@Service
@Transactional
public class BankCardServiceImpl implements BankCardService{

    private static final Logger logger = LoggerFactory.getLogger(BankCardServiceImpl.class);
    
    @Autowired
    private BankCardDao bankCardDao;
    @Override
    public void bankCardFourElementLog() {
        //查询需要处理的日志10条
        BankCardFourElementLogBo bo = new BankCardFourElementLogBo();
        bo.setIsUse(Constant.IS_USE_1);
        bo.setHandlerStatus(Constant.HANDLER_STATUS_10);
        List<BankCardFourElementLogEntity> fourLogEntities = bankCardDao.findBankCardFourLog(bo);
        if(fourLogEntities == null || fourLogEntities.size() == 0){
            logger.info("没有日志需要统计处理.....");
            return;
        }
        for(int i =  0; i < fourLogEntities.size(); i++){
            BankCardFourElementLogEntity bankCardFourLogEntity = fourLogEntities.get(i);
            BankCardFourElementLogBo bankCardFourElementLogBo = new BankCardFourElementLogBo();
            bankCardFourElementLogBo.setAddedTempId(bankCardFourLogEntity.getAddedTempId());
            if(Constant.BANKCARD_FOUR_STATUS_1.equals(bankCardFourLogEntity.getStatus())){
                bankCardFourElementLogBo.setSuccess(1);
                bankCardFourElementLogBo.setError(0);
            }else{
                bankCardFourElementLogBo.setSuccess(0);
                bankCardFourElementLogBo.setError(1);
            }
           int count = bankCardDao.updateBankCardFourCount(bankCardFourElementLogBo);
           logger.info("更新条件:{},更新次数count:{}", bankCardFourElementLogBo , count);
           Map<String,Object> map = new HashMap<String,Object>();
           map.put("isUse", "0");
           if(count == 1){
               map.put("handlerstatus", Constant.HANDLER_STATUS_20);
           }else{
               map.put("handlerstatus", Constant.HANDLER_STATUS_30);
           }
           map.put("logId", bankCardFourLogEntity.getLogId());
           bankCardDao.updateBankCardFourLogHandlerstatus(map);
        }
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void bankCardFourElementAddOverDue() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("status", Constant.STATUS_1);
        List<BankCardFourElementAddedTempEntity> addedTempEntities = bankCardDao.findFourElementAddTemp(map);
        int size = addedTempEntities.size();
        if(size == 0){
            logger.info("没有数据需要移库..............");
            return;
        }
        Map<String,Object> addOverDueMap = new HashMap<String,Object>();
        addOverDueMap.put("oldStatus", Constant.STATUS_1);
        addOverDueMap.put("newStatus", Constant.STATUS_2);
        for(int i = 0; i < size; i ++){
            BankCardFourElementAddedTempEntity addedTempEntity = addedTempEntities.get(i);
            addOverDueMap.put("id", addedTempEntity.getId());
            
            BankCardFourElementEntity entity = new BankCardFourElementEntity();
            //目前只查询索引是否存在相同
            entity.setBankCard(addedTempEntity.getBankCard());
            entity.setStatus(Constant.STATUS_1);
            BankCardFourElementEntity bankCardFourElementEntity = bankCardDao.findFourElement(entity);
            if(bankCardFourElementEntity != null){
                Long count = bankCardDao.updateFourElementAddedStatus(addOverDueMap);
                logger.info("银行卡四要素移库任务索引已经存在不移库，更新临时索引表索引状态为移库完成,更新数据数量:{},参数:{}",count,addOverDueMap);
                continue;
            }
            BankCardFourElementEntity fourElementEntity = new BankCardFourElementEntity();
            fourElementEntity.setAddedTempId(addedTempEntity.getId());
            fourElementEntity.setProductId(addedTempEntity.getProductId());
            fourElementEntity.setMemberId(addedTempEntity.getMemberId());
            fourElementEntity.setBankCard(addedTempEntity.getBankCard());
            fourElementEntity.setIp(addedTempEntity.getIp());
            fourElementEntity.setPath(addedTempEntity.getPath());
            fourElementEntity.setCreateTime(addedTempEntity.getCreateTime());
            fourElementEntity.setCreateUser(addedTempEntity.getCreateUser());
           
            bankCardDao.insertFourElement(fourElementEntity);
            Long count = bankCardDao.updateFourElementAddedStatus(addOverDueMap);
            logger.info("银行卡四要素移库任务索引不存在进行移库，更新临时索引表索引状态为移库完成,更新数据数量:{},参数:{}",count,addOverDueMap);
        }
    }

    @Override
    public void bankCardFourElementMoveOver() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("oldStatus", Constant.STATUS_1);
        map.put("newStatus", Constant.STATUS_2);
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        map.put("time", time);
        Long count = bankCardDao.updateFourElementAddedStatus(map);
        logger.info("银行卡四要素移库完成任务,更新数据数量:{}",count);
    }

    @Override
    public void bankCardFourElementUpdateOverDue() {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("oldStatus", Constant.UPDATE_RECORD_STATUS_10);
        map.put("newStatus", Constant.UPDATE_RECORD_STATUS_20);
        Calendar   cal   =   Calendar.getInstance();
        //cal.add(Calendar.DATE,   -1);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        map.put("time", time);
        Long count = bankCardDao.updateFourElementUpdateRecordStatus(map);
        logger.info("银行卡四要素修改记录过期处理任务,更新数据数量:{}",count);
    }
    
}
