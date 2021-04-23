/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean.query;

import java.io.Serializable;

/**
 * 产品管理查询参数Bean
 * 
 * @version 2017年12月8日下午12:54:31
 * @author wuliu
 */
public class MemberManagerQueryBean implements Serializable {
    
    private static final long serialVersionUID = -669434668464406629L;
    /**
     * 客户姓名
     */
    private String custName;
    /**
     * 客户简称
     */
    private String custSname;
    /**
     * 状态 0:警用；1:启用；
     */
    private String status;
   
    
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSname() {
        return custSname;
    }

    public void setCustSname(String custSname) {
        this.custSname = custSname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
