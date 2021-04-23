<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>用户管理</title>
</head>
<body>
	<div class="easyui-layout body-content" data-options="fit:true">
		<div class="content-padding" data-options="region:'north'">
			<form class="form horizontal form-padding" id="menuManage_searchForm">
				<div class="form-group">
					<label class="textbox-label textbox-label-before">菜单编号</label> 
					<input id="menuManage_menuId" name="menuId" class="easyui-textbox form-control ipt-xxs"
						data-options="" style="height:32px;">
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">菜单名称</label> 
					<input id="menuManage_menuName" name="menuName" class="easyui-textbox form-control ipt-xxs"
						data-options="" style="height:32px;">
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">菜单路径</label> 
					<input id="menuManage_menuUrl" name="menuUrl" class="easyui-textbox form-control ipt-xxs"
						data-options="" style="height:32px;">
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">菜单类型</label> 
					<select id="menuManage_menuType" name="menuType" class="easyui-combobox drop-down ipt-xxs"
						data-options="editable: false" style="height:32px;">
						<option value="">全部</option>
						<option value="0">菜单</option>
						<option value="1">按钮</option>
					</select>
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">菜单状态</label> 
					<select id="menuManage_status" name="status" class="easyui-combobox drop-down ipt-xxs"
						data-options="editable: false" style="height:32px;">
						<option value="">全部</option>
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select>
				</div>
				<div class="form-group">
					<a id="menuManage_searchBtn" class="easyui-linkbutton btn btn-primary btn-form-search"
						data-options="iconCls:'icon-search-white'">查询</a> 
					<input class="easyui-linkbutton btn btn-default" type="button" id="menuManage_clearBtn" value="重置">
				</div>
			</form>
		</div>
		<div class="table-outer-wrap" data-options="region:'center'">
			<table class="table" id="menuManage_table"></table>
		</div>
	</div>
	
<script type="text/javascript">
	
	// 随页面加载
	$(function(){
		menuManage_init();
	})
	
	// 初始化
	function menuManage_init() {
		menuManage_bindEvent();
		menuManage_renderTable();
		addCustomFun.addAutoClearTextBox();
	}
	
	// 绑定事件
	function menuManage_bindEvent() {
		$('#menuManage_searchBtn').on('click', function() {
			$('#menuManage_table').datagrid("load", sy.serializeObject($("#menuManage_searchForm").form()));
		});
		$('#menuManage_clearBtn').on('click', function() {
			$("#menuManage_menuId").textbox('setValue','');
			$("#menuManage_menuName").textbox('setValue','');
			$("#menuManage_menuUrl").textbox('setValue','');
			$("#menuManage_menuType").combobox('setValue','');
			$("#menuManage_status").combobox('setValue','');
		});
	}
	
	// 加载数据
	function menuManage_renderTable() {
		var tbSearchPage = $('#menuManage_table').datagrid({
			method : 'post',
			url : '${pageContext.request.contextPath}/menuManage/query.do',
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
					field : 'menuId',
					title : '菜单编号',
					width : '120'
				}, {
					field : 'menuName',
					title : '菜单名称',
					width : '150'
				}, {
					field : 'menuParId',
					title : '父级菜单编号',
					width : '120'
				}, {
					field : 'menuUrl',
					title : '菜单路径',
					width : '220'
				}, {
					field : 'menuType',
					title : '菜单类型',
					width : '120',
					formatter : menuManage_formatType
				}, {
					field : 'isLeaf',
					title : '是否叶子菜单',
					width : '120',
					formatter : menuManage_formatIsLeaf
				}, {
					field : 'status',
					title : '菜单状态',
					width : '120',
					formatter : menuManage_formatStatus
				}, {
					field : 'createUser',
					title : '创建人',
					width : '120'
				}, {
					field : 'createTime',
					title : '创建时间',
					width : '160',
					formatter : menuManage_formatDate
				}, {
					field : 'updateUser',
					title : '最后更新人',
					width : '120'
				}, {
					field : 'updateTime',
					title : '最后更新时间',
					width : '160',
					formatter : menuManage_formatDate
				}, {
					field : 'remark',
					title : '备注',
					width : '150'
				}
			] ],
			toolbar : [ {
				id : "menuAdd",
				text : "菜单",
				iconCls : "icon-add",
				handler : menuManage_addMenu
			}, {
				id : "buttonAdd",
				text : "按钮",
				iconCls : "icon-add",
				handler : menuManage_addButton
			}, {
				id : "menuModify",
				text : "修改",
				iconCls : "icon-edit",
				handler : menuManage_modify
			}, {
				id : "menuStartUsing",
				text : "启用",
				iconCls : "icon-noforbidden",
				handler : menuManage_start
			}, {
				id : "menuForbidden",
				text : "禁用",
				iconCls : "icon-forbidden",
				handler : menuManage_forbid
			} ],
			onLoadSuccess : function(data) {}
		});
	}
	
	// 新增菜单
	function menuManage_addMenu() {
		var rows = $('#menuManage_table').datagrid('getSelections');
		if(rows.length > 1) {
			$.messager.alert('提示','不能同时选择多项父级菜单！'); 
			return ;
		}
		// 新增一级菜单
		if(rows.length == 0) {
			addCustomFun.openDialog('${pageContext.request.contextPath}/menuManage/addMenuView.do?flag=1', '新增菜单', 750, 460, "menuManage_addTopMenuDialog", "auto");
			return ;
		}
		if(rows[0].isLeaf == '1' || rows[0].menuType == '1') {
			$.messager.alert('提示','不能在叶子节点菜单或按钮下添加菜单！'); 
			return ;
		}
		if(rows[0].status == '0') {
			$.messager.alert('提示','选择的父级菜单已被禁用！'); 
			return ;
		}
		// 新增非一级菜单
		addCustomFun.openDialog('${pageContext.request.contextPath}/menuManage/addMenuView.do?flag=2', '新增菜单', 750, 460, "menuManage_addMenuDialog", "auto");
	}
	
	// 新增按钮
	function menuManage_addButton() {
		var rows = $('#menuManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','请选择新增按钮的父级菜单！'); 
			return ;
		}
		if(rows.length > 1) {
			$.messager.alert('提示','不能同时选择多项父级菜单！'); 
			return ;
		}
		if(rows[0].isLeaf == '0' || rows[0].menuType == '1') {
			$.messager.alert('提示','不能在非叶子节点菜单或按钮下添加按钮！'); 
			return ;
		}
		if(rows[0].status == '0') {
			$.messager.alert('提示','选择的父级菜单已被禁用！'); 
			return ;
		}
		addCustomFun.openDialog('${pageContext.request.contextPath}/menuManage/addButtonView.do', '新增按钮', 750, 460, "menuManage_addButtonDialog", "auto");
	}
	
	// 修改
	function menuManage_modify() {
		var rows = $('#menuManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择菜单或按钮！'); 
			return ;
		}
		if(rows.length > 1) {
			$.messager.alert('提示','不能同时选择多个菜单或按钮！'); 
			return ;
		}
		addCustomFun.openDialog('${pageContext.request.contextPath}/menuManage/updateView.do?menuId='+rows[0].menuId, '修改菜单', 750, 460, "menuManage_updateMenuDialog", "auto");
	}
	
	// 启用
	function menuManage_start() {
		menuManage_updateStatus(1);
	}
	
	// 禁用
	function menuManage_forbid() {
		menuManage_updateStatus(0);
	}
	
	// 更新状态
	function menuManage_updateStatus(newStatus) {
		var rows = $('#menuManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择菜单或按钮！'); 
			return ;
		}
		var ids = "";
		var status = "";
		for(var i=0; i<rows.length; i++) {
			ids = ids + rows[i].menuId + ",";
			status = status + rows[i].status + ",";
		}
		ids = ids.substring(0, ids.lastIndexOf(","));
		status = status.substring(0, status.lastIndexOf(","));
		if(status.indexOf("1") != -1 && newStatus == 1) {
			$.messager.alert('提示','不能启用已启用的菜单或按钮！'); 
			return ;
		}
		if(status.indexOf("0") != -1 && newStatus == 0) {
			$.messager.alert('提示','不能禁用已禁用的菜单或按钮！'); 
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
					url: "${pageContext.request.contextPath}/menuManage/updateStatus.do?status="+newStatus+"&ids="+ids,
					dataType:'json', 
					success: function(result){
						$.messager.show({
							title : '提示',
							msg : result.msg
						});
						if(result.code == "Y") {
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
	        }
	    });
	}
	
	// 格式化菜单类型
	function menuManage_formatType(value,row,index) {
		if(value == '0') {
			return "菜单";
		} else{
			return "按钮";
		}
	}
	
	// 格式化状态
	function menuManage_formatStatus(value,row,index) {
		if(value=="1") {
			return "启用";
		} else {
			return "<font color='red'>禁用</font>";
		}
	}
	
	// 格式化是否叶子节点
	function menuManage_formatIsLeaf(value,row,index) {
		if(value == '1') {
			return "是";
		} else {
			return "否";
		}
	}
	
	// 格式化时间
	function menuManage_formatDate(value,row,index) {
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