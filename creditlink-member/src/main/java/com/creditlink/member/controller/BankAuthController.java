/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * δ������˾��ʽ����ͬ�⣬�����κθ��ˡ����岻��ʹ�á����ơ��޸Ļ򷢲������.
 * ��Ȩ���������������������޹�˾ http://www.credlink.com/
 */
package com.creditlink.member.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditlink.member.bean.BankAuthFourElementData;
import com.creditlink.member.bean.BankAuthFourElementReturn;
import com.creditlink.member.entity.BankCardFourElement;
import com.creditlink.member.service.BankAuthService;
import com.creditlink.member.util.Constants;


@Controller
@RequestMapping(value = "/bank/")
public class BankAuthController extends BaseController{
    
    /** 日志记录 */
    private static Logger log = LoggerFactory.getLogger(BankAuthController.class);
    
    @Value("${bankAuthFourElement_account}")
    private String bankAuthFourElementAccount; 
    @Value("${bankAuthFourElement_privateKey}")
    private String bankAuthFourElementPrivateKey; 
    @Autowired
    private BankAuthService bankAuthService;
    
    @RequestMapping(value = "bankAuthFourElement")
    public void bankAuthFourElement(HttpServletRequest request, HttpSession session){
        BankAuthFourElementReturn bankAuthFourElementReturn = new BankAuthFourElementReturn();
        bankAuthFourElementReturn.setCode(Constants.CODE_N);
        bankAuthFourElementReturn.setMsg(Constants.MSG_N);
        bankAuthFourElementReturn.setOrderNo((new Date().getTime()) + "");
        try {
            String account = request.getParameter("account");
            String privateKey = request.getParameter("privateKey");
            String reqSerialNo = request.getParameter("reqSerialNo");
            String bankCard = request.getParameter("bankCard");
            String name = request.getParameter("name");
            String mobile = request.getParameter("mobile");
            String idCard = request.getParameter("idCard");
            log.info("银行卡四要素认证账户信息:{},{}", account, privateKey);
            if(!bankAuthFourElementAccount.equals(account) || !bankAuthFourElementPrivateKey.equals(privateKey)){
                log.info("产品账户:{},私钥:{}",bankAuthFourElementAccount, bankAuthFourElementPrivateKey);
                bankAuthFourElementReturn.setMsg(Constants.MSG_N_PRODUCT_ACCOUNT_ERROR);
                return;
            }
            log.info("银行卡四要素认证请求参数，银行卡号:{},姓名:{},手机号:{},身份证号:{}", bankCard, name, mobile, idCard);
            if(StringUtils.isBlank(bankCard) || StringUtils.isBlank(name) 
                    || StringUtils.isBlank(mobile) || StringUtils.isBlank(idCard) || StringUtils.isBlank(reqSerialNo)){
                bankAuthFourElementReturn.setMsg(Constants.MSG_N_PARAMS_ERROR);
                return;
            }
            BankCardFourElement temp = new BankCardFourElement();
            temp.setBankCard(bankCard);
            BankCardFourElement bankCardFourElement = bankAuthService.find(temp);
            if(bankCardFourElement == null){//库无
                bankAuthFourElementReturn.setCode(Constants.CODE_Y);
                bankAuthFourElementReturn.setMsg(Constants.MSG_Y);
                BankAuthFourElementData authFourElementData = new BankAuthFourElementData();
                authFourElementData.setResult(Constants.AUTH_CODE_0);
                bankAuthFourElementReturn.setData(authFourElementData);
                return;
            }else{
                if(!bankCard.equals(bankCardFourElement.getBankCard()) || !name.equals(bankCardFourElement.getName())
                        || !mobile.equals(bankCardFourElement.getMobile()) || !idCard.equals(bankCardFourElement.getIdCard())){//不一致
                    bankAuthFourElementReturn.setCode(Constants.CODE_Y);
                    bankAuthFourElementReturn.setMsg(Constants.MSG_Y);
                    BankAuthFourElementData authFourElementData = new BankAuthFourElementData();
                    authFourElementData.setResult(Constants.AUTH_CODE_2);
                    bankAuthFourElementReturn.setData(authFourElementData);
                    return;
                }else{//一致
                    bankAuthFourElementReturn.setCode(Constants.CODE_Y);
                    bankAuthFourElementReturn.setMsg(Constants.MSG_Y);
                    BankAuthFourElementData authFourElementData = new BankAuthFourElementData();
                    authFourElementData.setResult(Constants.AUTH_CODE_1);
                    bankAuthFourElementReturn.setData(authFourElementData);
                    return;
                }
            }
        }
        catch (Throwable e) {
            log.error("xxxxxx银行卡四要素认证系统异常",e);
            bankAuthFourElementReturn.setMsg(Constants.MSG_N_SYS_ERROR);
        }finally{
            request.setAttribute(Constants.RSP_BODY, java2Map(bankAuthFourElementReturn));
        }
    }
}
