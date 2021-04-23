/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.cfg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.cfg.CfgDictPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.query.UserManagerQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.cfg.CfgDictService;

/**
 * 字典操作
 * 
 * @version 2017年12月8日上午9:42:08
 * @author wuliu
 */
@Controller
@RequestMapping(value = "/cfg/dict/")
public class CfgDictController extends BaseController{
    
    private static final Logger log = LoggerFactory.getLogger(CfgDictController.class);
    
    @Autowired
    private CfgDictService cfgDictService;
    
    @RequestMapping(value = "dictmanager")
    public String userManager(HttpServletRequest request,HttpServletResponse response){
        return "cfg/dict/dictmanager";
    }
    
    /**
     * 用户管理(查询)
     * 
     * @version 2017年12月8日下午12:40:17
     * @author wuliu
     * @param request
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "dictmanager/query")
    @ResponseBody
    public DataGridResBean dictManagerQuery(HttpServletRequest request,HttpSession session,UserManagerQueryBean bean,Long page, Long rows){
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
//        cfgDictService.loadDataGrid(res,req);
        return res;
    }
    
    @RequestMapping(value = "updateStatus")
    @ResponseBody
    public AjaxReturnMsg updateStatus(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("更新用户状态信息ids:{},status:{}");
            //校验
            checkStatus(msg, ids, status);
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
//            cfgDictService.updateStatusBatch(ids, Integer.parseInt(status), userInfLogin.getUserId());
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
    
    @RequestMapping(value = "addDict")
    @ResponseBody
    public AjaxReturnMsg addUser(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            CfgDictPo po = new CfgDictPo();
//            cfgDictService.insert(po);
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
    
    @RequestMapping(value = "updateDict")
    @ResponseBody
    public AjaxReturnMsg updateUser(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            CfgDictPo po = new CfgDictPo();
//            cfgDictService.update(po, Const.UPDATE_LIMIT_1);
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
}
