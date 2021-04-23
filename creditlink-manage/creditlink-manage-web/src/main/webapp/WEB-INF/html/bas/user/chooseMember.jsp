<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>联盟成员选择</title>
</head>
<body>
  <div class="easyui-layout body-content dialog" data-options="fit:true">
    <div data-options="region:'north'">
      <form class="form horizontal" id="chooseMember_searchForm">
        <div class="form-group">
          <label class="textbox-label textbox-label-before">成员名称</label>
          <input name="custName" class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;">
        </div>
        <div class="form-group">
          <label class="textbox-label textbox-label-before">成员简称</label>
          <input name="custSname" class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;">
        </div>
        <div class="form-group">
          <label class="textbox-label textbox-label-before">成员状态</label>
          <select name="status" class="easyui-combobox drop-down ipt-xxs" data-options="editable: false" style="height:32px;">
            <option value="">全部</option>
			<option value="1">启用</option>
			<option value="0">禁用</option>
          </select>
        </div>
        <div class="form-group">
          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="chooseMember_queryBtn" data-options="iconCls:'icon-search-white'">查询</a>
        </div>
      </form>
    </div>
    <div data-options="region:'center'" style="height: 500px;">
      <table id="chooseMember_table"></table>
    </div>
    <div class="text-center footer" data-options="region:'south'" style="height: 50px;">
      <button class="btn btn-primary block-center" type="button" id="chooseMember_ensureBtn">确认</button>
      <button class="btn btn-default block-center" type="button" onclick="$('#userManage_chooseMemberDialog').dialog('close')">取消</button>
    </div>
  </div>
  <script type="text/javascript">
    $(function(){
      chooseMember_bindEvent();
      var queryRoleInfoTb = $('#chooseMember_table').datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/userManage/queryMember.do',        
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,    
        autoRowHeight:true,
        rownumbers:true,
        columns:[[
        	{
				field : 'ck',
				checkbox : true
			}, {
				field : 'custName',
				title : '成员名称',
				width : '32%'
			}, {
				field : 'custSname',
				title : '成员简称',
				width : '32%'
			}, {
				field : 'status',
				title : '成员状态',
				width : '32%',
				formatter : chooseMember_formatStatus
			}
        ]],
        onLoadSuccess:function(data){
        },
      });
    })
    
    // 绑定事件
    function chooseMember_bindEvent() {
    	 // 查询
    	 $('#chooseMember_queryBtn').on('click', function(){
    		 $('#chooseMember_table').datagrid("load", sy.serializeObject($("#chooseMember_searchForm").form()));
    	 });
    	 // 确定
    	 $('#chooseMember_ensureBtn').on('click', function(){
    		 var rows = $('#chooseMember_table').datagrid('getSelections');
    		 if(rows.length == 0) {
    			 $.messager.alert('提示','当前没有选择联盟成员！'); 
    			 return ;
    		 }
    		 if(rows.length > 1) {
    		     $.messager.alert('提示','不能同时选择多个联盟成员！'); 
    			 return ;
    		 }
    		 if(rows[0].status == '0') {
    			 $.messager.alert('提示','不能选择已禁用的联盟成员！'); 
    			 return ;
    		 }
    		 callback_chooseMember(rows[0].custId ,rows[0].custName);
    		 $('#userManage_chooseMemberDialog').dialog('close');
    	 });
    }
    
    // 格式化状态
    function chooseMember_formatStatus(value,row,index) {
    	if (value=="1"){
			return "启用";
		} else {
			return "<font color='red'>禁用</font>";
		}
    }
  </script>
</body>
</html>