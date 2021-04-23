<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>新增用户</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="addUser_form">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>用户名</label>
      <input id="addUser_userName" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" validType="username" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">联盟成员</label>
      <div class="textbox border-0 text-left ipt-sm bg-transparent checkbox-item">
        <input id="addUser_isMember" class="ipt-checkbox" type="checkbox">
        <label for="addUser_isMember"><i class="icon"></i>是</label>
      </div>
    </div>
    <div class="form-group user-manage-select form-hide" id="addUser_unionMemberBox">
      <label class="textbox-label textbox-label-before">成员名称</label> 
      <input id="addUser_custName" readonly="true" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
      <input id="addUser_memberId" readonly="true" type="hidden">
      <a class="link-user-manage-select" id="addUser_chooseMember" href="#">选择成员...</a>
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">手机号码</label>
      <input id="addUser_userMobile" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">邮箱</label>
      <input id="addUser_userEmail" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input id="addUser_remark" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="addUser_btn" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#userManage_addUserDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
  // 随页面加载
  $(function(){
	  addUser_init();
  })
  
  // 初始化
  function addUser_init() {
	  addUser_bindEvent();
	  setTimeout(function(){
          addCustomFun.validateFormIsPass($('#addUser_form'),$('#addUser_btn'));
        },0)
  }
  
  // 绑定事件
  function addUser_bindEvent() {
	  var $unionMemberBox = $('#addUser_unionMemberBox');
	  // 是否联盟成员单选框
      $('#addUser_isMember').on('click', function(){
        if($(this).is(':checked')){
          $unionMemberBox.removeClass('form-hide').addClass('form-show');
        }else{
          $unionMemberBox.removeClass('form-show').addClass('form-hide');
        }
      });
      // 选择联盟成员
      $('#addUser_chooseMember').on('click', function(){
          addCustomFun.openDialog('${pageContext.request.contextPath}/userManage/chooseMemberView.do','联盟成员选择',1020,700,"userManage_chooseMemberDialog",[],"auto");
      });
      // 添加用户
      $('#addUser_btn').on('click', function() {
    	  var userName = $("#addUser_userName").val().trim();
    	  var memberId = $("#addUser_memberId").val().trim();
    	  var isMember = 0;
    	  if($('#addUser_isMember').is(':checked')) {
    		  isMember = 1;
    		  if(memberId == null || memberId == '') {
    			  $.messager.alert('提示','联盟成员名称不能为空！'); 
    			  return ;
    		  }
    	  }
    	  var userMobile = $("#addUser_userMobile").val().trim();
    	  if(userMobile != '' && !/^(1)\d{10}$/.test(userMobile)) {
    		  $.messager.alert('提示','手机号码格式不正确！'); 
    		  return ;
    	  }
    	  var userEmail = $("#addUser_userEmail").val().trim();
    	  if(userEmail != '' && !/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(userEmail)) {
    		  $.messager.alert('提示','邮箱格式不正确！'); 
    		  return ;
    	  }
    	  var remark = $("#addUser_remark").val().trim();
    	  $.ajax({
  			  type: "post",
  			  url: "${pageContext.request.contextPath}/userManage/addUser.do",
  			  data: {
  				  "userName": userName,
  				  "isMember": isMember,
  				  "memberId": memberId,
  				  "userMobile": userMobile,
  				  "userEmail": userEmail,
  				  "remark": remark
  			  },
  			  dataType: "json",
  			  success: function(result) {
  				  $.messager.show({
					  title : '提示',
					  msg : result.msg
				  });
				  if(result.code == "Y") {
					  $('#userManage_addUserDialog').dialog('close');
					  $('#userManage_table').datagrid('reload');
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
  
  // 选择联盟成员后赋值
  function callback_chooseMember(memberId, custName) {
	  $('#addUser_memberId').val(memberId);
	  $('#addUser_custName').textbox('setValue',custName);
  }
  </script>
</body>
</html>