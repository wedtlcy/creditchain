/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.util.bean;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.constants.DBConstants;
import com.creditlink.manager.constants.RetCode;
import com.creditlink.manager.util.ServiceException;

/**
 * ServiceCommonUtils工具类
 * 
 * @version 2017年12月8日下午4:44:55
 * @author wuliu
 */
public class ServiceCommonUtils {
    
    private static final Logger log = LoggerFactory.getLogger(ServiceCommonUtils.class);
    
    /**
     * 获取更新状态BO
     * 
     * @version 2017年12月8日下午4:20:57
     * @author wuliu
     * @param ids
     * @param status
     * @param userId
     * @return
     */
    public static UpdateStatusBo getUpdateStatusBo(UpdateStatusBatchBean updateStatusBatchBean) {
        UpdateStatusBo bo = new UpdateStatusBo();
        bo.setIds(updateStatusBatchBean.getIds());
        if(DBConstants.STATUS_0 == updateStatusBatchBean.getStatus()){//更新为警用
            bo.setOldStatus(DBConstants.STATUS_1);
            bo.setNewStatus(DBConstants.STATUS_0);
        }else if(DBConstants.STATUS_1 ==  updateStatusBatchBean.getStatus()){//更新为启用
            bo.setOldStatus(DBConstants.STATUS_0);
            bo.setNewStatus(DBConstants.STATUS_1); 
        }else{
            log.info("更新失败状态错误,状态:{}", updateStatusBatchBean.getStatus());
            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
        }
        bo.setUpdateUser(updateStatusBatchBean.getBasUserInfoPo().getUserName());
        bo.setUpdateTime(new Date());
        return bo;
    }
}
