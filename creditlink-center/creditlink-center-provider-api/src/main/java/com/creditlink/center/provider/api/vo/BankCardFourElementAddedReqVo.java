/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api.vo;


/**
 * 银行卡四要素新增请求VO
 * 
 * @version 2018年1月8日下午5:27:04
 * @author wuliu
 */
public class BankCardFourElementAddedReqVo extends BaseReqVo{

    private static final long serialVersionUID = -4077501645831393024L;
    /**
     * 第几页
     */
    private Integer pageNo;
    
    public Integer getPageNo() {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    
}
