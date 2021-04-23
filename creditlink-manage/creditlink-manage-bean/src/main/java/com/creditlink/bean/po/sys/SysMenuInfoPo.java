/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.sys;

import com.creditlink.bean.po.BasePo;

/**
 * 菜单信息表
 * 
 * @version 2017年12月5日下午3:21:32
 * @author wuliu
 */
public class SysMenuInfoPo extends BasePo {
    
    private static final long serialVersionUID = -4368404093229168250L;
    
    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 菜单父编号
     */
    private Long menuParId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单URL
     */
    private String menuUrl;
    /**
     * 菜单类型 0:菜单; 1:按钮;
     */
    private String menuType;
    /**
     * 菜单英文代码
     */
    private String menuCode;
    /**
     * 状态 0:警用; 1:启用;
     */
    private Integer status;
    /**
     * 是否叶子结点 0:否; 1:是;
     */
    private String isLeaf;
    /**
     * 菜单是否被角色授权 false:否; 1:是;
     */
    private Boolean checked = false; 
    
    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getMenuParId() {
        return menuParId;
    }
    public void setMenuParId(Long menuParId) {
        this.menuParId = menuParId;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public String getMenuType() {
        return menuType;
    }
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
    public String getMenuCode() {
        return menuCode;
    }
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getIsLeaf() {
        return isLeaf;
    }
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
