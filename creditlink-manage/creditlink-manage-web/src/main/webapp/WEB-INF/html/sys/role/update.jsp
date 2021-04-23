<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>修改菜单</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="updateRole_form">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>角色名称</label>
      <input id="updateRole_roleName" value="${role.roleName}" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>角色描述</label>
      <input id="updateRole_roleDesc" value="${role.roleDesc}" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input id="updateRole_remark" value="${role.remark}" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="updateRole_ensureBtn">确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#roleManage_updateRoleDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
  
	// 随页面加载
    $(function(){
    	updateRole_bindEvent();
    });
    
	// 绑定事件
	function updateRole_bindEvent() {
		$('#updateRole_ensureBtn').on('click', function() {
			var roleName = $("#updateRole_roleName").val().trim();
			var roleDesc = $("#updateRole_roleDesc").val().trim();
	    	var remark = $("#updateRole_remark").val().trim();
	    	var rows = $('#roleManage_table').datagrid('getSelections');
	    	var roleId = rows[0].roleId;
	    	$.ajax({
	  			  type: "post",
	  			  url: "${pageContext.request.contextPath}/roleManage/updateRole.do",
	  			  data: {
	  				  "roleId": roleId,
	  				  "roleName": roleName,
	  				  "roleDesc": roleDesc,
	  				  "remark": remark
	  			  },
	  			  dataType: "json",
	  			  success: function(result) {
	  				  $.messager.show({
						  title : '提示',
						  msg : result.msg
					  });
					  if(result.code == "Y") {
						  $('#roleManage_updateRoleDialog').dialog('close');
						  $('#roleManage_table').datagrid('reload');
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