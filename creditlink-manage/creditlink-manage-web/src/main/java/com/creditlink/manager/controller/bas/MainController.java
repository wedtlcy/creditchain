/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.bas;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuInfoPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.Tree;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.service.bas.BasUserInfoService;
import com.creditlink.manager.service.sys.SysMenuInfoService;
import com.creditlink.manager.util.MD5Utils;

/**
 * 主界面Controller
 * @version 2018年1月11日下午3:32:33
 * @author liuheng
 */
@Controller
@RequestMapping("/main")
public class MainController {
    
    private static Logger log = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
    private SysMenuInfoService sysMenuInfoService;
    
    @Autowired
    private BasUserInfoService basUserInfoService;
    
    /**
     * 
     * 主界面
     * @version 2018年1月11日下午3:43:50
     * @author liuheng
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/mainPanel")
    public ModelAndView mainPanel(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            BasUserInfoPo user = (BasUserInfoPo) session.getAttribute(LoginConstants.LOGIN_USER);
            modelAndView.addObject(LoginConstants.LOGIN_USER_NAME, user.getUserName());
            modelAndView.setViewName("/main/main");
        }
        catch (Exception e) {
            log.error("xxxxxx跳转主页面时系统异常: ", e);
            // 跳转至登陆页面
            modelAndView.setViewName("/login/login");
        }
        return modelAndView;
    }
    
    /**
     * 
     * 菜单
     * @version 2018年1月11日下午5:09:03
     * @author liuheng
     * @param session
     * @return
     */
    @RequestMapping("/menu")
    @ResponseBody
    public List<Tree> menu(HttpSession session) {
        List<Tree> treeList = new ArrayList<Tree>();
        try {
            BasUserInfoPo user = (BasUserInfoPo) session.getAttribute(LoginConstants.LOGIN_USER);
            List<SysMenuInfoPo> menuList = sysMenuInfoService.getUserMenuInfo(user.getUserId());
            treeList = sysMenuInfoService.menuListToTreeList(menuList);
        }
        catch (Exception e) {
            log.error("xxxxxx获取主界面菜单系统异常: ", e);
        }
        return treeList;
    }
    
    /**
     * 
     * 修改密码页面
     * @version 2018年2月7日上午9:52:45
     * @author liuheng
     * @return
     */
    @RequestMapping("/modifyPwdView")
    public String modifyPwdView() {
        return "/main/modifyPwd";
    }
    
    /**
     * 
     * 修改密码
     * @version 2018年2月7日上午10:13:20
     * @author liuheng
     * @param request
     * @return
     */
    @RequestMapping("/modifyPwd")
    @ResponseBody
    public AjaxReturnMsg modifyPwd(HttpServletRequest request){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            String oldPwd = request.getParameter("oldPwd");
            String newPwd = request.getParameter("newPwd");
            String newPwd2 = request.getParameter("newPwd2");
            if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) 
                    || StringUtils.isEmpty(newPwd2)) {
                log.info("修改密码->输入的密码不能为空！");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("输入的密码不能为空！");
                return msg;
            }
            if(!newPwd.equals(newPwd2)) {
                log.info("修改密码->两次输入的新密码不一致！");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("两次输入的新密码不一致！");
                return msg;
            }
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            BasUserInfoPo entity = new BasUserInfoPo();
            entity.setUserId(loginUser.getUserId());
            BasUserInfoPo info = basUserInfoService.find(entity);
            if(!info.getUserPwd().equals(MD5Utils.md5Lower32(oldPwd))) {
                log.info("修改密码->输入的旧密码不正确！");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("输入的旧密码不正确！");
                return msg;
            }
            info.setUserPwd(MD5Utils.md5Lower32(newPwd));
            basUserInfoService.update(info, true);
            request.getSession().setAttribute(LoginConstants.LOGIN_USER, info);
        }
        catch (Throwable e) {
            log.error("xxxxxx修改密码失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 没有权限页面
     * 
     * @version 2018年1月11日下午3:43:50
     * @author liuheng
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/noAuth")
    public ModelAndView noAuth(HttpServletRequest request, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/noAuth");
        return modelAndView;
    }
    
    /**
     * md5加密
     * 
     * @version 2018年1月11日下午3:43:50
     * @author liuheng
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/md5")
    @ResponseBody
    public AjaxReturnMsg md5(HttpServletRequest request, HttpSession session) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            String data = request.getParameter("data");
            if(StringUtils.isBlank(data)){
                log.info("加密数据不能为空!");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("加密数据不能为空！");
                return msg;
            }
            String md5Data = MD5Utils.md5Lower32(data);
            msg.setResult(md5Data);
        }
        catch (Throwable e) {
            log.error("xxxxxx加密数据失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
}
