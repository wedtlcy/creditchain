/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.bo;

/**
 * BaseQueryBo批量查询基类
 * 
 * @version 2017年12月5日下午4:53:01
 * @author wuliu
 */
public class BaseQueryBo extends BaseBo{
    
    private static final long serialVersionUID = -2453476278266064637L;
    /**
     * 当前第几页
     */
    private Integer pageNo;
    /**
     * 每页条数
     */
    private Integer pageSize;
    
    public Integer getPageStart() {
        if (this.getPageNo() <= 0) {
            return 0;
        }
        else {
            return (this.getPageNo() - 1) * this.getPageSize();
        }
    }
    
    public Integer getPageNo() {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
