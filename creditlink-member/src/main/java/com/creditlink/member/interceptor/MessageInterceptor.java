/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.member.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.member.util.Constants;
import com.google.gson.Gson;

/**
 * 拦截器
 * 
 * @version 2017年12月26日上午10:55:15
 * @author wuliu
 */
public class MessageInterceptor implements HandlerInterceptor{

    private static Logger log = LoggerFactory.getLogger(MessageInterceptor.class);
    private Gson gson = new Gson();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        Map<String, Object> bodyMap = (Map<String, Object>) request.getAttribute(Constants.RSP_BODY);
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("body", bodyMap);
        response(response, rspMap);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
      
    }
    
    private void response(HttpServletResponse response, Map<String, Object> msg) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
        try {
            out = response.getWriter();
            out.print(gson.toJson(msg));
            out.flush();
            out.close();
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
