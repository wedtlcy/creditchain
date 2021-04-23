/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.manager.controller.BaseController;

/**
 * IndexController
 * @version 2018年2月23日上午9:47:01
 * @author wuliu
 */
@Controller
@RequestMapping("/index")
public class IndexManagerController extends BaseController{
    
    private static final Logger log = LoggerFactory.getLogger(IndexManagerController.class);
    
    /**
     * 索引初始化页面初始化
     * 
     * @version 2018年1月23日下午5:35:13
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/indexMd5Init")
    public ModelAndView fourElementInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index/indexMd5Init");
        return modelAndView;
    }
}
