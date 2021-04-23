/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * IndexController
 * 
 * @version 2018年1月19日上午9:01:44
 * @author wuliu
 */
@Controller
public class IndexController extends BaseController {
    
    /**
     * 登录页面
     * 
     * @version 2018年1月19日上午10:38:23
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping({"/"})
    public ModelAndView loginView(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("/login/login");
    }
}
