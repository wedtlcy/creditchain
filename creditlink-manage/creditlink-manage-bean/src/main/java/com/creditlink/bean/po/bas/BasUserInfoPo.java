/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.po.bas;

import com.creditlink.bean.po.BasePo;

/**
 * 用户信息表
 * 
 * @version 2017年12月5日下午3:05:13
 * @author wuliu
 */
public class BasUserInfoPo extends BasePo {
    
    private static final long serialVersionUID = -2422327061024253985L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPwd;
    /**
     * 用户状态 0:禁用 1:启用
     */
    private Integer userStatus;
    /**
     * 是否是联盟成员 0:否 1:是
     */
    private Integer isMember;
    /**
     * 联盟成员ID
     */
    private Integer memberId;
    /**
     * 联系电话
     */
    private String userMobile;
    /**
     * 邮箱
     */
    private String userEmail;
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserPwd() {
        return userPwd;
    }
    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    
    public Integer getUserStatus() {
        return userStatus;
    }
    
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    
    public Integer getIsMember() {
        return isMember;
    }
    
    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }
    
    public String getUserMobile() {
        return userMobile;
    }
    
    public Integer getMemberId() {
        return memberId;
    }
    
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
