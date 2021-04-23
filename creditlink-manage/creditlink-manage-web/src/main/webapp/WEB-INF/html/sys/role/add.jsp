<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>新增角色</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="addRole_form">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>角色名称</label>
      <input id="addRole_roleName" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>角色描述</label>
      <input id="addRole_roleDesc" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input id="addRole_remark" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="addRole_ensureBtn" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#roleManage_addRoleDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
  // 随页面加载
  $(function(){
	  addRole_init();
  })
  
  // 初始化
  function addRole_init() {
	  addRole_bindEvent();
	  setTimeout(function(){
          addCustomFun.validateFormIsPass($('#addRole_form'),$('#addRole_ensureBtn'));
        },0)
  }
  
  // 绑定事件
  function addRole_bindEvent() {
      // 添加角色
      $('#addRole_ensureBtn').on('click', function() {
    	  var roleName = $("#addRole_roleName").val().trim();
    	  var roleDesc = $("#addRole_roleDesc").val().trim();
    	  var remark = $("#addRole_remark").val().trim();
    	  $.ajax({
  			  type: "post",
  			  url: "${pageContext.request.contextPath}/roleManage/addRole.do",
  			  data: {
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
					  $('#roleManage_addRoleDialog').dialog('close');
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