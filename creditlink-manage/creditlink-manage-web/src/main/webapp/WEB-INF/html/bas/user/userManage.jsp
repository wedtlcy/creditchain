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
			<form class="form horizontal form-padding" id="userManage_searchForm">
				<div class="form-group">
					<label class="textbox-label textbox-label-before">用户名</label> 
					<input id="userManage_userName" name="userName" class="easyui-textbox form-control ipt-xxs"
						data-options="" style="height:32px;">
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">用户状态</label> 
					<select id="userManage_userStatus" name="userStatus" class="easyui-combobox drop-down ipt-xxs"
						data-options="editable: false" style="height:32px;">
						<option value="">全部</option>
						<option value="1">启用</option>
						<option value="0">禁用</option>
					</select>
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">联盟成员</label> 
					<select id="userManage_isMember" name="isMember" class="easyui-combobox drop-down ipt-xxs"
						data-options="editable: false" style="height:32px;">
						<option value="">全部</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
				<div class="form-group">
					<label class="textbox-label textbox-label-before">联盟成员ID</label> 
					<input id="userManage_memberId" name="memberId" class="easyui-textbox form-control ipt-xxs"
						data-options="" style="height:32px;">
				</div>
				<div class="form-group">
					<a id="userManage_searchBtn" class="easyui-linkbutton btn btn-primary btn-form-search"
						data-options="iconCls:'icon-search-white'">查询</a> 
					<input class="easyui-linkbutton btn btn-default" type="button" id="userManage_clearBtn" value="重置">
				</div>
			</form>
		</div>
		<div class="table-outer-wrap" data-options="region:'center'">
			<table class="table" id="userManage_table"></table>
		</div>
	</div>
	
<script type="text/javascript">
	
	// 随页面加载
	$(function(){
		userManage_init();
	})
	
	// 初始化
	function userManage_init() {
		userManage_bindEvent();
		userManage_renderTable();
		addCustomFun.addAutoClearTextBox();
	}
	
	// 绑定事件
	function userManage_bindEvent() {
		$('#userManage_searchBtn').on('click', function() {
			$('#userManage_table').datagrid("load", sy.serializeObject($("#userManage_searchForm").form()));
		});
		$('#userManage_clearBtn').on('click', function() {
			$("#userManage_userName").textbox('setValue','');
			$("#userManage_memberId").textbox('setValue','');
			$("#userManage_userStatus").combobox('setValue','');
			$("#userManage_isMember").combobox('setValue','');
		});
	}
	
	// 加载数据
	function userManage_renderTable() {
		var tbSearchPage = $('#userManage_table').datagrid({
			method : 'post',
			url : '${pageContext.request.contextPath}/userManage/query.do',
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
					field : 'userName',
					title : '用户名',
					width : '150'
				}, {
					field : 'userStatus',
					title : '用户状态',
					width : '120',
					formatter : userManage_formatStatus
				}, {
					field : 'isMember',
					title : '联盟成员',
					width : '120',
					formatter : userManage_formatIsMember
				}, {
					field : 'memberId',
					title : '联盟成员ID',
					width : '150'
				}, {
					field : 'userMobile',
					title : '联系电话',
					width : '150'
				}, {
					field : 'userEmail',
					title : '邮箱',
					width : '150'
				}, {
					field : 'createUser',
					title : '创建人',
					width : '120'
				}, {
					field : 'createTime',
					title : '创建时间',
					width : '160',
					formatter : userManage_formatDate
				}, {
					field : 'updateUser',
					title : '最后更新人',
					width : '120'
				}, {
					field : 'updateTime',
					title : '最后更新时间',
					width : '160',
					formatter : userManage_formatDate
				}, {
					field : 'remark',
					title : '备注',
					width : '150'
				}
			] ],
			toolbar : [ {
				id : "userManage",
				text : "新增",
				iconCls : "icon-add",
				handler : userManage_add
			}, {
				id : "userModify",
				text : "修改",
				iconCls : "icon-edit",
				handler : userManage_modify
			}, {
				id : "userStartUsing",
				text : "启用",
				iconCls : "icon-noforbidden",
				handler : userManage_start
			}, {
				id : "userForbidden",
				text : "禁用",
				iconCls : "icon-forbidden",
				handler : userManage_forbid
			}, {
		          id : "userMatchRole",
		          text : "关联角色",
		          iconCls : "icon-noforbidden",
		          handler : userManage_matchRole
		    }],
			onLoadSuccess : function(data) {}
		});
	}
	
	// 新增
	function userManage_add() {
		addCustomFun.openDialog('${pageContext.request.contextPath}/userManage/addView.do', '新增用户', 750, 460, "userManage_addUserDialog", "auto");
	}
	
	// 修改
	function userManage_modify() {
		var rows = $('#userManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择用户！'); 
			return ;
		}
		if(rows.length > 1) {
			$.messager.alert('提示','不能同时选择多个用户！'); 
			return ;
		}
		if(rows[0].userStatus == '0') {
			$.messager.alert('提示','不能选择已禁用的用户！'); 
			return ;
		}
		addCustomFun.openDialog('${pageContext.request.contextPath}/userManage/updateView.do?userId='+rows[0].userId, '修改用户', 750, 460, "userManage_updateUserDialog", "auto");
	}
	
	// 关联角色
	function userManage_matchRole() {
		var rows = $('#userManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择用户！'); 
			return ;
		}
		if(rows.length > 1) {
			$.messager.alert('提示','不能同时选择多个用户！'); 
			return ;
		}
		if(rows[0].userStatus == '0') {
			$.messager.alert('提示','不能选择已禁用的用户！'); 
			return ;
		}
		addCustomFun.openDialog('${pageContext.request.contextPath}/userManage/matchRoleView.do?userId='+rows[0].userId,'关联角色',1020,700,"userManage_macthRoleDialog",[],"auto");
	}
	
	// 启用
	function userManage_start() {
		userManage_updateStatus(1);
	}
	
	// 禁用
	function userManage_forbid() {
		userManage_updateStatus(0);
	}
	
	// 更新状态
	function userManage_updateStatus(newStatus) {
		var rows = $('#userManage_table').datagrid('getSelections');
		if(rows.length == 0) {
			$.messager.alert('提示','当前没有选择用户！'); 
			return ;
		}
		var ids = "";
		var status = "";
		for(var i=0; i<rows.length; i++) {
			ids = ids + rows[i].userId + ",";
			status = status + rows[i].userStatus + ",";
		}
		ids = ids.substring(0, ids.lastIndexOf(","));
		status = status.substring(0, status.lastIndexOf(","));
		if(status.indexOf("1") != -1 && newStatus == 1) {
			$.messager.alert('提示','不能启用已启用的用户！'); 
			return ;
		}
		if(status.indexOf("0") != -1 && newStatus == 0) {
			$.messager.alert('提示','不能禁用已禁用的用户！'); 
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
					url: "${pageContext.request.contextPath}/userManage/updateStatus.do?status="+newStatus+"&ids="+ids,
					dataType:'json', 
					success: function(result){
						$.messager.show({
							title : '提示',
							msg : result.msg
						});
						if(result.code == "Y") {
							$('#userManage_table').datagrid('reload');
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
	function userManage_formatStatus(value,row,index) {
		if (value=="1"){
			return "启用";
		} else {
			return "<font color='red'>禁用</font>";
		}
	}
	
	// 格式化是否联盟成员状态
	function userManage_formatIsMember(value,row,index) {
		if (value=="1"){
			return "<font color='red'>是</font>";
		} else {
			return "否";
		}
	}
	
	// 格式化时间
	function userManage_formatDate(value,row,index) {
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