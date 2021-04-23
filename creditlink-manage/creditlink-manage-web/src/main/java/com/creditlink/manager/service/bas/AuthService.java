/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.bas;

import com.creditlink.manager.bean.AuthCheckBean;

/**
 * 权限
 * 
 * @version 2018年1月30日上午9:15:44
 * @author wuliu
 */
public interface AuthService {
    
    /**
     * 权限校验判断
     * 
     * @version 2018年1月30日上午9:19:28
     * @author wuliu
     * @param checkBean
     * @return
     */
    public Boolean authCheck(AuthCheckBean checkBean);
}
