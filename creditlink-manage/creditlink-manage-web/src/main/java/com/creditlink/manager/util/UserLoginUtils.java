package com.creditlink.manager.util;

import javax.servlet.http.HttpServletRequest;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.manager.constants.LoginConstants;

/**
 * 用户登录工具类
 * 
 * @version 2017年12月19日下午5:58:16
 * @author wuliu
 */
public class UserLoginUtils {

    /**
     * 获取用户登录信息
     * 
     * @version 2017年12月19日下午5:58:34
     * @author wuliu
     * @param request
     * @return
     */
	public static BasUserInfoPo getLoginUserInfo(HttpServletRequest request){
	    BasUserInfoPo basInfo = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
		return basInfo;
	}
}
