/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 鏈粡鏈叕鍙告寮忎功闈㈠悓鎰忥紝鍏朵粬浠讳綍涓汉銆佸洟浣撲笉寰椾娇鐢ㄣ�澶嶅埗銆佷慨鏀规垨鍙戝竷鏈蒋浠�
 * 鐗堟潈鎵�湁娣卞湷甯備俊鑱斿緛淇℃湁闄愬叕鍙�http://www.credlink.com/
 */
package com.creditlink.dao.bas;

import java.util.List;
import java.util.Map;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.bas.BasUserInfoPo;

/**
 * 用户管理
 * 
 * @version 2018年1月23日上午10:24:52
 * @author wuliu
 */
public interface BasUserInfoDao{
    
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(BasUserInfoPo entity);
    
    /**
     * 查询单个
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public BasUserInfoPo find(BasUserInfoPo entity);
    
    /**
     * 根据ID更新
     * 
     * @version 2018年1月23日上午10:42:13
     * @author wuliu
     * @param entity
     * @return
     */
    public int update(BasUserInfoPo entity);
    
    /**
     * 查询所有
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public List<BasUserInfoPo> findAll(BasUserInfoPo info);
    
    /**
     * 更新状态
     * 
     * @version 2018年1月23日上午10:22:56
     * @author wuliu
     * @param bo
     * @return
     */
    public int updateStatusBatch(UpdateStatusBo bo);

   /**
    * 用户管理查询数量
    * 
    * @version 2018年1月23日上午10:24:37
    * @author wuliu
    * @param map
    * @return
    */
    public Long userManagerQueryCount(Map<String, Object> map);
    
    /**
     * 用户管理查询
     * 
     * @version 2018年1月23日上午10:23:38
     * @author wuliu
     * @param map
     * @return
     */
     public List<Map<String,Object>> userManagerQuery(Map<String, Object> map);

}
