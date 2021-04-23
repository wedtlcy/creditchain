/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.manager.service.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditlink.bean.bo.UpdateStatusBo;
import com.creditlink.bean.po.bas.BasUserInfoPo;
import com.creditlink.bean.po.sys.SysMenuInfoPo;
import com.creditlink.dao.sys.SysMenuInfoDao;
import com.creditlink.manager.bean.DataGridReqBean;
import com.creditlink.manager.bean.DataGridResBean;
import com.creditlink.manager.bean.Tree;
import com.creditlink.manager.bean.UpdateStatusBatchBean;
import com.creditlink.manager.constants.Const;
import com.creditlink.manager.constants.RetCode;
import com.creditlink.manager.service.sys.SysMenuInfoService;
import com.creditlink.manager.util.BeanUtil;
import com.creditlink.manager.util.ServiceException;
import com.creditlink.manager.util.bean.ServiceCommonUtils;


/**
 * SysMenuInfoServiceImpl
 * 
 * @version 2017年12月5日下午5:17:53
 * @author wuliu
 */
@Service
@Transactional
public class SysMenuInfoServiceImpl implements SysMenuInfoService{

    private static final Logger log = LoggerFactory.getLogger(SysMenuInfoServiceImpl.class);
    
    @Autowired
    private SysMenuInfoDao sysMenuInfoDao;
//    @Value("${service}")
//    private String service;
    
    @Override
    public void insert(SysMenuInfoPo entity) {
        sysMenuInfoDao.insert(entity);
    }
    
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public SysMenuInfoPo find(SysMenuInfoPo info) {
        return sysMenuInfoDao.find(info);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SysMenuInfoPo> findAll(SysMenuInfoPo info) {
        return sysMenuInfoDao.findAll(info);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<SysMenuInfoPo> getUserMenuInfo(Long userId) {
        BasUserInfoPo bo = new BasUserInfoPo();
        bo.setUserId(userId);
        return sysMenuInfoDao.getUserMenuInfo(bo);
    }

    @Override
    public void update(SysMenuInfoPo info, Boolean limit) {
        int count = sysMenuInfoDao.update(info);
        if(limit){
            if(count != 1){
                throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
            }
        }
    }

    @Override
    public void update(SysMenuInfoPo info, Integer num) {
        int count = sysMenuInfoDao.update(info);
        if(num.intValue() != count){
            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
        }   
    }

    @Override
    public void updateStatusBatch(UpdateStatusBatchBean updateStatusBatchBean) {
        int idsLen = updateStatusBatchBean.getIds().split(Const.IDS_SEPARATOR).length;
        UpdateStatusBo bo = ServiceCommonUtils.getUpdateStatusBo(updateStatusBatchBean);
        int count = sysMenuInfoDao.updateStatusBatch(bo);
        if(idsLen != count){
            log.info("需要更新数量:{},被更新数量:{}",updateStatusBatchBean.getIds(),count);
            throw new ServiceException(RetCode.UPDATE_FAIL_CODE_N, RetCode.UPDATE_FAIL_CODE_N_MSG);
        }
    }

    @Override
    public void loadDataGrid(DataGridResBean res, DataGridReqBean req) {
        //默认返回值
        res.setTotal(0L);
        res.setRows(new ArrayList<Map<String,Object>>());
        //查询条件
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = BeanUtil.objectToMapByIntrospector(req.getBean());
        }
        catch (Throwable e) {
            log.error("xxxxxx用户管理查询系统异常",e);
            return;
        }
        map.put("pageStart", req.getPageStart());
        map.put("pageSize", req.getRows());
        //查询数量
        Long total = sysMenuInfoDao.menuManagerQueryCount(map);
        res.setTotal(total);
        //查询数据
        List<Map<String,Object>> list = sysMenuInfoDao.menuManagerQuery(map);
        if(list == null || list.isEmpty()){
            list = new ArrayList<Map<String,Object>>();
        }
        res.setRows(list);
    }

    @Override
    public List<Tree> queryUserAuthMenu(String parentId, BasUserInfoPo userInfLogin) {
//        System.out.println("--------------------------service---------------" + service);
        List<Tree> tree = new ArrayList<Tree>();
        List <SysMenuInfoPo> menuList = sysMenuInfoDao.queryUserAuthMenu(userInfLogin);
        tree = makeMenuTree(menuList, parentId);
        return tree;
    }

    /**
     * makeMenuTree
     * 
     * @version 2017年12月11日下午3:11:33
     * @author wuliu
     * @param menuList
     * @param parentId
     * @return
     */
    private List<Tree> makeMenuTree(List<SysMenuInfoPo> menuList, String parentId) {
        Long parentMenuId = Long.parseLong(parentId);
        List<Tree> treeList = new ArrayList<Tree>();
        /*for(SysMenuInfoPo menu:menuList){
            if(menu.getMenuParId() != null){
                if(parentMenuId.intValue() == menu.getMenuParId().intValue()){
                    MenuTree tree = new MenuTree();
                    tree.setId(menu.getMenuId());
                    tree.setText(menu.getMenuName());
                    
                    Attributes att = new Attributes();
                    att.setUrl(menu.getMenuUrl());
                    tree.setAttributes(att);
                    
                    addChildrenToTreeForM(tree.children,menu.getMenuId(),menuList);
                    treeList.add(tree);
                }
            }
        }*/
        return treeList;    
    }

    /**
     * 迭代获取菜单信息
     * 
     * @version 2017年12月11日下午4:10:27
     * @author wuliu
     * @param treeList
     * @param parId
     * @param menuList
     */
    private void addChildrenToTreeForM(List<Tree> treeList,Long parId,List<SysMenuInfoPo> menuList) {
        /*for(SysMenuInfoPo menu:menuList){
            if(menu.getMenuParId()!=null){
                if(parId.intValue() == menu.getMenuParId().intValue()){
                    MenuTree tree = new MenuTree();
                    tree.setId(menu.getMenuId());
                    tree.setText(menu.getMenuName());
                    Attributes att = new Attributes();
                    att.setUrl(menu.getMenuUrl());
                    tree.setAttributes(att);
                    
                    addChildrenToTreeForM(tree.children,menu.getMenuId(),menuList);
                    treeList.add(tree);
                }
            }
        }*/
    }
    
    @Override
    public Long getMaxMenuId(Long menuParId) {
        Long menuId =  sysMenuInfoDao.getMaxMenuId(menuParId);
        if(menuId != null) {
            return menuId + 1;
        }
        else {
            return Long.valueOf(menuParId.toString() + "01");
        }
    }
    
    /**
     * 
     * MenuList转TreeList
     * @version 2018年1月11日下午7:18:52
     * @author liuheng
     * @return
     */
    @Override
    public List<Tree> menuListToTreeList(List<SysMenuInfoPo> menuList) throws Exception {
        List<Tree> treeList = new ArrayList<Tree>();
        if(menuList == null || menuList.isEmpty()) {
            return treeList;
        }
        List<List<Tree>> tempList = new ArrayList<List<Tree>>();
        int tempListIndex = 0;
        tempList.add(new ArrayList<Tree>());
        // 获取一级菜单
        int size = menuList.size(); // 待遍历菜单List大小
        for (int i = size-1; i >= 0; i--) {
            if(menuList.get(i).getMenuParId().longValue() == 0) {
                SysMenuInfoPo tempMenu = menuList.get(i);
                Tree tempTree = new Tree();
                tempTree.setId(tempMenu.getMenuId());
                tempTree.setMenuParId(tempMenu.getMenuParId());
                tempTree.setText(tempMenu.getMenuName());
                tempTree.setChecked(tempMenu.getChecked());
                tempTree.setUrl(tempMenu.getMenuUrl());
                tempTree.setIconCls("hide");
                tempList.get(tempListIndex).add(tempTree);
                menuList.remove(i);
            }
        }
        //无一级菜单
        if(tempList.get(tempListIndex).size() == 0) {
            return treeList;
        }
        // 子菜单遍历直至无子菜单
        int sum; // 当前遍历获取的子菜单的总数
        do {
            tempList.add(new ArrayList<Tree>());
            for(Tree tree: tempList.get(tempListIndex)) {
                size = menuList.size();
                for (int i = size-1; i >= 0; i--) {
                    if(menuList.get(i).getMenuParId().longValue() == tree.getId().longValue()) {
                        SysMenuInfoPo tempMenu = menuList.get(i);
                        Tree tempTree = new Tree();
                        tempTree.setId(tempMenu.getMenuId());
                        tempTree.setMenuParId(tempMenu.getMenuParId());
                        tempTree.setText(tempMenu.getMenuName());
                        tempTree.setChecked(tempMenu.getChecked());
                        tempTree.setUrl(tempMenu.getMenuUrl());
                        tempTree.setIconCls("hide");
                        tempList.get(tempListIndex+1).add(tempTree);
                        menuList.remove(i);
                    }
                }
            }
            tempListIndex++;
            sum = tempList.get(tempListIndex).size();
        }
        while (sum > 0);

        // tempList最后一个为空,所以remove掉最后一个
        tempList.remove(tempList.size()-1);
        
        int tempListSize = tempList.size();
        // 收缩菜单
        for (int i = tempListSize-2; i >= 0; i--) {
            for(int j = tempList.get(i).size()-1; j >= 0; j--) {
                List<Tree> tempTreeList = new ArrayList<Tree>();
                for(int k = tempList.get(i+1).size()-1; k >= 0; k--) {
                    if(tempList.get(i+1).get(k).getMenuParId().longValue() == tempList.get(i).get(j).getId().longValue()) {
                        tempTreeList.add(tempList.get(i+1).get(k));
                        tempList.get(i+1).remove(k);
                    }
                    else {
                        break;
                    }
                }
                if(!tempTreeList.isEmpty()) {
                    tempList.get(i).get(j).setState("closed");
                    tempList.get(i).get(j).setChecked(false);
                }
                tempList.get(i).get(j).setChildren(tempTreeList);
            }
        }
        
        // 倒序改为正序
        for(int i = tempList.get(0).size()-1; i >= 0; i--) {
            treeList.add(tempList.get(0).get(i));
        }
        
        return treeList;
    }
}
