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
      <form class="form horizontal" id="signProductManager_search">
        <div class="form-group">
		          <label class="textbox-label textbox-label-before">产品名称</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="signProductManager_productName" name="productName">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">产品标识</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="signProductManager_productFlag" name="productFlag">
		        </div>
		         <div class="form-group">
		          <label class="textbox-label textbox-label-before">产品类型</label>
		          <select class="easyui-combobox drop-down ipt-xxs bg-white" data-options="editable: false" style="height:32px;" id="signProductManager_productType" name="productType">
		            <option value="">全部</option>
		            <option value="1">查询</option>
		            <option value="2">验证</option>
		          </select>
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">状态</label>
		          <select class="easyui-combobox drop-down ipt-xxs bg-white" data-options="editable: false" style="height:32px;" id="signProductManager_productStatus" name="productStatus">
		            <option value="">全部</option>
		            <option value="1">启用</option>
		            <option value="0">警用</option>
		          </select>
		        </div>
        <div class="form-group">
          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="" data-options="iconCls:'icon-search-white'" onclick="searchSignProductManager();">查询</a>
       		<input class="easyui-linkbutton btn btn-default" type="button" onclick="signProductManagerClearParam()" value="清空" >
        </div>
      </form>
    </div>
    <div data-options="region:'center'" style="height: 500px;">
      <table id="queryRoleInfoTb"></table>
    </div>
    <div class="text-center footer" data-options="region:'south'" style="height: 50px;">
      <button class="btn btn-primary block-center" type="button" id="btnSureForSignProductManager" onclick="btnSureForSignProductManager();">确认</button>
      <button class="btn btn-default block-center" type="button" onclick="$('#signProductManager').dialog('close')">取消</button>
    </div>
  </div>
  <script type="text/javascript">
    $(function(){
      var queryRoleInfoTb = $('#queryRoleInfoTb').datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/bas/contract/productQueryForSignProduct.do',        
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,    
        autoRowHeight:true,
        rownumbers:true,
        columns:[[
          {field:'ck',checkbox:true},
          {field:'productId',title:'产品编号',align:'center',width:'30%'},
          {field:'productName',title:'产品名称',align:'center',width:'30%'},
          {field:'productFlag',title:'产品标识',align:'center',width:'20%'},
          {field:'productStatus',title:'状态',align:'center',width:'20%',formatter : signProductManagerFormatStatus}
        ]],
        onLoadSuccess:function(data){
        },
      });
    });    
    
    //查找
    function searchSignProductManager(){
    	 $('#queryRoleInfoTb').datagrid('load',sy.serializeObject($("#signProductManager_search").form())); 
    }
    //确定选中的产品
    function btnSureForSignProductManager(){
    	var rows = $('#queryRoleInfoTb').datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
		var productId = rows[0].productId;
		var productName = rows[0].productName;
		var productFlag = rows[0].productFlag;
		callback_signProductManager(productId, productName,productFlag);
		$('#signProductManager').dialog('close');
    }
    
    //格式化状态描述
    function signProductManagerFormatStatus(val,row,index){
 	   if(row.productStatus != null){
 		   if(row.productStatus == 0){
 			   return "<font style='color:red'>禁用</font>";
 		   }else if(row.productStatus == 1){
 			   return "启用";
 		   }else{
 			   return "-";
 		   }
 		}else{
 			return "-";
 		}
    }
    
    function signProductManagerClearParam(){
    	 $("#signProductManager_productName").textbox('setValue','');
  	    $("#signProductManager_productFlag").textbox('setValue','');
    }
  </script>
</body>
</html>