/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.sys;

import java.util.List;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysUserRoleRelPo;
import com.creditlink.manager.service.BaseService;

/**
 * SysUserRoleRelService
 * @version 2017年12月5日下午5:24:13
 * @author wuliu
 */
public interface SysUserRoleRelService extends BaseService<SysUserRoleRelPo>{
    
    /**
     * 查询(所有)
     * 
     * @version 2017年12月5日下午3:45:08
     * @author wuliu
     * @param info
     * @return
     */
    public List<SysUserRoleRelPo> findAll(SysUserRoleRelPo info);
    
    /**
     * 
     * 保存用户角色关联信息
     * @version 2018年2月6日下午3:52:10
     * @author liuheng
     * @param info
     * @return
     */
    public void saveUserRoleRel(Long userId, String roleIds, BasUserInfoPo loginUser);

}
