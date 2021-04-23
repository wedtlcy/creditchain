<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>修改用户</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="updateUser_form">
    <div class="form-group">
      <label class="textbox-label textbox-label-before">用户名</label>
      <input id="updateUser_userName" value="${user.userName}" readonly="true" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">联盟成员</label>
      <div class="textbox border-0 text-left ipt-sm bg-transparent checkbox-item">
        <input id="updateUser_isMember" class="ipt-checkbox" type="checkbox">
        <label for="updateUser_isMember"><i class="icon"></i>是</label>
      </div>
    </div>
    <div class="form-group user-manage-select form-hide" id="updateUser_unionMemberBox">
      <label class="textbox-label textbox-label-before">成员名称</label> 
      <input id="updateUser_custName" readonly="true" value="${memberName}" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
      <input id="updateUser_isMemberValue" readonly="true" value="${user.isMember}" type="hidden">
      <input id="updateUser_memberId" readonly="true" value="${user.memberId}" type="hidden">
      <a class="link-user-manage-select" id="updateUser_chooseMember"  href="#">选择成员...</a>
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">联系电话</label>
      <input id="updateUser_userMobile" value="${user.userMobile}" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">邮箱</label>
      <input id="updateUser_userEmail" value="${user.userEmail}" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input id="updateUser_remark" value="${user.remark}" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="updateUser_ensureBtn">确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#userManage_updateUserDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
  
	// 随页面加载
    $(function(){
    	var isMember = $("#updateUser_isMemberValue").val().trim();
    	if(isMember == '1') {
    		$('#updateUser_isMember').attr('checked', true);
    		$('#updateUser_unionMemberBox').removeClass('form-hide').addClass('form-show');
    	}
    	updateUser_bindEvent();
    });
    
	// 绑定事件
	function updateUser_bindEvent() {
		var $unionMemberBox = $('#updateUser_unionMemberBox');
		// 是否联盟成员单选框
	    $('#updateUser_isMember').on('click', function(){
	      if($(this).is(':checked')){
	        $unionMemberBox.removeClass('form-hide').addClass('form-show');
	      }else{
	        $unionMemberBox.removeClass('form-show').addClass('form-hide');
	      }
	    });
	    // 选择联盟成员
	    $('#updateUser_chooseMember').on('click', function(){
	        addCustomFun.openDialog('${pageContext.request.contextPath}/userManage/chooseMemberView.do','联盟成员选择',1020,700,"userManage_chooseMemberDialog",[],"auto");
	    });
		// 修改用户
		$('#updateUser_ensureBtn').on('click', function() {
			var rows = $('#userManage_table').datagrid('getSelections');
			var userId = rows[0].userId;
			var memberId = $("#updateUser_memberId").val().trim();
	    	var isMember = 0;
	    	if($('#updateUser_isMember').is(':checked')) {
	    		isMember = 1;
	    		if(memberId == null || memberId == '') {
	    			$.messager.alert('提示','联盟成员名称不能为空！'); 
	    			return ;
	    		}
	    	}
	    	else{
	    		memberId = '';
	    	}
	    	var userMobile = $("#updateUser_userMobile").val().trim();
	    	if(userMobile != '' && !/^(1)\d{10}$/.test(userMobile)) {
	    		  $.messager.alert('提示','手机号码格式不正确！'); 
	    		  return ;
	    	  }
	    	var userEmail = $("#updateUser_userEmail").val().trim();
	    	if(userEmail != '' && !/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(userEmail)) {
	    		  $.messager.alert('提示','邮箱格式不正确！'); 
	    		  return ;
	    	  }
	    	var remark = $("#updateUser_remark").val().trim();
	    	$.ajax({
	  			  type: "post",
	  			  url: "${pageContext.request.contextPath}/userManage/updateUser.do",
	  			  data: {
	  				  "userId": userId,
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
						  $('#userManage_updateUserDialog').dialog('close');
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
		$('#updateUser_memberId').val(memberId);
		$('#updateUser_custName').textbox('setValue',custName);
	}
  </script>
</body>
</html>