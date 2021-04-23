/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean;

import java.io.Serializable;

/**
 * DataGridReqBean
 * @version 2017年12月8日下午12:44:46
 * @author wuliu
 */
public class DataGridReqBean implements Serializable{
    
    private static final long serialVersionUID = -7446273774079297201L;
    /**
     * 当前第几页
     */
    private Long page;
    /**
     * 每页显示的记录数
     */
    private Long rows;
    /**
     * 第几条记录起始
     */
    private Long pageStart;
    /**
     * 其它查询参数
     */
    private Object bean;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Long getPageStart() {
        pageStart = (page - 1) * rows;
        return pageStart;
    }
    
}
