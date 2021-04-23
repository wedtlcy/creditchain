/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api.vo;


/**
 * 银行卡四要素修改请求返回VO
 * 
 * @version 2018年1月8日下午5:28:36
 * @author wuliu
 */
public class BankCardFourElementUpdateResVo extends BaseResVo{

    private static final long serialVersionUID = -9155818512661120750L;
    
    /**
     * 总页数
     */
    private Long totalPage;
    
    private Object data;
    
    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
