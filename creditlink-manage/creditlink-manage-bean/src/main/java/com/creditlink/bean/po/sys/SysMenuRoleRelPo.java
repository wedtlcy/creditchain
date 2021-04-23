/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.sys;

import com.creditlink.bean.po.BasePo;

/**
 * 菜单角色关联表
 * 
 * @version 2017年12月5日下午3:25:15
 * @author wuliu
 */
public class SysMenuRoleRelPo extends BasePo {
    
    private static final long serialVersionUID = 8090941510905145643L;
    /**
     * 主键，自增ID
     */
    private Long id;
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 状态 0:警用；1:启用；
     */
    private Integer status;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
