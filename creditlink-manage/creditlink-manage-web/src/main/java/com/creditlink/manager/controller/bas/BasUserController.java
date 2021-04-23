/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.bas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditlink.bean.po.bas.BasMemberInfoPo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysUserRoleRelPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.bean.query.MemberManagerQueryBean;
import com.creditlink.manager.bean.query.RoleManagerQueryBean;
import com.creditlink.manager.bean.query.UserManagerQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.bas.BasMemberInfoService;
import com.creditlink.manager.service.bas.BasUserInfoService;
import com.creditlink.manager.service.sys.SysRoleInfoService;
import com.creditlink.manager.service.sys.SysUserRoleRelService;
import com.creditlink.manager.util.MD5Utils;

/**
 * 用户管理
 * @version 2017年12月8日下午12:29:40
 * @author wuliu
 */
@Controller
@RequestMapping(value = "/userManage")
public class BasUserController extends BaseController{
    
    private static final Logger log = LoggerFactory.getLogger(BasUserController.class);
    
    @Autowired
    private BasUserInfoService basUserInfoService;
    
    @Autowired
    private BasMemberInfoService basMemberInfoService;
    
    @Autowired
    private SysRoleInfoService sysRoleInfoService;
    
    @Autowired
    private SysUserRoleRelService sysUserRoleRelService;
    
    /**
     * 
     * 用户管理页面
     * @version 2018年1月31日下午3:06:24
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/manageView")
    public String userManager() {
        return "bas/user/userManage";
    }
    
    /**
     * 
     * 查询用户数据
     * @version 2018年1月31日下午3:06:13
     * @author liuheng
     * @param request
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public DataGridResBean userManagerQuery(HttpServletRequest request,UserManagerQueryBean bean,Long page, Long rows) {
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        basUserInfoService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 
     * 更新用户状态
     * @version 2018年1月31日下午3:06:03
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
            log.info("启用用户状态信息ids:{},status:{}", ids, status);
            //校验
            checkStatus(msg, ids, status);
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),userInfLogin);
            basUserInfoService.updateStatusBatch(updateStatusBatchBean);
        }
        catch (Throwable e) {
            log.error("xxxxxx启用用户失败", e);
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
     * 添加用户页面
     * @version 2018年1月31日下午3:05:54
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/addView")
    public String addView(){
        return "bas/user/add";
    }
    
    /**
     * 
     * 选择联盟成员页面
     * @version 2018年1月31日下午3:05:44
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/chooseMemberView")
    public String chooseMemberView(){
        return "bas/user/chooseMember";
    }
    
    /**
     * 
     * 查询联盟成员
     * @version 2018年2月6日下午2:45:52
     * @author liuheng
     * @param request
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/queryMember")
    @ResponseBody
    public DataGridResBean queryMember(HttpServletRequest request, MemberManagerQueryBean bean, 
            Long page, Long rows){
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        basMemberInfoService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 
     * 添加用户
     * @version 2018年1月31日下午3:05:34
     * @author liuheng
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/addUser")
    @ResponseBody
    public AjaxReturnMsg addUser(BasUserInfoPo info, HttpServletRequest request){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            String userName = info.getUserName();
            BasUserInfoPo entity = new BasUserInfoPo();
            entity.setUserName(userName);
            BasUserInfoPo userInfo = basUserInfoService.find(entity);
            if(userInfo != null) {
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("该用户名已存在！");
                return msg;
            }
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            info.setUserPwd(MD5Utils.md5Lower32("123456"));
            info.setUserStatus(1);
            info.setCreateTime(new Date());
            info.setCreateUser(loginUser.getUserName());
            basUserInfoService.insert(info);
        }
        catch (Throwable e) {
            log.error("xxxxxx添加用户失败", e);
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
     * 更新用户页面
     * @version 2018年1月31日下午3:05:16
     * @author liuheng
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateView")
    public String updateView(HttpServletRequest request){
        BasUserInfoPo userInfo = new BasUserInfoPo();
        BasMemberInfoPo memberInfo = new BasMemberInfoPo();
        try {
            BasUserInfoPo entity = new BasUserInfoPo();
            entity.setUserId(Long.parseLong(request.getParameter("userId")));
            userInfo = basUserInfoService.find(entity);
            BasMemberInfoPo mEntity = new BasMemberInfoPo();
            mEntity.setCustId(userInfo.getMemberId().longValue());
            memberInfo = basMemberInfoService.find(mEntity);
            request.setAttribute("user", userInfo);
            if(memberInfo != null) {
                request.setAttribute("memberName", memberInfo.getCustName());
            }
            else {
                request.setAttribute("memberName", "");
            }
        }
        catch (Throwable e) {
            log.error("xxxxxx查询用户信息失败: ", e);
        }
        return "bas/user/update";
    }
    
    /**
     * 
     * 更新用户
     * @version 2018年1月31日下午3:05:03
     * @author liuheng
     * @param info
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public AjaxReturnMsg updateUser(BasUserInfoPo info, HttpServletRequest request){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            info.setUpdateTime(new Date());
            info.setUpdateUser(loginUser.getUserName());
            basUserInfoService.update(info, true);
        }
        catch (Throwable e) {
            log.error("xxxxxx更新用户状态失败", e);
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
     * 匹配角色页面
     * @version 2018年2月5日下午4:07:14
     * @author liuheng
     * @return
     */
    @RequestMapping(value = "/matchRoleView")
    public String matchRoleView(SysUserRoleRelPo entity) {
        try {
            entity.setStatus(1);
            List<SysUserRoleRelPo> list = sysUserRoleRelService.findAll(entity);
            if(list == null || list.isEmpty()){
                request.setAttribute("roleId", "");
            }else{
                String roleId = "";
                int size = list.size();
                for(int i = 0; i < size; i++){
                    if(i == (size - 1)){
                        roleId = roleId + list.get(i).getRoleId();
                    }else{
                        roleId = roleId + list.get(i).getRoleId() + ",";
                    }
                }
                request.setAttribute("roleId", roleId);
            }
        }
        catch (Throwable e) {
            log.error("xxxxxx查询角色信息失败: ", e);
        }
        return "bas/user/matchRole";
    }
    
    /**
     * 
     * 查询角色信息
     * @version 2018年2月6日下午3:24:01
     * @author liuheng
     * @param request
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/queryRole")
    @ResponseBody
    public DataGridResBean queryRole(HttpServletRequest request, RoleManagerQueryBean bean,
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
     * 关联角色
     * @version 2018年2月6日下午3:32:16
     * @author liuheng
     * @param relPo
     * @return
     */
    @RequestMapping(value = "/matchRole")
    @ResponseBody
    public AjaxReturnMsg matchRole(HttpServletRequest request){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            String userId = request.getParameter("userId");
            String roleIds = request.getParameter("roleIds");
            BasUserInfoPo loginUser = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            sysUserRoleRelService.saveUserRoleRel(Long.parseLong(userId),roleIds, loginUser);
        }
        catch (Throwable e) {
            log.error("xxxxxx关联角色失败", e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
}
