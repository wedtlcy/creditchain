/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean;

import java.io.Serializable;

import com.creditlink.bean.po.bas.BasUserInfoPo;

/**
 * UpdateStatusBatchBean
 * 
 * @version 2018年1月31日下午3:03:31
 * @author wuliu
 */
public class UpdateStatusBatchBean implements Serializable {
    
    private static final long serialVersionUID = -7740197246125583400L;
    
    /**
     * ids
     */
    private String ids;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 登录用户
     */
    private BasUserInfoPo basUserInfoPo;
    
    
    public UpdateStatusBatchBean() {
    }

    public UpdateStatusBatchBean(String ids, Integer status, BasUserInfoPo basUserInfoPo) {
        super();
        this.ids = ids;
        this.status = status;
        this.basUserInfoPo = basUserInfoPo;
    }

    public String getIds() {
        return ids;
    }
    
    public void setIds(String ids) {
        this.ids = ids;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public BasUserInfoPo getBasUserInfoPo() {
        return basUserInfoPo;
    }
    
    public void setBasUserInfoPo(BasUserInfoPo basUserInfoPo) {
        this.basUserInfoPo = basUserInfoPo;
    }
    
}
