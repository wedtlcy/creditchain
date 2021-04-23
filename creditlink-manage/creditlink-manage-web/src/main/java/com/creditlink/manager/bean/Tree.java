/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 菜单树
 * 
 * @version 2017年12月11日下午2:34:16
 * @author wuliu
 */
public class Tree implements Serializable {
    
    private static final long serialVersionUID = 2059468068607832251L;
    
    /**
     * 菜单ID
     */
    private Long id;
    
    /**
     * 父菜单ID
     */
    @JsonIgnore
    private Long menuParId;
    
    /**
     * 菜单名称
     */
    private String text;
    
    /**
     * 单选框是否选中(关联菜单页面)
     */
    private Boolean checked;
    
    /**
     * 默认展开/关闭状态
     */
    private String state;
    
    /**
     * 菜单链接地址
     */
    private String url;
    
    /**
     * 菜单图标
     */
    private String iconCls;
    
    /**
     * 其他属性
     */
    private Map<String, String> attributes = new HashMap<String, String>();
    
    /**
     * 子菜单
     */
    private List<Tree> children = new ArrayList<Tree>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuParId() {
        return menuParId;
    }

    public void setMenuParId(Long menuParId) {
        this.menuParId = menuParId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }
}
