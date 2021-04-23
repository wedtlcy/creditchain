/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.sys;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuInfoPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.bean.query.MenuManagerQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.sys.SysMenuInfoService;

/**
 * 
 * 菜单维护
 * @version 2017年12月8日上午11:10:26
 * @author wuliu
 */
@Controller
@RequestMapping(value = "/menuManage")
public class SysMenuController extends BaseController{
    
    private static final Logger log = LoggerFactory.getLogger(SysMenuController.class);
    
    @Autowired
    private SysMenuInfoService sysMenuInfoService;
    
    /**
     * 
     * 管理菜单页面
     * @version 2018年2月5日上午9:27:45
     * @author liuheng
     * @return
     */
    @RequestMapping("/manageView")
    public String manageView() {
        return "sys/menu/menuManage";
    }
    
    /**
     * 
     * 查询菜单信息
     * @version 2018年2月5日上午9:27:55
     * @author liuheng
     * @param request
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public DataGridResBean menuManageQuery(HttpServletRequest request,MenuManagerQueryBean bean,Long page, Long rows) {
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        sysMenuInfoService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 
     * 更新菜单状态
     * @version 2018年2月5日上午9:28:06
     * @author liuheng
     * @param request
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public AjaxReturnMsg updateStatus(HttpServletRequest request) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("启用菜单状态信息ids:{},status:{}", ids, status);
            //校验
            checkStatus(msg, ids, status);
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),loginUser);
            sysMenuInfoService.updateStatusBatch(updateStatusBatchBean);
        }
        catch (Throwable e) {
            log.error("xxxxxx启用菜单失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 
     * 添加菜单页面
     * @version 2018年2月5日上午9:29:19
     * @author liuheng
     * @return
     */
    @RequestMapping("/addMenuView")
    public String addView(HttpServletRequest request) {
        String flag = request.getParameter("flag");
        String url = "";
        // 添加一级菜单页面
        if("1".equals(flag)) {
            url = "sys/menu/addTopMenu";
        }
        // 添加非一级菜单页面
        else if("2".equals(flag)) {
            url = "sys/menu/addMenu";
        }
        return url;
    }
    
    /**
     * 
     * 添加按钮页面
     * @version 2018年2月5日上午9:29:32
     * @author liuheng
     * @return
     */
    @RequestMapping("/addButtonView")
    public String addButtonView() {
        return "sys/menu/addButton";
    }
    
    /**
     * 
     * 添加菜单
     * @version 2018年2月5日上午9:30:22
     * @author liuheng
     * @param info
     * @return
     */
    @RequestMapping("/addMenu")
    @ResponseBody
    public AjaxReturnMsg addMenu(SysMenuInfoPo info, HttpServletRequest request) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            // 菜单类型:菜单
            info.setMenuType("0");
            // 菜单状态:启用
            info.setStatus(1);
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            info.setCreateTime(new Date());
            info.setCreateUser(loginUser.getUserName());
            info.setMenuId(sysMenuInfoService.getMaxMenuId(info.getMenuParId()));
            sysMenuInfoService.insert(info);
        }
        catch (Throwable e) {
            log.error("xxxxxx添加菜单失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 
     * 添加按钮
     * @version 2018年2月5日上午9:30:41
     * @author liuheng
     * @param info
     * @return
     */
    @RequestMapping("/addButton")
    @ResponseBody
    public AjaxReturnMsg addButton(SysMenuInfoPo info, HttpServletRequest request) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            // 菜单类型:按钮
            info.setMenuType("1");
            // 菜单状态:启用
            info.setStatus(1);
            // 是否叶子节点
            info.setIsLeaf("1");
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            info.setCreateTime(new Date());
            info.setCreateUser(loginUser.getUserName());
            info.setMenuId(sysMenuInfoService.getMaxMenuId(info.getMenuParId()));
            sysMenuInfoService.insert(info);
        }
        catch (Throwable e) {
            log.error("xxxxxx添加按钮失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 
     * 修改菜单页面
     * @version 2018年2月5日上午9:30:57
     * @author liuheng
     * @return
     */
    @RequestMapping("/updateView")
    public String updateView(SysMenuInfoPo info, HttpServletRequest request) {
        request.setAttribute("menu", sysMenuInfoService.find(info));
        return "sys/menu/update";
    }
    
    /**
     * 
     * 修改菜单
     * @version 2018年2月5日上午10:09:54
     * @author liuheng
     * @param info
     * @return
     */
    @RequestMapping("/updateMenu")
    @ResponseBody
    public AjaxReturnMsg updateMenu(SysMenuInfoPo info, HttpServletRequest request) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            info.setUpdateUser(loginUser.getUserName());
            info.setUpdateTime(new Date());
            sysMenuInfoService.update(info, true);
        }
        catch (Throwable e) {
            log.error("xxxxxx修改菜单失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
}
