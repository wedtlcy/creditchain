/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.bas;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.bean.po.bas.BasMemberInfoPo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.bean.query.MemberManagerQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.bas.BasMemberInfoService;

/**
 * ProductController
 * 
 * @version 2018年1月25日上午10:10:33
 * @author wuliu
 */
@Controller
@RequestMapping(value = "/bas/member/")
public class MemberController extends BaseController{   
    
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    
    @Autowired
    private BasMemberInfoService basMemberInfoService;
    
    @RequestMapping(value = "memberManager")
    public String userManager(HttpServletRequest request,HttpServletResponse response){
        return "bas/member/memberManager";
    }
    
    /**
     * 联盟成员管理(查询)
     * 
     * @version 2017年12月8日下午12:40:17
     * @author wuliu
     * @param request
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "memberManager/query")
    @ResponseBody
    public DataGridResBean memberManagerQuery(HttpServletRequest request,HttpSession session,MemberManagerQueryBean bean,Long page, Long rows){
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        basMemberInfoService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 更新联盟成员状态
     * 
     * @version 2018年1月23日下午5:37:24
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateMemberStatus")
    @ResponseBody
    public AjaxReturnMsg updateMemberStatus(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("更新用户状态信息ids:{},status:{}");
            //校验
            checkStatus(msg, ids, status);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),userInfLogin);
            basMemberInfoService.updateStatusBatch(updateStatusBatchBean);
        }
        catch (Throwable e) {
            log.error("xxxxxx更新用户状态失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 联盟成员修改初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateMemberInit")
    public ModelAndView updateProductInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/member/updateMemberInit");
        String id = request.getParameter("id");
        BasMemberInfoPo temp = new BasMemberInfoPo();
        temp.setCustId(Long.parseLong(id));
        BasMemberInfoPo po = basMemberInfoService.find(temp);
        request.setAttribute("memberInfo", po);
        return modelAndView;
    }
    
    /**
     * 修改联盟成员
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateMemebr")
    @ResponseBody
    public AjaxReturnMsg updateMemebr(HttpServletRequest request, HttpServletResponse response, BasMemberInfoPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            BasMemberInfoPo temp = new BasMemberInfoPo();
            temp.setCustId(po.getCustId());
            BasMemberInfoPo basMemberInfoPo = basMemberInfoService.find(temp);
            if(basMemberInfoPo == null){
                log.info("联盟成员信息不存在:{}", po.getCustId());
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("联盟成员信息不存在");
                return msg;
            }
            po.setUpdateTime(new Date());
            po.setUpdateUser(userInfLogin.getUserId() + "");
            basMemberInfoService.update(po, Const.UPDATE_LIMIT_1);
        }
        catch (Throwable e) {
            log.error("xxxxxx更新产品失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 新增联盟成员初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addMemberInit")
    public ModelAndView addProductInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/member/addMemberInit");
        return modelAndView;
    }
    
    /**
     * 新增联盟成员
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addMember")
    @ResponseBody
    public AjaxReturnMsg addMember(HttpServletRequest request, HttpServletResponse response, BasMemberInfoPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            po.setCreateTime(new Date());
            po.setCreateUser(userInfLogin.getUserId() + "");
            basMemberInfoService.insert(po);
        }
        catch (Throwable e) {
            log.error("xxxxxx更新产品失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
}
