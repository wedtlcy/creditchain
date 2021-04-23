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
			<form class="form horizontal form-padding" id="contractManager_searchform">
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">合同编号</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="contractManager_contractCode" name="contractCode">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">合同名称</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="contractManager_contractName" name="contractName">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">状态</label>
		          <select class="easyui-combobox drop-down ipt-xxs" data-options="editable: false" style="height:32px;" id="status" name="status">
		            <option value="">全部</option>
		            <option value="1">启用</option>
		            <option value="0">警用</option>
		          </select>
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="contractManagerBtnSearch" data-options="iconCls:'icon-search-white'">查询</a>
		           <input class="easyui-linkbutton btn btn-default" type="button" onclick="contractManagerClearParam()" value="清空" >
		        </div>
	      </form>
		</div>
		
		<div class="table-outer-wrap" data-options="region:'center'">	
      		<table class="table" id="contractManagerTable"></table>
		</div>
  </div>
  <script type="text/javascript">
   var contractManager = {
    init: function(){
      _this = this;
      this.contractManagerTable = $('#contractManagerTable');
      addCustomFun.addAutoClearTextBox();
      _this.bindEvent();
    },
    bindEvent: function(){
        $('#contractManagerBtnSearch').on('click', function(){          
        	searchContractManager();
        });
    },
    renderTable: function(){
      var tbSearchPage = contractManager.contractManagerTable.datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/bas/contract/contractManager/query.do',
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,
        autoRowHeight:true,
        columns:[[
          {field:'ck',checkbox:true},
          {field:'id',title:'合同编号',width:'80'},
          {field:'contractCode',title:'合同编号',width:'80'},
          {field:'contractName',title:'合同名称',width:'80'},
          {field:'contractType',title:'合同类别',width:'200'},
          {field:'contractCategory',title:'公司类型',width:'100'},
          {field:'custType',title:'合作类型',width:'200'},
          {field:'userId',title:'客户编号',width:'150'},
          {field:'status',title:'状态',width:'150',formatter : formatStatus},
          {field:'contractDetail',title:'合同详情',width :120,formatter : contractDetail_format},
          {field:'signDate',title:'签订日期',width:'150'},
          {field:'effDate',title:'生效日期',width:'150'},
          {field:'expDate',title:'失效日期',width:'80'},
          {field:'createTime',title:'创建时间',width:'200',formatter : encryption_formatDate},
          {field:'remark',title:'备注',width:'150'}
        ]],
        toolbar:[
		{
		    id : "add",
		    text : "新增",
		    iconCls : "icon-add",
		    handler : contractManager.add
		  },
		{
		    id : "modify",
		    text : "修改",
		    iconCls : "icon-edit",
		    handler : contractManager.modify
		  },
		{
			    id : "signProduct",
			    text : "签订产品",
			    iconCls : "icon-edit",
			    handler : contractManager.signProduct
		},
        {
          id : "startUsing",
          text : "启用",
          iconCls : "icon-noforbidden",
          handler : contractManager.startUsing
        },
        {
          id : "forbidden",
          text : "禁用",
          iconCls : "icon-forbidden",
          handler : contractManager.forbidden
        }],
        onLoadSuccess:function(data){
        }
      });
    },
    add: function(){
    	addCustomFun.openDialog('${pageContext.request.contextPath}/bas/contract/addContractInit.do','新增合同',750,700,"addContract","auto");
	},
    modify: function(){
    	var rows = contractManager.contractManagerTable.datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
       addCustomFun.openDialog('${pageContext.request.contextPath}/bas/contract/updateContractInit.do?id=' + rows[0].id,'修改合同',750,650,"updateContract","auto");
    },
    signProduct: function(){
    	var rows = contractManager.contractManagerTable.datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
		 addCustomFun.openDialog('${pageContext.request.contextPath}/bas/contract/signProductInit.do?id=' + rows[0].id,'签订产品',700,650,"signProductInit","auto");
    },
    startUsing: function(){
    	var rows = contractManager.contractManagerTable.datagrid('getSelections');
        if(rows.length == 0 ){
          $.messager.alert('警告','当前没有选择数据项！'); 
          return ;
        }else{
        	updateContractStatus(1);
        }
    },
    forbidden: function(){
    	 var rows = contractManager.contractManagerTable.datagrid('getSelections');
         if(rows.length ==0 ){
           $.messager.alert('警告','当前没有选择数据项！'); 
           return ;
         }else{
        	 updateContractStatus(0);
         }
    }
   };
 	//更新状态
   function updateContractStatus(status){
	   var rows = contractManager.contractManagerTable.datagrid('getSelections');
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
			ids = ids+rows[i].id+",";
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
					url: "${pageContext.request.contextPath}/bas/contract/updateContractStatus.do?status="+status+"&ids="+ids,
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
							contractManager.contractManagerTable.datagrid('reload'); 
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
   function searchContractManager(){
	   contractManager.contractManagerTable.datagrid('load',sy.serializeObject($("#contractManager_searchform").form())); 
   }
   //页面初始化
   $(function(){
	   contractManager.init();
   	 //加载数据
   	 contractManager.renderTable();
   });
   
   //格式化状态描述
   function formatcontractStatus(val,row,index){
	   if(row.status != null){
		   if(row.status == 0){
			   return "<font style='color:red'>禁用</font>";
		   }else if(row.status == 1){
			   return "启用";
		   }else{
			   return "-";
		   }
		}else{
			return "-";
		}
   }
   
   function contractManagerClearParam(){
	   $("#contractManager_contractCode").textbox('setValue','');
	   $("#contractManager_contractName").textbox('setValue','');
   }
   
   //合同详情
   function contractDetail_format(val,row,index){
	   var str="<a href=\"javascript:contractDetail_productQry(\'"+row.id+"\');\">客户合同产品详情</a>";
	     return str;
   }
   
   function contractDetail_productQry(id){
	   addCustomFun.openDialog('${pageContext.request.contextPath}/bas/contract/contractProductDetailInit.do?id=' + id,'合同产品详情',750,650,"contractProductDetailInit","auto");
   }
  </script>
</body>
</html>