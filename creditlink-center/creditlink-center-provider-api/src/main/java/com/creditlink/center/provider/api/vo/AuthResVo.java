/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.api.vo;


/**
 * AuthResVo
 *
 * @version 2018年1月2日上午8:46:43
 * @author wuliu
 */
public class AuthResVo extends BaseResVo{

    private static final long serialVersionUID = -7840835181936215589L;
    /**
     * 是否有权限
     */
    private String isLimit;

    public String getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(String isLimit) {
        this.isLimit = isLimit;
    }

}
