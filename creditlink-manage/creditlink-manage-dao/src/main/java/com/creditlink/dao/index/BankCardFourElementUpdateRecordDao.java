/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.dao.index;

import com.creditlink.bean.po.index.BankCardFourElementUpdateRecordPo;

/**
 * BankCardFourElementUpdateRecordDao
 * 
 * @version 2018年1月24日下午3:38:05
 * @author wuliu
 */
public interface BankCardFourElementUpdateRecordDao {
    
    /**
     * 单个插入
     * 
     * @version 2018年1月23日上午10:22:38
     * @author wuliu
     * @param info
     * @return
     */
    public void insert(BankCardFourElementUpdateRecordPo entity);
    
    /**
     * 查询修改记录
     * 
     * @version 2018年1月22日上午10:10:04
     * @author wuliu
     * @param addedTempPo
     * @return
     */
    public BankCardFourElementUpdateRecordPo find(BankCardFourElementUpdateRecordPo entity);
}
