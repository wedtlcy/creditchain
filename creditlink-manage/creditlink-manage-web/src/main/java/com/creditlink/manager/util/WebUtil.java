package com.creditlink.manager.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.common.utils.NetUtils;

/**
 * Web工具类
 * 
 * @version 2016-5-3下午8:20:13
 * @author xiaoyun.zeng
 */
public class WebUtil {
    /**
     * 从request中获取客户端IP地址
     * 
     * @author xiaoyun.zeng
     * @date 2015-4-15下午1:48:38
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("http_remote_user_ip");
        String localIP = "127.0.0.1";
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 获取客户端IP地址（仅支持SpringMvc）
     * 
     * @version 2016-5-3下午8:22:53
     * @author xiaoyun.zeng
     * @return
     */
    public static String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return getClientIp(request);
    }
    
    /**
     * 获取本机IP地址
     * 
     * @version 2016-5-11上午9:14:42
     * @author xiaoyun.zeng
     * @return
     */
    public static String getLocalIp() {
        return NetUtils.getLocalHost();
    }
}
