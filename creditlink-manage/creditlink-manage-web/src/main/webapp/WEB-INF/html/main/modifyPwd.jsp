<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>修改密码</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="modifyPwdForm">
    <div class="form-group vertical">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>旧密码</label>
      <input id="modifyPwd_oldPwd" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true,prompt:'请输入旧密码',type:'password'" style="height:40px;">
    </div>
    <div class="form-group vertical">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>新密码</label>
      <input id="modifyPwd_newPwd" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true,prompt:'请输入至少6位字符，同时包含字母和数字',type:'password'" validType="safepass" style="height:40px;">
    </div>
    <div class="form-group vertical">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>确认密码</label>
      <input id="modifyPwd_newPwd2" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true,prompt:'请输入至少6位字符，同时包含字母和数字',type:'password'" validType="equalTo['#modifyPwd_newPwd']" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="btnModifyPwdSure" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#modifyPwdDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    $(function(){
      setTimeout(function(){
        addCustomFun.validateFormIsPass($('#modifyPwdForm'),$('#btnModifyPwdSure'));
      },0)
      modifyPwd_bindEvent();
    })
    
    function modifyPwd_bindEvent() {
    	$('#btnModifyPwdSure').on('click', function() {
    		var oldPwd = $('#modifyPwd_oldPwd').val();
    		var newPwd = $('#modifyPwd_newPwd').val();
    		var newPwd2 = $('#modifyPwd_newPwd2').val();
    		if(newPwd != newPwd2) {
    		    $.messager.alert('提示','两次输入的新密码不一致！'); 
      		    return ;
    		}
    		$.ajax({
    			  type: "post",
    			  url: "${pageContext.request.contextPath}/main/modifyPwd.do",
    			  data: {
    				  "oldPwd": oldPwd,
    				  "newPwd": newPwd,
    				  "newPwd2": newPwd2
    			  },
    			  dataType: "json",
    			  success: function(result) {
    				  $.messager.show({
  					  title : '提示',
  					  msg : result.msg
  				  });
  				  if(result.code == "Y") {
  					  $('#modifyPwdDialog').dialog('close');
  				  }
    			  },
    			  error: function() {
    				  $.messager.show({
  					  title : '提示',
  					  msg : '网络异常！'
  				  });
    			  }
    		  });
    	});
    }
  </script>
</body>
</html>