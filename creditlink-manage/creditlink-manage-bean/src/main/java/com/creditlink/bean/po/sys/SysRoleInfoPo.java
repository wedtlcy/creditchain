/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.sys;

import com.creditlink.bean.po.BasePo;

/**
 * 角色信息表
 * 
 * @version 2017年12月5日下午3:25:15
 * @author wuliu
 */
public class SysRoleInfoPo extends BasePo {
    
    private static final long serialVersionUID = 7550244091531059465L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDesc;
    /**
     * 父角色ID
     */
    private Long roleParId;
    /**
     * 状态 0:警用；1:启用；
     */
    private Integer status;
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleDesc() {
        return roleDesc;
    }
    
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    
    public Long getRoleParId() {
        return roleParId;
    }
    
    public void setRoleParId(Long roleParId) {
        this.roleParId = roleParId;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
