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

import com.creditlink.bean.po.bas.BasProductInfoPo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.bean.query.ProductManagerQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.bas.BasProductInfoService;
import com.creditlink.manager.util.Constants;

/**
 * ProductController
 * 
 * @version 2018年1月25日上午10:10:33
 * @author wuliu
 */
@Controller
@RequestMapping(value = "/bas/product/")
public class ProductController extends BaseController{   
    
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private BasProductInfoService basProductInfoService;
    
    @RequestMapping(value = "productManager")
    public String userManager(HttpServletRequest request,HttpServletResponse response){
        return "bas/product/productManager";
    }
    
    /**
     * 产品管理(查询)
     * 
     * @version 2017年12月8日下午12:40:17
     * @author wuliu
     * @param request
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "productManager/query")
    @ResponseBody
    public DataGridResBean productManagerQuery(HttpServletRequest request,HttpSession session,ProductManagerQueryBean bean,Long page, Long rows){
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        basProductInfoService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 更新产品状态
     * 
     * @version 2018年1月23日下午5:37:24
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateProductStatus")
    @ResponseBody
    public AjaxReturnMsg updateProductStatus(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("更新用户状态信息ids:{},status:{}");
            //校验
            checkStatus(msg, ids, status);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),userInfLogin);
            basProductInfoService.updateStatusBatch(updateStatusBatchBean);
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
     * 产品修改初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateProductInit")
    public ModelAndView updateProductInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/product/updateProductInit");
        String id = request.getParameter("id");
        BasProductInfoPo temp = new BasProductInfoPo();
        temp.setProductId(Long.parseLong(id));
        BasProductInfoPo po = basProductInfoService.find(temp);
        request.setAttribute("productInfo", po);
        return modelAndView;
    }
    
    /**
     * 修改产品
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateProduct")
    @ResponseBody
    public AjaxReturnMsg updateProduct(HttpServletRequest request, HttpServletResponse response, BasProductInfoPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            if(Constants.IS_MEMBER_1 == userInfLogin.getIsMember()){
                log.info("联盟成员不允许操作");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，联盟成员不允许操作.");
                return msg;
            }
            BasProductInfoPo temp = new BasProductInfoPo();
            temp.setProductId(po.getProductId());
            BasProductInfoPo basProductInfoPo = basProductInfoService.find(temp);
            if(basProductInfoPo == null){
                log.info("产品信息不存在:{}", po.getProductId());
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("产品信息不存在");
                return msg;
            }
            po.setUpdateTime(new Date());
            po.setUpdateUser(userInfLogin.getUserId() + "");
            basProductInfoService.update(po, Const.UPDATE_LIMIT_1);
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
     * 新增产品初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addProductInit")
    public ModelAndView addProductInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/product/addProductInit");
        return modelAndView;
    }
    
    /**
     * 新增产品
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addProduct")
    @ResponseBody
    public AjaxReturnMsg addProduct(HttpServletRequest request, HttpServletResponse response, BasProductInfoPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            if(Constants.IS_MEMBER_1 == userInfLogin.getIsMember()){
                log.info("联盟成员不允许操作");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，联盟成员不允许操作.");
                return msg;
            }
            BasProductInfoPo temp = new BasProductInfoPo();
            temp.setProductFlag(po.getProductFlag());
            BasProductInfoPo basProductInfoPo = basProductInfoService.find(temp);
            if(basProductInfoPo != null){
                log.info("产品标识已存在:{}", po.getProductFlag());
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("产品标识已存在");
                return msg;
            }
            po.setCreateTime(new Date());
            po.setCreateUser(userInfLogin.getUserId() + "");
            basProductInfoService.insert(po);
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
