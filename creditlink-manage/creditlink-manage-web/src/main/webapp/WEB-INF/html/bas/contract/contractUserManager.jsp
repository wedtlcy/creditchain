<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>选择用户</title>
</head>
<body>
  <div class="easyui-layout body-content dialog" data-options="fit:true">
    <div data-options="region:'north'">
      <form class="form horizontal" id="userManage_forContract_search">
      	 <input id="isMember" name="isMember" type="hidden" value="1">
        <div class="form-group">
          <label class="textbox-label textbox-label-before">成员名称</label>
          <input class="easyui-textbox form-control ipt-xxs" id="userName" name="userName" style="height:32px;">
        </div>
        <div class="form-group">
          <label class="textbox-label textbox-label-before">成员状态</label>
          <select class="easyui-combobox drop-down ipt-xxs" data-options="editable: false" id="userStatus" name="userStatus" style="height:32px;">
             <option value="">全部</option>
		     <option value="1">启用</option>
		     <option value="0">警用</option>
          </select>
        </div>
        <div class="form-group">
          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="" data-options="iconCls:'icon-search-white'" onclick="searchUserManager();">查询</a>
        </div>
      </form>
    </div>
    <div data-options="region:'center'" style="height: 500px;">
      <table id="queryRoleInfoTb"></table>
    </div>
    <div class="text-center footer" data-options="region:'south'" style="height: 50px;">
      <button class="btn btn-primary block-center" type="button" id="userManageBtnAddSure_forContract" onclick="userManageBtnAddSureForContract();">确认</button>
      <button class="btn btn-default block-center" type="button" onclick="$('#userManageChooseUninMenberModal_forContract').dialog('close')">取消</button>
    </div>
  </div>
  <script type="text/javascript">
    $(function(){
      var queryRoleInfoTb = $('#queryRoleInfoTb').datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/bas/contract/usersForAddContract.do?isMember=1',        
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,    
        autoRowHeight:true,
        rownumbers:true,
        columns:[[
          {field:'ck',checkbox:true},
          {field:'userId',title:'成员编号',align:'center',width:'32%'},
          {field:'userName',title:'成员名称',align:'center',width:'32%'},
          {field:'userStatus',title:'成员状态',align:'center',width:'36%',formatter : formatStatus}
        ]],
        onLoadSuccess:function(data){
        },
      });
    });    
    
    //查找
    function searchUserManager(){
    	 $('#queryRoleInfoTb').datagrid('load',sy.serializeObject($("#userManage_forContract_search").form())); 
    }
    
    function userManageBtnAddSureForContract(){
    	var rows = $('#queryRoleInfoTb').datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
		var userId = rows[0].userId;
		var userName = rows[0].userName;
		callback_contractUserManager(userId, userName);
		$('#userManageChooseUninMenberModal_forContract').dialog('close');
    }
    
    //格式化状态描述
    function formatStatus(val,row,index){
 	   if(row.userStatus != null){
 		   if(row.userStatus == 0){
 			   return "<font style='color:red'>禁用</font>";
 		   }else if(row.userStatus == 1){
 			   return "启用";
 		   }else{
 			   return "-";
 		   }
 		}else{
 			return "-";
 		}
    }
  </script>
</body>
</html>