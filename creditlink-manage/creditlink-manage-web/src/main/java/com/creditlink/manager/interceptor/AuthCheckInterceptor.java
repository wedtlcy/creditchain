/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.manager.bean.AuthCheckBean;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.service.bas.AuthService;

/**
 * 登录拦截器
 * @version 2018年1月10日下午2:12:49
 * @author liuheng
 */
public class AuthCheckInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthCheckInterceptor.class);
    
    @Autowired
    private AuthService authService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //获取登录用户
        if(request.getSession().getAttribute(LoginConstants.LOGIN_USER) == null) {
            response.sendRedirect(request.getContextPath() + LoginConstants.LOGIN_URL);
            return false;
        }else{
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            String url = request.getServletPath();
            AuthCheckBean authCheckBean = new AuthCheckBean();
            authCheckBean.setUserId(userInfLogin.getUserId());
            authCheckBean.setUrl(url);
            Boolean flag = authService.authCheck(authCheckBean);
            log.info("操作权限判断是否存在操作权限:{}", flag);
            if(!flag){
                response.sendRedirect(request.getContextPath() + LoginConstants.NO_AUTH_URL);
                return false;
            }else{
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
