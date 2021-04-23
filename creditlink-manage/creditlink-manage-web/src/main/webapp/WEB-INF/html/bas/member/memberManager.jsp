<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>联盟成员管理</title>
</head>
<body>
  <div class="easyui-layout body-content" data-options="fit:true">
		<div class="content-padding" data-options="region:'north'">
			<form class="form horizontal form-padding" id="memberManager_searchform">
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">客户姓名</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="memberManager_custName" name="custName">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">客户简称</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="memberManager_custSname" name="custSname">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">状态</label>
		          <select class="easyui-combobox drop-down ipt-xxs" data-options="editable: false" style="height:32px;" id="memberManager_status" name="status">
		            <option value="">全部</option>
		            <option value="1">启用</option>
		            <option value="0">警用</option>
		          </select>
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="memberManagerBtnSearch" data-options="iconCls:'icon-search-white'">查询</a>
		           <input class="easyui-linkbutton btn btn-default" type="button" onclick="memberManagerClearParam()" value="清空" >
		        </div>
	      </form>
		</div>
		
		<div class="table-outer-wrap" data-options="region:'center'">	
      		<table class="table" id="memberManagerTable"></table>
		</div>
  </div>
  <script type="text/javascript">
   var memberManager = {
    init: function(){
      _this = this;
      this.memberManagerTable = $('#memberManagerTable');
      addCustomFun.addAutoClearTextBox();
      _this.bindEvent();
    },
    bindEvent: function(){
        $('#memberManagerBtnSearch').on('click', function(){          
        	searchmemberManager();
        });
    },
    renderTable: function(){
      var tbSearchPage = memberManager.memberManagerTable.datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/bas/member/memberManager/query.do',
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,
        autoRowHeight:true,
        columns:[[
          {field:'ck',checkbox:true},
          {field:'custId',title:'客户编号',width:'80'},
          {field:'custName',title:'客户姓名',width:'80'},
          {field:'custSname',title:'客户简称',width:'80'},
          {field:'addr',title:'公司地址',width:'200'},
          {field:'mobile',title:'公司电话',width:'100'},
          {field:'registName',title:'工商注册名称',width:'200'},
          {field:'orgCode',title:'组织机构代码',width:'150'},
          {field:'regNo',title:'营业执照号',width:'150'},
          {field:'taxRegistCode',title:'税务登记证号',width:'150'},
          {field:'descInfo',title:'经营内容',width:'150'},
          {field:'contactName',title:'联系人姓名',width:'100'},
          {field:'contactTel',title:'联系人电话',width:'100'},
          {field:'contactEmail',title:'联系人Email',width:'100'},
          {field:'status',title:'状态',width:'80',formatter : formatStatus},
          {field:'createTime',title:'创建时间',width:'200',formatter : encryption_formatDate},
          {field:'remark',title:'备注',width:'150'}
        ]],
        toolbar:[
		{
		    id : "add",
		    text : "新增",
		    iconCls : "icon-add",
		    handler : memberManager.add
		  },
		{
		    id : "modify",
		    text : "修改",
		    iconCls : "icon-edit",
		    handler : memberManager.modify
		  },
        {
          id : "startUsing",
          text : "启用",
          iconCls : "icon-noforbidden",
          handler : memberManager.startUsing
        },
        {
          id : "forbidden",
          text : "禁用",
          iconCls : "icon-forbidden",
          handler : memberManager.forbidden
        }],
        onLoadSuccess:function(data){
        }
      });
    },
    add: function(){
    	addCustomFun.openDialog('${pageContext.request.contextPath}/bas/member/addMemberInit.do','新增联盟成员',750,880,"addMember","auto");
	},
    modify: function(){
    	var rows = memberManager.memberManagerTable.datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
       addCustomFun.openDialog('${pageContext.request.contextPath}/bas/member/updateMemberInit.do?id=' + rows[0].custId,'修改联盟成员',750,900,"updateMember","auto");
    },
    startUsing: function(){
    	var rows = memberManager.memberManagerTable.datagrid('getSelections');
        if(rows.length == 0 ){
          $.messager.alert('警告','当前没有选择数据项！'); 
          return ;
        }else{
        	updateMemberStatus(1);
        }
    },
    forbidden: function(){
    	 var rows = memberManager.memberManagerTable.datagrid('getSelections');
         if(rows.length ==0 ){
           $.messager.alert('警告','当前没有选择数据项！'); 
           return ;
         }else{
        	 updateMemberStatus(0);
         }
    }
   };
 	//更新状态
   function updateMemberStatus(status){
	   var rows = memberManager.memberManagerTable.datagrid('getSelections');
		if(rows.length ==0 ){
			 $.messager.alert('警告','当前没有选择数据项！'); 
		     return ;
		}
		var ids="";
		for(var i=0; i < rows.length; i++){
			if(status == '0' && rows[i].status=='0'){//禁用
				$.messager.alert('警告','不能禁用已禁用的数据 ！','warning'); 
				return ;
			}
			if(status == '1' && rows[i].status=='1'){//启用
				$.messager.alert('警告','不能启用已启用的数据 ！','warning'); 
				return ;
			}
			ids = ids+rows[i].custId+",";
		}
		ids = ids.substring(0, ids.lastIndexOf(","));
		var showmsg = "";
		if(status == 0){
        	showmsg = "确认要禁用选中的索引吗?";
        }else{
        	showmsg = "确认要启用选中的索引吗?";
        }
		$.messager.confirm('确认', showmsg, function(ok) {
			if(ok){
				$.ajax({
					type: "post",
					url: "${pageContext.request.contextPath}/bas/member/updateMemberStatus.do?status="+status+"&ids="+ids,
					dataType:'json', 
					success: function(result){
						if (result.code == 'N') {
							$.messager.alert('提示', "操作:" + result.msg+" 错误代码："+result.code,
							'error');
						} else {
							$.messager.show({
								title : '提示',
								msg : result.msg
							});
							memberManager.memberManagerTable.datagrid('reload'); 
						}
					},
					error: function() {
						$.messager.alert('提示', "网络异常！",'error');
                    }
				}); 
			}
		});
   }
 
   //查找
   function searchmemberManager(){
	   memberManager.memberManagerTable.datagrid('load',sy.serializeObject($("#memberManager_searchform").form())); 
   }
   //页面初始化
   $(function(){
	   memberManager.init();
   	 //加载数据
   	 memberManager.renderTable();
   });
   
   function memberManagerClearParam(){
	   $("#memberManager_custName").textbox('setValue','');
	   $("#memberManager_custSname").textbox('setValue','');
   }
  </script>
</body>
</html>