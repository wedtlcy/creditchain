/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.bean.query;

import java.io.Serializable;

/**
 * 用户管理查询参数Bean
 * 
 * @version 2017年12月8日下午12:54:31
 * @author wuliu
 */
public class UserManagerQueryBean implements Serializable{
    
    private static final long serialVersionUID = 3089612676611420148L;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户状态 0:禁用 1:启用
     */
    private Integer userStatus;
    /**
     * 联盟成员ID
     */
    private Integer memberId;
    /**
     * 是否联盟成员 0:否 1:是
     */
    private Integer isMember;
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getUserStatus() {
        return userStatus;
    }
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    public Integer getMemberId() {
        return memberId;
    }
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    public Integer getIsMember() {
        return isMember;
    }
    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }
}
