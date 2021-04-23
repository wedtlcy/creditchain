/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuInfoPo;
import com.creditlink.bean.po.sys.SysMenuRoleRelPo;
import com.creditlink.bean.po.sys.SysRoleInfoPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.Tree;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.bean.query.RoleManagerQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.sys.SysMenuInfoService;
import com.creditlink.manager.service.sys.SysMenuRoleRelService;
import com.creditlink.manager.service.sys.SysRoleInfoService;

/**
 * 角色管理
 * 
 * @version 2017年12月8日上午11:11:29
 * @author wuliu
 */
@Controller
@RequestMapping(value = "/roleManage")
public class SysRoleController extends BaseController {
    
    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);
    
    @Autowired
    private SysRoleInfoService sysRoleInfoService;
    
    @Autowired
    private SysMenuInfoService sysMenuInfoService;
    
    @Autowired
    private SysMenuRoleRelService sysMenuRoleRelService;
    
    /**
     * 
     * 管理角色页面
     * @version 2018年2月5日下午4:08:19
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/manageView")
    public String manageView() {
        return "sys/role/roleManage";
    }
    
    /**
     * 
     * 查询角色信息
     * @version 2018年2月5日下午4:08:10
     * @author liuheng
     * @param request
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public DataGridResBean query(HttpServletRequest request, RoleManagerQueryBean bean,
            Long page, Long rows) {
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        sysRoleInfoService.loadDataGrid(res, req);
        return res;
    }
    
    /**
     * 
     * 更新角色状态
     * @version 2018年2月5日下午4:08:00
     * @author liuheng
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public AjaxReturnMsg updateStatus(HttpServletRequest request){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("启用角色状态信息ids:{},status:{}", ids, status);
            //校验
            checkStatus(msg, ids, status);
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),loginUser);
            sysRoleInfoService.updateStatusBatch(updateStatusBatchBean);
        }
        catch (Throwable e) {
            log.error("xxxxxx启用角色失败", e);
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
     * @version 2018年2月5日下午4:07:33
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/addView")
    public String addView() {
        return "sys/role/add";
    }
    
    /**
     * 
     * 更新菜单页面
     * @version 2018年2月5日下午4:07:23
     * @author liuheng
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateView")
    public String updateView(SysRoleInfoPo info, HttpServletRequest request) {
        request.setAttribute("role", sysRoleInfoService.find(info));
        return "sys/role/update";
    }
    
    /**
     * 
     * 匹配菜单页面
     * @version 2018年2月5日下午4:07:14
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/matchMenuView")
    public String matchMenuView() {
        return "sys/role/matchMenu";
    }
    
    /**
     * 
     * 查询所有启用的菜单
     * @version 2018年2月5日下午4:07:04
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/matchMenuList")
    @ResponseBody
    public List<Tree> matchMenuList(HttpServletRequest request) {
        List<Tree> treeList = new ArrayList<Tree>();
        try {
            SysMenuInfoPo info = new SysMenuInfoPo();
            info.setStatus(1);
            // 所有菜单
            List<SysMenuInfoPo> menuList = sysMenuInfoService.findAll(info);
            long roleId = Long.valueOf(request.getParameter("roleId"));
            SysMenuRoleRelPo entity = new SysMenuRoleRelPo();
            entity.setRoleId(roleId);
            entity.setStatus(1);
            // 用户授权菜单
            List<SysMenuRoleRelPo> relList = sysMenuRoleRelService.findAll(entity);
            // 遍历出所有已经授权的菜单
            for(SysMenuRoleRelPo relPo: relList) {
                for(SysMenuInfoPo po: menuList) {
                    if(po.getMenuId().longValue() == relPo.getMenuId().longValue()) {
                        po.setChecked(true);
                        break;
                    }
                }
            }
            treeList = sysMenuInfoService.menuListToTreeList(menuList);
        }
        catch (Throwable e) {
            log.error("xxxxxx获取关联菜单列表失败 ", e);
        }
        return treeList;
    }
    
    /**
     * 
     * 关联菜单
     * @version 2018年2月5日下午4:06:56
     * @author liuheng
     * @param request
     * @return
     */
    @RequestMapping(value = "/matchMenu")
    @ResponseBody
    public AjaxReturnMsg matchMenu(HttpServletRequest request) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            long roleId = Long.valueOf(request.getParameter("roleId"));
            String[] menuIds = request.getParameterValues("menuIds[]");
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            // 传来的menuIds[]参数为空时,menuIds字符串数组为空
            if (menuIds == null) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("roleId", roleId);
                params.put("ids", "");
                params.put("oldStatus", 1);
                params.put("newStatus", 0);
                params.put("updateUser", loginUser.getUserName());
                params.put("updateTime", new Date());
                sysMenuRoleRelService.updateStatusBatch(params);
                msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
                msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
                return msg;
            }
            List<Long> menuIdList = new ArrayList<Long>();
            for (String menuId : menuIds) {
                menuIdList.add(Long.valueOf(menuId));
            }
            sysMenuRoleRelService.saveMenuRoleRels(roleId, menuIdList, loginUser);
        }
        catch (Throwable e) {
            log.error("xxxxxx角色关联菜单失败", e);
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
     * 添加角色
     * @version 2018年2月5日下午4:06:42
     * @author liuheng
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/addRole")
    @ResponseBody
    public AjaxReturnMsg addRole(SysRoleInfoPo info, HttpServletRequest request) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            // 角色状态:启用
            info.setStatus(1);
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            info.setCreateTime(new Date());
            info.setCreateUser(loginUser.getUserName());
            sysRoleInfoService.insert(info);
        }
        catch (Throwable e) {
            log.error("xxxxxx添加角色失败", e);
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
     * 更新角色
     * @version 2018年2月5日下午4:06:32
     * @author liuheng
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateRole")
    @ResponseBody
    public AjaxReturnMsg updateRole(SysRoleInfoPo info, HttpServletRequest request) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            info.setUpdateTime(new Date());
            info.setUpdateUser(loginUser.getUserName());
            sysRoleInfoService.update(info, true);
        }
        catch (Throwable e) {
            log.error("xxxxxx修改角色失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
}
