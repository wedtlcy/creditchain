/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean;

import java.io.Serializable;

/**
 * DataGridResBean
 * 
 * @version 2017年12月8日下午12:34:48
 * @author wuliu
 */
public class DataGridResBean implements Serializable {
    
    private static final long serialVersionUID = -8972911209189606929L;
    /**
     * 总数目
     */
    private Long total;
    /**
     * 对象
     */
    private Object rows;
    
    public Long getTotal() {
        return total;
    }
    
    public void setTotal(Long total) {
        this.total = total;
    }
    
    public Object getRows() {
        return rows;
    }
    
    public void setRows(Object rows) {
        this.rows = rows;
    }
    
}
