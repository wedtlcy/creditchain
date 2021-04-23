/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean.query;

import java.io.Serializable;

/**
 * 角色管理查询
 * 
 * @version 2017年12月8日下午2:39:41
 * @author wuliu
 */
public class RoleManagerQueryBean implements Serializable {
    
    private static final long serialVersionUID = -4030788220159845880L;
    
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 状态 0:警用；1:启用；
     */
    private Integer status;
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
}
