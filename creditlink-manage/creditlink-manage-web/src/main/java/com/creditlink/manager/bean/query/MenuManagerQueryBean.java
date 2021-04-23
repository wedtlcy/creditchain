/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean.query;

import java.io.Serializable;

/**
 * 菜单管理查询参数Bean
 * 
 * @version 2017年12月8日下午12:54:31
 * @author wuliu
 */
public class MenuManagerQueryBean implements Serializable{
    
    private static final long serialVersionUID = 3089612676611420148L;
    
    /**
     * 菜单编号
     */
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单url
     */
    private String menuUrl;
    /**
     * 菜单类型 0:菜单; 1:按钮;
     */
    private String menuType;
    /**
     * 状态 0:禁用; 1:启用;
     */
    private Integer status;
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
