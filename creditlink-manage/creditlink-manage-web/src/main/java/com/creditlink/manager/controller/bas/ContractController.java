/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.bas;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.bean.po.bas.BasConProdRelPo;
import com.creditlink.bean.po.bas.BasContractInfoPo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.bean.query.ContractManagerQueryBean;
import com.creditlink.manager.bean.query.ContractProductManagerQueryBean;
import com.creditlink.manager.bean.query.ProductManagerQueryBean;
import com.creditlink.manager.bean.query.UserManagerQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.bas.BasConProdRelService;
import com.creditlink.manager.service.bas.BasContractInfoService;
import com.creditlink.manager.service.bas.BasProductInfoService;
import com.creditlink.manager.service.bas.BasUserInfoService;

/**
 * ProductController
 * 
 * @version 2018年1月25日上午10:10:33
 * @author wuliu
 */
@Controller
@RequestMapping(value = "/bas/contract/")
public class ContractController extends BaseController{   
    
    private static final Logger log = LoggerFactory.getLogger(ContractController.class);
    
    @Autowired
    private BasContractInfoService basContractInfoService;
    @Autowired
    private BasConProdRelService basConProdRelService;
    @Autowired
    private BasUserInfoService basUserInfoService;
    @Autowired
    private BasProductInfoService basProductInfoService;
    
    
    @RequestMapping(value = "contractManager")
    public String userManager(HttpServletRequest request,HttpServletResponse response){
        return "bas/contract/contractManager";
    }
    
    /**
     * 合同管理(查询)
     * 
     * @version 2017年12月8日下午12:40:17
     * @author wuliu
     * @param request
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "contractManager/query")
    @ResponseBody
    public DataGridResBean contractManagerQuery(HttpServletRequest request,HttpSession session,ContractManagerQueryBean bean,Long page, Long rows){
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        basContractInfoService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 更新合同状态
     * 
     * @version 2018年1月23日下午5:37:24
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateContractStatus")
    @ResponseBody
    public AjaxReturnMsg updateContractStatus(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("更新合同状态信息ids:{},status:{}");
            //校验
            checkStatus(msg, ids, status);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),userInfLogin);
            basContractInfoService.updateStatusBatch(updateStatusBatchBean);
        }
        catch (Throwable e) {
            log.error("xxxxxx更新合同状态失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 合同修改初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateContractInit")
    public ModelAndView updateProductInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/contract/updateContractInit");
        String id = request.getParameter("id");
        BasContractInfoPo temp = new BasContractInfoPo();
        temp.setId(Long.parseLong(id));
        BasContractInfoPo po = basContractInfoService.find(temp);
        request.setAttribute("contractInfo", po);
        return modelAndView;
    }
    
    /**
     * 修改合同
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateContract")
    @ResponseBody
    public AjaxReturnMsg updateContract(HttpServletRequest request, HttpServletResponse response, BasContractInfoPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            BasContractInfoPo temp = new BasContractInfoPo();
            temp.setId(po.getId());
            BasContractInfoPo bascontractInfoPo = basContractInfoService.find(temp);
            if(bascontractInfoPo == null){
                log.info("合同信息不存在:{}", po.getId());
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("合同信息不存在");
                return msg;
            }
            po.setUpdateTime(new Date());
            po.setUpdateUser(userInfLogin.getUserId() + "");
            basContractInfoService.update(po, Const.UPDATE_LIMIT_1);
        }
        catch (Throwable e) {
            log.error("xxxxxx更新合同失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 新增合同初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addContractInit")
    public ModelAndView addProductInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/contract/addContractInit");
        return modelAndView;
    }
    
    /**
     * 新增合同
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addContract")
    @ResponseBody
    public AjaxReturnMsg addContract(HttpServletRequest request, HttpServletResponse response, BasContractInfoPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(StringUtils.isNotBlank(po.getSignDateStr())){
                po.setSignDate(sdf.parse(po.getSignDateStr()));
            }
            if(StringUtils.isNotBlank(po.getEffDateStr())){
                po.setEffDate(sdf.parse(po.getEffDateStr()));
            }
            if(StringUtils.isNotBlank(po.getExpDateStr())){
                po.setExpDate(sdf.parse(po.getExpDateStr()));
            }
            po.setCreateTime(new Date());
            po.setCreateUser(userInfLogin.getUserId() + "");
            basContractInfoService.insert(po);
        }
        catch (Throwable e) {
            log.error("xxxxxx新增合同失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 新增合同选择用户
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/contractUserManager")
    public ModelAndView contractUserManager(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/contract/contractUserManager");
        return modelAndView;
    }
    
    /**
     * 签订产品
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/signProductInit")
    public ModelAndView signProductInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/contract/signProductInit");
        request.setAttribute("id", request.getParameter("id"));
        return modelAndView;
    }
    
    
    /**
     * 签订产品(选择产品)
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/signProductManager")
    public ModelAndView signProductManager(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/contract/signProductManager");
        return modelAndView;
    }
    
    /**
     * 签订产品
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/signProduct")
    @ResponseBody
    public AjaxReturnMsg signProduct(HttpServletRequest request, HttpServletResponse response, BasConProdRelPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            po.setStatus(1);
            BasConProdRelPo basConProdRelPo = basConProdRelService.find(po);
            if(basConProdRelPo != null){
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("已签订产品，不能重复签订.");
                return msg;
            }
            po.setCreateTime(new Date());
            po.setCreateUser(userInfLogin.getUserId() + "");
            basConProdRelService.insert(po);
        }
        catch (Throwable e) {
            log.error("xxxxxx签订合同失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 合同对应的产品
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/contractProductDetailInit")
    public ModelAndView contractProductDetailInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bas/contract/contractProductDetailInit");
        request.setAttribute("id", request.getParameter("id"));
        return modelAndView;
    }
    
    /**
     * 合同产品关联
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "contractProductManager/query")
    @ResponseBody
    public DataGridResBean contractProductManager(HttpServletRequest request,HttpSession session,ContractProductManagerQueryBean bean,Long page, Long rows){
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        basConProdRelService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 更新合同产品关联状态
     * 
     * @version 2018年1月23日下午5:37:24
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateContractProductStatus")
    @ResponseBody
    public AjaxReturnMsg updateContractProductStatus(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("更新合同产品状态信息ids:{},status:{}");
            //校验
            checkStatus(msg, ids, status);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),userInfLogin);
            basConProdRelService.updateStatusBatch(updateStatusBatchBean);
        }
        catch (Throwable e) {
            log.error("xxxxxx更新合同产品状态失败",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
            return msg;
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    
    /**
     * 选择客户
     * 
     * @version 2018年1月31日下午3:06:13
     * @author liuheng
     * @param request
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/usersForAddContract")
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
    @RequestMapping(value = "productQueryForSignProduct")
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
}
