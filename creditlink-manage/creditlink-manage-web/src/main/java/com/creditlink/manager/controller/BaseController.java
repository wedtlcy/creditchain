/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * δ������˾��ʽ����ͬ�⣬�����κθ��ˡ����岻��ʹ�á����ơ��޸Ļ򷢲������.
 * ��Ȩ���������������������޹�˾ http://www.credlink.com/
 */
package com.creditlink.manager.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.creditlink.manager.bean.AjaxReturnMsg;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.DBConstants;
import com.creditlink.manager.util.ServiceException;

/**
 * BaseController
 * 
 * @version 2017年12月5日下午6:16:25
 * @author wuliu
 */
public class BaseController {
    
    public  HttpServletRequest request;
    public  HttpServletResponse response;
    public  HttpSession session;
    
    public static final String RSP_CODE = "code";
    public static final String RSP_MSG  = "msg";
    
    @ModelAttribute 
    public void setReqAndRes(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();  
    } 
    
    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    public HttpSession getSession(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }
    
    public ServletContext getServletContext(){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    /**
     * getIpAddr
     * 
     * @version 2017年12月5日下午7:28:57
     * @author wuliu
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        if (request == null)
        return "";
    
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * htmlEscape
     * TODO:方法描述
     * @version 2017年12月5日下午7:28:41
     * @author wuliu
     * @param input
     * @return
     */
    public static String htmlEscape(String input) {
        String value = "";
        if(null == input || 0 == input.length() || 0 == input.replaceAll("\\s", "").length()){
            return input;
        }
        value = input.replaceAll("&", "&amp;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll(" ", "&nbsp;");
        value = value.replaceAll("'", "&#39;");  
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("\n", "<br/>");
        return value;
    }
    
   /**
    * cleanXSS
    * 
    * @version 2017年12月5日下午7:28:32
    * @author wuliu
    * @param value
    * @return
    */
    public String cleanXSS(String value){
        value= value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    } 
    
   /**
    * getParamAsMap
    * 
    * @version 2017年12月5日下午7:28:24
    * @author wuliu
    * @param request
    * @return
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map getParamAsMap(HttpServletRequest request) {
        Map m = new HashMap();
        Map map = request.getParameterMap();
        Iterator keyIterator = (Iterator) map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = (String) keyIterator.next();
            String[] value = (String[]) (map.get(key));
            if (value.length > 1) {
                StringBuffer sb = new StringBuffer("");
                for (int i = 0; i < value.length; i++) {
                    if (!sb.toString().equals("")) {
                        sb.append(",");
                    }
                    sb.append(value[i]);
                }
                String val = sb.toString();
                val = val.replaceAll("&", "&amp;");
                val = val.replaceAll(">", "&gt;");
                val = val.replaceAll("<", "&lt;");
                val = val.replaceAll("\"", "&quot;");
                val = val.replaceAll("\'", "&acute;");
                m.put(key, val);
            } else {
                String val = value[0].toString().trim();
                val = val.replaceAll("&", "&amp;");
                val = val.replaceAll(">", "&gt;");
                val = val.replaceAll("<", "&lt;");
                val = val.replaceAll("\"", "&quot;");
                val = val.replaceAll("\'", "&acute;");
                m.put(key, val);
            }
        }
        return m;
    }
    
    /**
     * 状态校验(公共)
     * TODO:方法描述
     * @version 2017年12月8日下午4:55:53
     * @author wuliu
     * @param msg
     * @param ids
     * @param status
     */
    public void checkStatus(AjaxReturnMsg msg, String ids, String status) {
        if(StringUtils.isBlank(ids)){
            new ServiceException(Const.AJAX_ERROR_CODE_N, Const.AJAX_ERROR_CODE_N_MSG);
        }
        if(!DBConstants.STATUS_STR_0.equals(status) && !DBConstants.STATUS_STR_1.equals(status)){
            new ServiceException(Const.AJAX_ERROR_CODE_N, Const.AJAX_ERROR_CODE_N_MSG);
        }
    }
    
    /**
     * 导出excel
     *
     * @param wb
     *            excel对象
     * @param fileName
     *            文件名
     * @throws IOException
     */
    protected void export(HSSFWorkbook wb, String fileName,HttpServletResponse response) throws IOException {
        // 设置response的编码方式
        response.setContentType("application/x-msdownload");
        // 解决中文乱码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
        OutputStream output = response.getOutputStream();
        wb.write(output);
        output.flush();
        output.close();
    }
}
