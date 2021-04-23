/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.bas;

import com.creditlink.bean.po.BasePo;

/**
 * 联盟成员信息表
 * 
 * @version 2017年12月6日下午2:02:09
 * @author wuliu
 */
public class BasMemberInfoPo extends BasePo {
    
    private static final long serialVersionUID = 2511636509967428286L;
    /**
     * 客户ID
     */
    private Long custId;
    /**
     * 客户姓名
     */
    private String custName;
    /**
     * 客户简称
     */
    private String custSname;
    /**
     * 公司地址
     */
    private String addr;
    /**
     * 公司电话
     */
    private String mobile;
    /**
     * 工商注册名称
     */
    private String registName;
    /**
     * 组织机构代码
     */
    private String orgCode;
    /**
     * 营业执照号
     */
    private String regNo;
    /**
     * 税务登记证号
     */
    private String taxRegistCode;
    /**
     * 经营内容
     */
    private String descInfo;
    /**
     * 客户联系人姓名
     */
    private String contactName;
    /**
     * 客户联系人电话
     */
    private String contactTel;
    /**
     * 客户联系人Email
     */
    private String contactEmail;
    /**
     * 状态
     */
    private String status;
    
    public Long getCustId() {
        return custId;
    }
    
    public void setCustId(Long custId) {
        this.custId = custId;
    }
    
    public String getCustName() {
        return custName;
    }
    
    public void setCustName(String custName) {
        this.custName = custName;
    }
    
    public String getCustSname() {
        return custSname;
    }
    
    public void setCustSname(String custSname) {
        this.custSname = custSname;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAddr() {
        return addr;
    }
    
    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getRegistName() {
        return registName;
    }
    
    public void setRegistName(String registName) {
        this.registName = registName;
    }
    
    public String getOrgCode() {
        return orgCode;
    }
    
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    
    public String getRegNo() {
        return regNo;
    }
    
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
    
    public String getTaxRegistCode() {
        return taxRegistCode;
    }
    
    public void setTaxRegistCode(String taxRegistCode) {
        this.taxRegistCode = taxRegistCode;
    }
    
    public String getDescInfo() {
        return descInfo;
    }
    
    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }
    
    public String getContactName() {
        return contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String getContactTel() {
        return contactTel;
    }
    
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }
    
    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
}
