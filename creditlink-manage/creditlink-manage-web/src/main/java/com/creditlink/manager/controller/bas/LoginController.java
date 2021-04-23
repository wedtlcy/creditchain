/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.bas;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.DBConstants;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.constants.RedirectUrlConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.bas.BasUserInfoService;
import com.creditlink.manager.util.ImageUtil;
import com.creditlink.manager.util.MD5Utils;

/**
 * 用户登录
 * @version 2018年1月10日上午9:37:57
 * @author liuheng
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private BasUserInfoService basUserInfoService;
    
    /**
     * 
     * 登录页面
     * @version 2018年1月10日上午9:46:06
     * @author liuheng
     * @return
     */
    @RequestMapping("/loginView")
    public ModelAndView loginView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login/login");
        return modelAndView;
    }
    
    /**
     * 
     * 用户登录
     * @version 2018年1月10日上午10:02:05
     * @author liuheng
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String verifyCode = request.getParameter("verifyCode");
            log.info("用户登录开始->用户名:{},MD5密码:{},验证码:{},访问IP:{}", username, MD5Utils.md5Lower32(password), 
                    verifyCode, getIpAddr(request));
            if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                log.info("用户登录失败->用户名或密码不能为空");
                modelAndView.addObject(LoginConstants.LOGIN_ERROR_KEY, LoginConstants.NULL_USERNAME_OR_PASSWORD);
                return modelAndView;
            }
            if(StringUtils.isBlank(verifyCode)) {
                log.info("用户登录失败->验证码不能为空");
                modelAndView.addObject(LoginConstants.LOGIN_ERROR_KEY, LoginConstants.NULL_VERIFY_CODE);
                return modelAndView;
            }
            if(!verifyCode.equals(session.getAttribute("captcha"))) {
                log.info("用户登录失败->验证码错误");
                modelAndView.addObject(LoginConstants.LOGIN_ERROR_KEY, LoginConstants.WRONG_VERIFY_CODE);
                return modelAndView;
            }
            BasUserInfoPo userInfoPo = new BasUserInfoPo();
            userInfoPo.setUserName(username);
            userInfoPo.setUserPwd(MD5Utils.md5Lower32(password));
            userInfoPo.setUserStatus(DBConstants.STATUS_1);
            log.info("登录用户参数:{}", userInfoPo);
            //查询用户信息
            List<BasUserInfoPo> basUserInfoPos = basUserInfoService.findAll(userInfoPo);
            if(basUserInfoPos == null || basUserInfoPos.isEmpty()){
                log.info("用户登录失败->用户名或密码错误");
                modelAndView.addObject(LoginConstants.LOGIN_ERROR_KEY, LoginConstants.WRONG_USERNAME_OR_PASSWORD);
                return modelAndView;
            }
            int size = basUserInfoPos.size();
            log.info("查询用户数目:{}", size);
            if(size > 1){
                log.error("xxxxxx用户登录失败->数据异常,存在多条数据,用户名:{},密码:{}",username,password);
                modelAndView.addObject(LoginConstants.LOGIN_ERROR_KEY, LoginConstants.WRONG_USERNAME_OR_PASSWORD);
                return modelAndView;
            }
            userInfoPo = basUserInfoPos.get(0);
            session.setAttribute(LoginConstants.LOGIN_USER, userInfoPo);
            log.info("用户登录成功");
            //登录成功跳转主页面
            modelAndView.setViewName(RedirectUrlConstants.USER_MAINPANEL);
        }
        catch (Exception e) {
            log.error("xxxxxx用户登录异常: ", e);
            modelAndView.addObject(LoginConstants.LOGIN_ERROR_KEY, Const.AJAX_SYS_ERROR_N_MSG);
        }
        return modelAndView;
    }
    
    /**
     * 
     * 登录验证码图片
     * @version 2017年12月25日下午5:18:26
     * @author wuliu
     * @param session
     * @param response
     */
    @RequestMapping("/verifyCodeImg")
    public void genCaptcha(HttpSession session, HttpServletResponse response){
        int CAPTCHA_LENGTH = 4; // 验证码长度
        String captcha = "";
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captcha += (new Random()).nextInt(10);
        }
        BufferedImage img = generateCaptchaImg(captcha);
        session.setAttribute("captcha", captcha);
        try {
            // 发送图片
            ImageIO.write(img, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            log.error("xxxxxx生成验证码异常",e);
        }
    }
    
    /**
     * 
     * 生产验证码图片
     * @version 2018年1月10日上午9:57:45
     * @author liuheng
     * @param captcha
     * @return
     */
    private BufferedImage generateCaptchaImg(String captcha) {
        int width = 60;
        int height = 30;
        return ImageUtil.generateCaptchaImg(captcha, width, height, null, null);
    }
    
    /**
     * 
     * 退出登陆
     * @version 2018年1月11日下午4:18:48
     * @author liuheng
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        // 删除用户Session
        session.removeAttribute(LoginConstants.LOGIN_USER);
        try {
            response.sendRedirect(request.getContextPath() + LoginConstants.LOGIN_URL);
        }
        catch (IOException e) {
            log.error("xxxxxx退出登录重定向至登录页面异常: ", e);
        }
    }
}
