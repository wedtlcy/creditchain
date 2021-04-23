/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.cfg;

import com.creditlink.bean.po.BasePo;

/**
 * 字典
 * 
 * @version 2017年12月7日下午6:12:18
 * @author wuliu
 */
public class CfgDictPo extends BasePo {
    
    private static final long serialVersionUID = -6146771945069326004L;
    
    private Long id;
    /**
     * 代码
     */
    private String code;
    /**
     * 描述
     */
    private String desc;
    /**
     * KEY
     */
    private String key;
    /**
     * VALUE
     */
    private String value;
    /**
     * VALUE1
     */
    private String value1;
    /**
     * VALUE2
     */
    private String value2;
    /**
     * VALUE3
     */
    private String value3;
    /**
     * 状态
     */
    private Integer status;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue1() {
        return value1;
    }
    
    public void setValue1(String value1) {
        this.value1 = value1;
    }
    
    public String getValue2() {
        return value2;
    }
    
    public void setValue2(String value2) {
        this.value2 = value2;
    }
    
    public String getValue3() {
        return value3;
    }
    
    public void setValue3(String value3) {
        this.value3 = value3;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
