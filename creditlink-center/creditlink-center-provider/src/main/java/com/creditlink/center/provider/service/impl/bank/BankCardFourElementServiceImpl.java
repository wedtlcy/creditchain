/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.service.impl.bank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.center.provider.api.consts.BankCardRetCode;
import com.creditlink.center.provider.api.vo.BankCardFourElementAddedReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementAddedResVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateReqVo;
import com.creditlink.center.provider.api.vo.BankCardFourElementUpdateResVo;
import com.creditlink.center.provider.dao.BankCardDao;
import com.creditlink.center.provider.service.bank.BankCardFourElementService;
import com.creditlink.center.provider.util.Constant;

/**
 * BankCardFourElementServiceImpl
 * 
 * @version 2018年1月8日下午5:14:32
 * @author wuliu
 */
@Service
@Transactional
public class BankCardFourElementServiceImpl implements BankCardFourElementService{

    @Autowired
    private BankCardDao bankCardDao;
    
    @Override
    public BankCardFourElementAddedResVo bankCardFourElementAdded(BankCardFourElementAddedReqVo reqVo) {
        BankCardFourElementAddedResVo res = new BankCardFourElementAddedResVo();
        res.setRetInfo(BankCardRetCode.CODE_000000, BankCardRetCode.MSG_000000);
        //查询昨日新增数据量
        Map<String,Object> countMap = new HashMap<String,Object>();
        Calendar   cal   =   Calendar.getInstance();
        cal.add(Calendar.DATE,   -1);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        countMap.put("time", time);
        countMap.put("status", Constant.STATUS_1);
        Long count = bankCardDao.addedBankCardFourElementCount(countMap);
        if(count != null && count.longValue() == 0){
            res.setTotalPage(0L);
            res.setData(new ArrayList<Map<String,Object>>());
            return res;
        }
        Long totalPage = 0L;
        if(count % Constant.PAGE_SIZE == 0){
            totalPage = count / Constant.PAGE_SIZE;
        }else{
            totalPage = count / Constant.PAGE_SIZE +1;
        }
        res.setTotalPage(totalPage);
        countMap.put("pageStart", (reqVo.getPageNo() - 1) *  Constant.PAGE_SIZE);
        countMap.put("pageSize", Constant.PAGE_SIZE);
        List<Map<String,Object>> list = bankCardDao.addedBankCardFourElement(countMap);
        if(list != null && !list.isEmpty()){
            res.setData(list);
        }else{
            res.setData(new ArrayList<Map<String,Object>>());
        }
        return res;
    }

    @Override
    public BankCardFourElementUpdateResVo bankCardFourElementUpdate(BankCardFourElementUpdateReqVo reqVo) {
        BankCardFourElementUpdateResVo res = new BankCardFourElementUpdateResVo();
        res.setRetInfo(BankCardRetCode.CODE_000000, BankCardRetCode.MSG_000000);
        //查询当日修改数据量
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("status", Constant.UPDATE_STATUS_10);
        Calendar   cal   =   Calendar.getInstance();
        String time = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        params.put("time", time);
        Long count = bankCardDao.bankCardFourElementUpdateCount(params);
        if(count != null && count.longValue() == 0){
            res.setTotalPage(0L);
            res.setData(new ArrayList<Map<String,Object>>());
            return res;
        }
        Long totalPage = 0L;
        if(count % Constant.PAGE_SIZE == 0){
            totalPage = count / Constant.PAGE_SIZE;
        }else{
            totalPage = count / Constant.PAGE_SIZE +1;
        }
        res.setTotalPage(totalPage);
        params.put("pageStart", (reqVo.getPageNo() - 1) *  Constant.PAGE_SIZE);
        params.put("pageSize", Constant.PAGE_SIZE);
        List<Map<String,Object>> list = bankCardDao.bankCardFourElementUpdate(params);
        if(list != null && !list.isEmpty()){
            res.setData(list);
        }else{
            res.setData(new ArrayList<Map<String,Object>>());
        }
        return res;
    }
    
}
