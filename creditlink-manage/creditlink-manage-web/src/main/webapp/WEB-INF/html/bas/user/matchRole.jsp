<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>关联角色</title>
</head>
<body>
  <div class="easyui-layout body-content dialog" data-options="fit:true">
    <div data-options="region:'north'">
      <form class="form horizontal" id="matchRole_searchForm">
        <input id="matchRole_roleId" value="${roleId}" readonly="true" type="hidden">
        <div class="form-group">
          <label class="textbox-label textbox-label-before">角色名称</label>
          <input name="roleName" class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;">
        </div>
        <div class="form-group">
          <label class="textbox-label textbox-label-before">角色状态</label>
          <select name="status" class="easyui-combobox drop-down ipt-xxs" data-options="editable: false" style="height:32px;">
            <option value="">全部</option>
			<option value="1">启用</option>
			<option value="0">禁用</option>
          </select>
        </div>
        <div class="form-group">
          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="matchRole_queryBtn" data-options="iconCls:'icon-search-white'">查询</a>
        </div>
      </form>
    </div>
    <div data-options="region:'center'" style="height: 500px;">
      <table id="matchRole_table"></table>
    </div>
    <div class="text-center footer" data-options="region:'south'" style="height: 50px;">
      <button class="btn btn-primary block-center" type="button" id="matchRole_ensureBtn">确认</button>
      <button class="btn btn-default block-center" type="button" onclick="$('#userManage_macthRoleDialog').dialog('close')">取消</button>
    </div>
  </div>
  <script type="text/javascript">
    $(function(){
    	var queryRoleInfoTb = $('#matchRole_table').datagrid({
            method:'post',
            url:'${pageContext.request.contextPath}/userManage/queryRole.do',        
            fit:true,
            ctrlSelect:true,
            pagination: true,
            singleSelect:false,
            striped:true,    
            autoRowHeight:true,
            rownumbers:true,
            pageSize:100,
            pageList: [100], 
            columns:[[
            	{
    				field : 'ck',
    				checkbox : true
    			}, {
    				field : 'roleName',
    				title : '角色名称',
    				width : '32%'
    			}, {
    				field : 'roleDesc',
    				title : '角色描述',
    				width : '32%'
    			}, {
    				field : 'status',
    				title : '角色状态',
    				width : '32%',
    				formatter : matchRole_formatStatus
    			}
            ]],
            onLoadSuccess:function(data){
            	var roleId = $('#matchRole_roleId').val();
            	if(roleId != ''){
	            	var roleIds = roleId.split(",");
	                var rows = $("#matchRole_table").datagrid("getRows");
	                for(var i=0;i<rows.length;i++) {
	                	for(var j = 0; j < roleIds.length; j++ ){
		              	  if(rows[i].roleId == roleIds[j]) {
		              		  var index = $("#matchRole_table").datagrid("getRowIndex",rows[i]);
		              		  $("#matchRole_table").datagrid("checkRow",index);
		              	  }
	                	}
	                }
            	}
            },
          });
          matchRole_bindEvent();
    });
    
    // 绑定事件
    function matchRole_bindEvent() {
    	 // 查询
    	 $('#matchRole_queryBtn').on('click', function(){
    		 $('#matchRole_table').datagrid("load", sy.serializeObject($("#matchRole_searchForm").form()));
    	 });
    	 // 确定
    	 $('#matchRole_ensureBtn').on('click', function(){
    		 var rows = $('#matchRole_table').datagrid('getSelections');
    		 if(rows.length == 0) {
    			 $.messager.alert('提示','当前没有选择角色！'); 
    			 return ;
    		 }
/*     		 if(rows.length > 1) {
    		     $.messager.alert('提示','不能同时选择多个角色！'); 
    			 return ;
    		 } */
    		 if(rows[0].status == '0') {
    			 $.messager.alert('提示','不能选择已禁用的角色！'); 
    			 return ;
    		 }
    		 var userRows = $('#userManage_table').datagrid('getSelections');
    		 var userId = userRows[0].userId;
    		 var roleIds = "";//rows[0].roleId;
    		 for(var i = 0; i < rows.length; i ++){
    			 if(i == (rows.length-1)){
    				 roleIds = roleIds + rows[i].roleId;
    			 }else{
    				 roleIds = roleIds + rows[i].roleId + ",";
    			 }
    		 }
    		 $.ajax({
     			  type: "post",
     			  url: "${pageContext.request.contextPath}/userManage/matchRole.do",
     			  data: {
     				  "userId": userId,
     				  "roleIds": roleIds
     			  },
     			  dataType: "json",
     			  success: function(result) {
     				  $.messager.show({
	   					  title : '提示',
	   					  msg : result.msg
	   				  });
	   				  if(result.code == "Y") {
	   					  $('#userManage_macthRoleDialog').dialog('close');
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
    
    // 格式化状态
    function matchRole_formatStatus(value,row,index) {
    	if (value=="1"){
			return "启用";
		} else {
			return "<font color='red'>禁用</font>";
		}
    }
  </script>
</body>
</html>