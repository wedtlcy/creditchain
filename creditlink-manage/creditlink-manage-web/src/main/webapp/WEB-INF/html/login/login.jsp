<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="login-page">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/fav.ico">
  <title>平台登录</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/themes/custom/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/xldfPages.css">
</head>
<body class="login-body">
  <img class="bg-top-img" src="${pageContext.request.contextPath}/static/images/bg/login_top.png" alt="">
  <div class="login-outer-wrap">
    <div class="left-wrap">
      <div class="word-wrap">
        <img class="title" src="${pageContext.request.contextPath}/static/images/login_mian_title.png" alt="">
      </div>
    </div>
    <div class="right-wrap">
      <form class="form form-login" id="loginForm" method="post" action="${pageContext.request.contextPath}/login/login.do">
        <div class="form-group">
          <input class="easyui-textbox form-control ipt-block" id="username" name="username" data-options="prompt:'用户名',iconCls:'icon-user',iconAlign:'left',iconWidth:28,required: true" style="height: 40px;">
        </div>
        <div class="form-group">
          <input class="easyui-textbox form-control ipt-block ime-disabled" id="password" name="password" data-options="prompt:'密码',iconCls:'icon-pwd',iconAlign:'left',iconWidth:28,type:'password',required: true" validtype="specialCharacter" style="height: 40px;">
        </div>
        <div class="form-group form-verify form-hide" id="verifyForm">
          <input class="easyui-textbox form-control ipt-block" name="verifyCode" data-options="prompt:'验证码',iconCls:'icon-verfiy',iconAlign:'left',iconWidth:28,required:true" style="height: 40px;">
          <div class="verify-code-wrap"><img id="verifyCodeImg" src="${pageContext.request.contextPath}/login/verifyCodeImg.do" alt="验证码"></div>
        </div>
        <button type="sbumit" class="btn btn-primary btn-block btn-login" id="loginBtnSign" disabled="">立即登录</button>
        <!--忘记密码暂时隐藏(.hide)-->
        <a href="#" class="link-forget">忘记密码？</a>    
        <div id="errorText" class="txt-hint">${loginError}</div>
      </form>
    </div>
  </div>
  <div class="footer">
    <p class="copy-right">Copyright&copy;&nbsp;&nbsp;2015-2018 深圳市信联征信有限公司版权所有 <span class="custom-box">客服电话<a class="link" href="tel:(0775)26942648">(0775)26942648</a></span></p>
  </div>
  <!-- script -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/easyui-lang-zh_CN.js"></script> 
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/common/easyui.custom.js"></script> 
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/formReg.js"></script>

  <script>
    var loginPage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        this.refreshCaptcha();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#loginForm'),$('#loginBtnSign'));
        },0);
      },
      bindEvent: function(){
        $('#username').textbox('textbox').bind('input',function(e){
          if($(this).val().length>0){
            $('#verifyForm').removeClass('form-hide').addClass('form-show');
            $('#errorText').text("");
          }else{
            $('#verifyForm').removeClass('form-show').addClass('form-hide');
          }
        });
      },
      refreshCaptcha: function(){
    	  $('#verifyCodeImg').on('click', function(){
    		  $('#verifyCodeImg').attr('src', '${pageContext.request.contextPath}/login/verifyCodeImg.do?timestamp=' + (new Date()).valueOf());
    	  });
      }
    };
    $(function(){
      loginPage.init();
    });
  </script>
</body>
</html>