/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.webapi.service;

import java.util.Map;

import com.creditlink.center.provider.api.vo.BaseReqVo;

/**
 * RibbonCommonService(ribbon+restTemplate服务调用方式)
 * 
 * @version 2018年1月4日下午12:42:11
 * @author wuliu
 */
public interface RibbonCommonService {
    
    /**
     * ribbon-post请求服务方式(baseReqVo)
     * 
     * @version 2018年1月4日下午12:43:58
     * @author wuliu
     * @param params
     * @param url
     * @return
     */
    public String ribbonPost(BaseReqVo baseReqVo, String url);
    
    /**
     * ribbon-post请求服务方式(map)
     * 
     * @version 2018年1月4日下午12:43:58
     * @author wuliu
     * @param params
     * @param url
     * @return
     */
    public String ribbonPost(Map<String,Object> params, String url);
}
