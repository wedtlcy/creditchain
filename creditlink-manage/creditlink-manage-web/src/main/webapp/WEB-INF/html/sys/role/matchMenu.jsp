<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>关联菜单</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="matchMenu_form">
    <div class="form-group vertical">
      <label class="textbox-label textbox-label-before">选择关联菜单</label>
      <ul id="matchMenu_tree" class="contact-menu" data-options="method:'get',animate:true,checkbox:true"></ul>
    </div>
    <button class="btn btn-primary block-center" type="button" id="matchMenu_ensureBtn">确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#roleManage_matchMenuDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    $(function(){
    	var rows = $('#roleManage_table').datagrid('getSelections');
    	$('#matchMenu_tree').tree({
    	    url: '${pageContext.request.contextPath}/roleManage/matchMenuList.do?roleId=' + rows[0].roleId
    	});
    	matchMenu_bindEvent();
    });
    
    function matchMenu_bindEvent() {
    	$('#matchMenu_ensureBtn').on('click', function() {
    		// 获取所有选中的菜单ID
    		var nodes = $('#matchMenu_tree').tree('getChecked', ['checked','indeterminate']);
    		var menuIds = new Array();
    		for(var i=0; i<nodes.length; i++) {
    			menuIds[i] = nodes[i].id;
    		}
    		// 获取选择的角色ID
    		var rows = $('#roleManage_table').datagrid('getSelections');
    		var roleId = rows[0].roleId;
    		$.ajax({
    			  type: "post",
    			  url: "${pageContext.request.contextPath}/roleManage/matchMenu.do",
    			  data: {
    				  "roleId": roleId,
    				  "menuIds": menuIds
    			  },
    			  dataType: "json",
    			  success: function(result) {
    				  $.messager.show({
  					  	  title : '提示',
  					  	  msg : result.msg
  				  	  });
  				      if(result.code == "Y") {
  					      $('#roleManage_matchMenuDialog').dialog('close');
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