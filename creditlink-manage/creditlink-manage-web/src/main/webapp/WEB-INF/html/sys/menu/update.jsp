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
  <form class="dialog padding-common form vertical text-center" id="updateMenu_form">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>菜单名称</label>
      <input id="updateMenu_menuName" value="${menu.menuName}" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">菜单路径</label>
      <input id="updateMenu_menuUrl" value="${menu.menuUrl}" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input id="updateMenu_remark" value="${menu.remark}" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="updateMenu_ensureBtn">确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#menuManage_updateMenuDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
  
	// 随页面加载
    $(function(){
    	updateMenu_bindEvent();
    });
    
	// 绑定事件
	function updateMenu_bindEvent() {
		$('#updateMenu_ensureBtn').on('click', function() {
			var menuName = $("#updateMenu_menuName").val().trim();
			var menuUrl = $("#updateMenu_menuUrl").val().trim();
	    	var remark = $("#updateMenu_remark").val().trim();
	    	var rows = $('#menuManage_table').datagrid('getSelections');
	    	var menuId = rows[0].menuId;
	    	if(rows[0].isLeaf == '1' && (menuUrl == null || menuUrl == '')) {
	    		$.messager.alert('提示','叶子节点菜单或按钮菜单路径不能为空！'); 
				return ;
	    	}
	    	$.ajax({
	  			  type: "post",
	  			  url: "${pageContext.request.contextPath}/menuManage/updateMenu.do",
	  			  data: {
	  				  "menuId": menuId,
	  				  "menuName": menuName,
	  				  "menuUrl": menuUrl,
	  				  "remark": remark
	  			  },
	  			  dataType: "json",
	  			  success: function(result) {
	  				  $.messager.show({
						  title : '提示',
						  msg : result.msg
					  });
					  if(result.code == "Y") {
						  $('#menuManage_updateMenuDialog').dialog('close');
						  $('#menuManage_table').datagrid('reload');
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