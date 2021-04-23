/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.bas.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditlink.dao.sys.SysMenuInfoDao;
import com.creditlink.manager.bean.AuthCheckBean;
import com.creditlink.manager.service.bas.AuthService;

/**
 * AuthServiceImpl
 * 
 * @version 2018年1月30日上午9:16:09
 * @author wuliu
 */
@Service
public class AuthServiceImpl implements AuthService{
    
    @Autowired
    private SysMenuInfoDao sysMenuInfoDao;
    
    @Override
    public Boolean authCheck(AuthCheckBean checkBean) {
          HashMap<String, Object> map = new HashMap<String, Object>();
          map.put("userId", checkBean.getUserId());
          List<String> list = sysMenuInfoDao.getUserAuthUrl(map);
          if(list == null || list.isEmpty()){
              return false;
          }else{
              if(list.contains(checkBean.getUrl())){
                  return true;
              }else{
                  return false;
              }
          }
    }
}
