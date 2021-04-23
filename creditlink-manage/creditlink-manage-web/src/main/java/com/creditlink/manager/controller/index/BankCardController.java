/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.controller.index;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.creditlink.bean.po.bas.BasProductInfoPo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.index.BankCardFourElementAddedTempPo;
import com.creditlink.bean.po.index.BankCardFourElementPo;
import com.creditlink.dao.bas.BasProductInfoDao;
import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.bean.query.FourElementTodayQueryBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.LoginConstants;
import com.creditlink.manager.controller.BaseController;
import com.creditlink.manager.service.index.BankCardFourElementAddedTempService;
import com.creditlink.manager.service.index.BankCardFourElementService;
import com.creditlink.manager.util.Constants;
import com.creditlink.manager.util.ExcelUtils;

/**
 * 银行卡四要素BankCardFourElementController
 * 
 * @version 2018年1月19日上午10:38:06
 * @author wuliu
 */
@Controller
@RequestMapping("/index/bankcard")
public class BankCardController extends BaseController{
    
    private static final Logger log = LoggerFactory.getLogger(BankCardController.class);
    
    @Autowired
    private BankCardFourElementService bankCardFourElementService;
    @Autowired
    private BankCardFourElementAddedTempService bankCardFourElementAddedTempService;
    @Autowired
    private BasProductInfoDao basProductInfoDao;
    
    @Value("${bankcard_fourelement_flag}")
    private String bankcardFourelementFlag;
    @Value("${importDataStartTime}")
    private String importDataStartTime;
    @Value("${importDataEndTime}")
    private String importDataEndTime;
    
    @Value("${updateIndexStartTime}")
    private String updateIndexStartTime;
    @Value("${updateIndexEndTime}")
    private String updateIndexEndTime;
    
    /**
     * 四要素索引查询页面初始化
     * 
     * @version 2018年1月23日下午5:35:13
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fourElementInit")
    public ModelAndView fourElementInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index/bankcard/fourElementInit");
        return modelAndView;
    }
    
    /**
     * 四要素索引导入页面初始化
     * 
     * @version 2018年1月23日下午5:35:31
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fourElementImportInit")
    public ModelAndView fourElementImportInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index/bankcard/fourElementImportInit");
        return modelAndView;
    }
    
    /**
     * 四要素临时索引页面初始化
     * 
     * @version 2018年1月23日下午5:35:57
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fourElementTodayInit")
    public ModelAndView fourElementTodayInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index/bankcard/fourElementTodayInit");
        return modelAndView;
    }
    
    /**
     * 导入四要素索引
     * 
     * @version 2018年1月23日下午5:36:14
     * @author wuliu
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/fourElementImport")
    @ResponseBody
    public AjaxReturnMsg fourElementImport(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        FileInputStream fis = null;
        int n = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            long startTime = format.parse(sdf.format(date) + " " + importDataStartTime).getTime();
            long endTime = format.parse(sdf.format(date) + " " + importDataEndTime).getTime();
            if((date.getTime() < startTime) || date.getTime() > endTime){
                log.info("不在允许导入的时间内");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，此时间段不允许导入操作，操作时间:"+ importDataStartTime + "-" + importDataEndTime);
                return msg;
            }
            BasProductInfoPo temp = new BasProductInfoPo();
            temp.setProductFlag(bankcardFourelementFlag);
            BasProductInfoPo basProductInfoPo = basProductInfoDao.find(temp);
            if(basProductInfoPo == null){
                log.info("产品信息没有查询到:{}",bankcardFourelementFlag);
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg(Const.AJAX_ERROR_CODE_N_MSG);
                return msg;
            }
            // 转型为MultipartHttpRequest
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获得文件：   
            MultipartFile file = multipartRequest.getFile("file");    
            // 获得文件名：   
            String filename = file.getOriginalFilename();
            if(StringUtils.isBlank(filename)){
                log.error("文件名称为空");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("文件名称为空");
                return msg;
            }
            @SuppressWarnings("rawtypes")
            List<Map> list = null;
            fis = (FileInputStream) file.getInputStream();
            // 注意：readExcel2003Cp该方法里面的操作已经将fis流关闭了
            list = ExcelUtils.readExcel2003Cp(fis,4);
            if(list == null || list.isEmpty()){
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("没有需要导入的数据");
                return msg;
            }else{
                BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
                n = bankCardFourElementAddedTempService.fourElementImport(list, basProductInfoPo,userInfLogin);
            }
            log.info("导入数据失败数量:{}", n);
        }
        catch (Throwable e) {
            log.error("xxxxxx上传索引异常",e);
            msg.setCode(Const.AJAX_ERROR_CODE_N);
            msg.setMsg(Const.AJAX_SYS_ERROR_N_MSG);
            return msg;
        }finally{
            try {
                if(fis != null){
                fis.close();
                }
            }
            catch (IOException e) {
                log.error("xxxxxx上传索引关闭流异常",e);
            }    
        }
        msg.setCode(Const.AJAX_SUCCESS_CODE_Y);
        msg.setMsg(Const.AJAX_SUCCESS_CODE_Y_MSG);
        return msg;
    }
    
    /**
     * 今日四要素索引信息查询页面
     * 
     * @version 2018年1月23日下午5:36:25
     * @author wuliu
     * @param request
     * @param response
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/fourElementToday")
    @ResponseBody
    public DataGridResBean fourElementToday(HttpServletRequest request, HttpServletResponse response, FourElementTodayQueryBean bean,
            Long page, Long rows) {
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        bankCardFourElementAddedTempService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 更新今日四要素索引状态
     * 
     * @version 2018年1月23日下午5:36:55
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateStatusFourElementAddedTemp")
    @ResponseBody
    public AjaxReturnMsg updateStatusFourElementAddedTemp(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            long startTime = format.parse(sdf.format(date) + " " + updateIndexStartTime).getTime();
            long endTime = format.parse(sdf.format(date) + " " + updateIndexEndTime).getTime();
            if((date.getTime() < startTime) || date.getTime() > endTime){
                log.info("不在允许修改索引的时间内");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，此时间段不允许修改索引的操作，操作时间:"+ updateIndexStartTime + "-" + updateIndexEndTime);
                return msg;
            }
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("更新用户状态信息ids:{},status:{}");
            //校验
            checkStatus(msg, ids, status);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),userInfLogin);
            bankCardFourElementAddedTempService.updateStatusBatch(updateStatusBatchBean);
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
     * 四要素索引信息查询
     * 
     * @version 2018年1月23日下午5:37:11
     * @author wuliu
     * @param request
     * @param response
     * @param bean
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/fourElement")
    @ResponseBody
    public DataGridResBean fourElement(HttpServletRequest request, HttpServletResponse response, FourElementTodayQueryBean bean,
            Long page, Long rows) {
        DataGridResBean res = new DataGridResBean();
        DataGridReqBean req = new DataGridReqBean();
        req.setBean(bean);
        req.setPage(page);
        req.setRows(rows);
        bankCardFourElementService.loadDataGrid(res,req);
        return res;
    }
    
    /**
     * 四要素索引状态更新
     * 
     * @version 2018年1月23日下午5:37:24
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateStatusFourElement")
    @ResponseBody
    public AjaxReturnMsg updateStatusFourElement(HttpServletRequest request,HttpServletResponse response){
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            if(Constants.IS_MEMBER_1 == userInfLogin.getIsMember()){
                log.info("联盟成员不允许操作");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，联盟成员不允许操作.");
                return msg;
            }
            String ids = request.getParameter("ids");//更新信息
            String status = request.getParameter("status");//新状态
            log.info("更新用户状态信息ids:{},status:{}");
            //校验
            checkStatus(msg, ids, status);
            UpdateStatusBatchBean updateStatusBatchBean = new UpdateStatusBatchBean(ids, Integer.parseInt(status),userInfLogin);
            bankCardFourElementService.updateStatusBatch(updateStatusBatchBean);
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
     * 四要素索引信息修改初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateFourElementInit")
    public ModelAndView updateFourElementInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index/bankcard/updateFourElementInit");
        String id = request.getParameter("id");
        BankCardFourElementPo temp = new BankCardFourElementPo();
        temp.setId(Long.parseLong(id));
        BankCardFourElementPo po = bankCardFourElementService.find(temp);
        request.setAttribute("fourElement", po);
        return modelAndView;
    }
    
    /**
     * 四要素索引信息修改
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateFourElement")
    @ResponseBody
    public AjaxReturnMsg updateFourElement(HttpServletRequest request, HttpServletResponse response, BankCardFourElementPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            if(Constants.IS_MEMBER_1 == userInfLogin.getIsMember()){
                log.info("联盟成员不允许操作");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，联盟成员不允许操作.");
                return msg;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            long startTime = format.parse(sdf.format(date) + " " + updateIndexStartTime).getTime();
            long endTime = format.parse(sdf.format(date) + " " + updateIndexEndTime).getTime();
            if((date.getTime() < startTime) || date.getTime() > endTime){
                log.info("不在允许修改索引的时间内");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，此时间段不允许修改索引的操作，操作时间:"+ updateIndexStartTime + "-" + updateIndexEndTime);
                return msg;
            }
            BankCardFourElementPo temp = new BankCardFourElementPo();
            temp.setId(po.getId());
            BankCardFourElementPo bankCardFourElementPo = bankCardFourElementService.find(temp);
            if(bankCardFourElementPo == null){
                log.info("索引信息不存在:{}", po.getId());
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("索引信息不存在");
                return msg;
            }
            po.setUpdateTime(new Date());
            po.setUpdateUser(userInfLogin.getUserName());
            bankCardFourElementService.update(po, Const.UPDATE_LIMIT_1);
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
     * 四要素索引信息修改初始化页面
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateFourElementTodayInit")
    public ModelAndView updateFourElementTodayInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index/bankcard/updateFourElementTodayInit");
        String id = request.getParameter("id");
        BankCardFourElementAddedTempPo temp = new BankCardFourElementAddedTempPo();
        temp.setId(Long.parseLong(id));
        BankCardFourElementAddedTempPo po = bankCardFourElementAddedTempService.find(temp);
        request.setAttribute("fourElementAddedTemp", po);
        return modelAndView;
    }
    
    /**
     * 四要素索引信息修改(今日导入索引)
     * 
     * @version 2018年1月23日下午5:37:48
     * @author wuliu
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateFourElementAddedTemp")
    @ResponseBody
    public AjaxReturnMsg updateFourElementAddedTemp(HttpServletRequest request, HttpServletResponse response, BankCardFourElementAddedTempPo po) {
        AjaxReturnMsg msg = new AjaxReturnMsg();
        try {
            BasUserInfoPo userInfLogin = (BasUserInfoPo) request.getSession().getAttribute(LoginConstants.LOGIN_USER);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            long startTime = format.parse(sdf.format(date) + " " + updateIndexStartTime).getTime();
            long endTime = format.parse(sdf.format(date) + " " + updateIndexEndTime).getTime();
            if((date.getTime() < startTime) || date.getTime() > endTime){
                log.info("不在允许修改索引的时间内");
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("对不起，此时间段不允许修改索引的操作，操作时间:"+ updateIndexStartTime + "-" + updateIndexEndTime);
                return msg;
            }
            BankCardFourElementAddedTempPo temp = new BankCardFourElementAddedTempPo();
            temp.setId(po.getId());
            BankCardFourElementAddedTempPo bankCardFourElementAddedTempPo = bankCardFourElementAddedTempService.find(temp);
            if(bankCardFourElementAddedTempPo == null){
                log.info("索引信息不存在:{}", po.getId());
                msg.setCode(Const.AJAX_ERROR_CODE_N);
                msg.setMsg("索引信息不存在");
                return msg;
            }
            po.setUpdateTime(new Date());
            po.setUpdateUser(userInfLogin.getUserName());
            bankCardFourElementAddedTempService.update(po, Const.UPDATE_LIMIT_1);
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
