<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>角色管理</title>
</head>
<body>
	<div class="easyui-layout body-content" data-options="fit:true">
		<div class="content-padding" data-options="region:'north'">
			<form class="form horizontal form-padding" id="roleManage_searchForm">
				<div class="form-group">
					<label class="textbox-label textbox-label-before">角色名称</label> 
					<input id="roleManage_roleName" name="roleName" class="easyui-textbox form-control ipt-xxs"
						data-options="" style="height:32px;">
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">角色状态</label> 
					<select id="roleManage_status" name="status" class="easyui-combobox drop-down ipt-xxs"
						data-options="editable: false" style="height:32px;">
						<option value="">全部</option>
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select>
				</div>
				<div class="form-group">
					<a id="roleManage_searchBtn" class="easyui-linkbutton btn btn-primary btn-form-search"
						data-options="iconCls:'icon-search-white'">查询</a> 
					<input class="easyui-linkbutton btn btn-default" type="button" id="roleManage_clearBtn" value="重置">
				</div>
			</form>
		</div>
		<div class="table-outer-wrap" data-options="region:'center'">
			<table class="table" id="roleManage_table"></table>
		</div>
	</div>
	
<script type="text/javascript">
	
	// 随页面加载
	$(function(){
		roleManage_init();
	})
	
	// 初始化
	function roleManage_init() {
		roleManage_bindEvent();
		roleManage_renderTable();
		addCustomFun.addAutoClearTextBox();
	}
	
	// 绑定事件
	function roleManage_bindEvent() {
		$('#roleManage_searchBtn').on('click', function() {
			$('#roleManage_table').datagrid("load", sy.serializeObject($("#roleManage_searchForm").form()));
		});
		$('#roleManage_clearBtn').on('click', function() {
			$("#roleManage_roleName").textbox('setValue','');
			$("#roleManage_status").combobox('setValue','');
		});
	}
	
	// 加载数据
	function roleManage_renderTable() {
		var tbSearchPage = $('#roleManage_table').datagrid({
			method : 'post',
			url : '${pageContext.request.contextPath}/roleManage/query.do',
			fit : true,
			ctrlSelect : true,
			pagination : true,
			singleSelect : false,
			striped : true,
			autoRowHeight : true,
			columns : [ [
				{
					field : 'ck',
					checkbox : true
				}, {
					field : 'roleName',
					title : '角色名称',
					width : '200'
				}, {
					field : 'roleDesc',
					title : '角色描述',
					width : '200'
				}, {
					field : 'roleParId',
					title : '父角色ID',
					width : '150'
				},  {
					field : 'status',
					title : '角色状态',
					width : '120',
					formatter : roleManage_formatStatus
				}, {
					field : 'createUser',
					title : '创建人',
					width : '120'
				}, {
					field : 'createTime',
					title : '创建时间',
					width : '160',
					formatter : roleManage_formatDate
				}, {
					field : 'updateUser',
					title : '最后更新人',
					width : '120'
				}, {
					field : 'updateTime',
					title : '最后更新时间',
					width : '160',
					formatter : roleManage_formatDate
				}, {
					field : 'remark',
					title : '备注',
					width : '150'
				}
			] ],
			toolbar : [ {
				id : "roleManage",
				text : "新增",
				iconCls : "icon-add",
				handler : roleManage_add
			}, {
				id : "roleModify",
				text : "修改",
				iconCls : "icon-edit",
				handler : roleManage_modify
			}, {
				id : "roleStartUsing",
				text : "启用",
				iconCls : "icon-noforbidden",
				handler : roleManage_start
			}, {
				id : "roleForbidden",
				text : "禁用",
				iconCls : "icon-forbidden",
				handler : roleManage_forbid
			} ,{
		          id : "roleMatchMenu",
		          text : "关联菜单",
		          iconCls : "icon-noforbidden",
		          handler : roleManage_matchMenu
		    }],
			onLoadSuccess : function(data) {}
		});
	}
	
	// 新增
	function roleManage_add() {
		addCustomFun.openDialog('${pageContext.request.contextPath}/roleManage/addView.do', '新增角色', 750, 460, "roleManage_addRoleDialog", "auto");
	}
	
	// 修改
	function roleManage_modify() {
		var rows = $('#roleManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择角色！'); 
			return ;
		}
		if(rows.length > 1) {
			$.messager.alert('提示','不能同时选择多个角色！'); 
			return ;
		}
		addCustomFun.openDialog('${pageContext.request.contextPath}/roleManage/updateView.do?roleId='+rows[0].roleId, '修改角色', 750, 460, "roleManage_updateRoleDialog", "auto");
	}
	
	// 关联菜单
	function roleManage_matchMenu() {
		var rows = $('#roleManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择角色！'); 
			return ;
		}
		if(rows.length > 1) {
			$.messager.alert('提示','不能同时选择多个角色！'); 
			return ;
		}
		if(rows[0].status == '0') {
			$.messager.alert('提示','不能选择已禁用的角色！'); 
			return ;
		}
		addCustomFun.openDialog('${pageContext.request.contextPath}/roleManage/matchMenuView.do', '关联菜单', 750, 460, "roleManage_matchMenuDialog", "auto");
	}
	
	// 启用
	function roleManage_start() {
		roleManage_updateStatus(1);
	}
	
	// 禁用
	function roleManage_forbid() {
		roleManage_updateStatus(0);
	}
	
	// 更新状态
	function roleManage_updateStatus(newStatus) {
		var rows = $('#roleManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择角色！'); 
			return ;
		}
		var ids = "";
		var status = "";
		for(var i=0; i<rows.length; i++) {
			ids = ids + rows[i].roleId + ",";
			status = status + rows[i].status + ",";
		}
		ids = ids.substring(0, ids.lastIndexOf(","));
		status = status.substring(0, status.lastIndexOf(","));
		if(status.indexOf("1") != -1 && newStatus == 1) {
			$.messager.alert('提示','不能启用已启用的角色！'); 
			return ;
		}
		if(status.indexOf("0") != -1 && newStatus == 0) {
			$.messager.alert('提示','不能禁用已禁用的角色！'); 
			return ;
		}
		
		var showMsg = "";
		if(newStatus == 0) {
			showMsg = "确认禁用?"
		}
		else {
			showMsg = "确认启用?"
		}
		$.messager.confirm('确认', showMsg, function(r){
	    	if (r){
	    		$.ajax({
					type: "post",
					url: "${pageContext.request.contextPath}/roleManage/updateStatus.do?status="+newStatus+"&ids="+ids,
					dataType:'json', 
					success: function(result){
						$.messager.show({
							title : '提示',
							msg : result.msg
						});
						if(result.code == "Y") {
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
	        }
	    });
	}
	
	// 格式化状态
	function roleManage_formatStatus(value,row,index) {
		if (value=="1"){
			return "启用";
		} else {
			return "<font color='red'>禁用</font>";
		}
	}
		
	// 格式化时间
	function roleManage_formatDate(value,row,index) {
		if(value == null || value == '') {
			return value;
		}
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}

	// 格式化时间工具类
	Date.prototype.format = function(fmt) { 
	     var o = { 
	        "M+" : this.getMonth()+1,                 //月份 
	        "d+" : this.getDate(),                    //日 
	        "h+" : this.getHours(),                   //小时 
	        "m+" : this.getMinutes(),                 //分 
	        "s+" : this.getSeconds(),                 //秒 
	        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	        "S"  : this.getMilliseconds()             //毫秒 
	    }; 
	    if(/(y+)/.test(fmt)) {
	            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	    }
	     for(var k in o) {
	        if(new RegExp("("+ k +")").test(fmt)){
	             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	         }
	     }
	    return fmt; 
	}     
</script>
</body>
</html>