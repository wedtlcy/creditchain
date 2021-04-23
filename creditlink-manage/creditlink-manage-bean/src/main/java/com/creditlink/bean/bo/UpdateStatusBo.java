/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.bean.bo;

import java.util.Date;

/**
 * UpdateStatusBo状态修改bo
 * 
 * @version 2017年12月8日下午3:58:06
 * @author wuliu
 */
public class UpdateStatusBo extends BaseBo{

    private static final long serialVersionUID = -2561352668043183410L;
    /**
     * 编号
     */
    private Integer id;
    /**
     * 编号集合
     */
    private String ids;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 老状态
     */
    private Integer oldStatus;
    /**
     * 新状态
     */
    private Integer newStatus;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getIds() {
        return ids;
    }
    public void setIds(String ids) {
        this.ids = ids;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getOldStatus() {
        return oldStatus;
    }
    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }
    public Integer getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }
    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
}
