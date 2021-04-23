<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>今日索引</title>
</head>
<body>
  <div class="easyui-layout body-content" data-options="fit:true">
		<div class="content-padding" data-options="region:'north'">
			<form class="form horizontal form-padding" id="productManager_searchform">
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">产品名称</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="productManager_productName" name="productName">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">产品标识</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="productManager_productFlag" name="productFlag">
		        </div>
		         <div class="form-group">
		          <label class="textbox-label textbox-label-before">产品类型</label>
		          <select class="easyui-combobox drop-down ipt-xxs bg-white" data-options="editable: false" style="height:32px;" id="productManager_productType" name="productType">
		            <option value="">全部</option>
		            <option value="1">查询</option>
		            <option value="2">验证</option>
		          </select>
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">状态</label>
		          <select class="easyui-combobox drop-down ipt-xxs bg-white" data-options="editable: false" style="height:32px;" id="productManager_productStatus" name="productStatus">
		            <option value="">全部</option>
		            <option value="1">启用</option>
		            <option value="0">警用</option>
		          </select>
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="productManagerBtnSearch" data-options="iconCls:'icon-search-white'">查询</a>
		           <input class="easyui-linkbutton btn btn-default" type="button" onclick="productManagerClearParam()" value="清空" >
		        </div>
	      </form>
		</div>
		
		<div class="table-outer-wrap" data-options="region:'center'">	
      		<table class="table" id="productManagerTable"></table>
		</div>
  </div>
  <script type="text/javascript">
   var productManager = {
    init: function(){
      _this = this;
      this.productManagerTable = $('#productManagerTable');
      addCustomFun.addAutoClearTextBox();
      _this.bindEvent();
    },
    bindEvent: function(){
        $('#productManagerBtnSearch').on('click', function(){          
        	searchProductManager();
        });
    },
    renderTable: function(){
      var tbSearchPage = productManager.productManagerTable.datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/bas/product/productManager/query.do',
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,
        autoRowHeight:true,
        columns:[[
          {field:'ck',checkbox:true},
          {field:'productId',title:'产品编号',width:'80'},
          {field:'productName',title:'产品名称',width:'200'},
          {field:'productFlag',title:'产品标识',width:'80'},
          {field:'productType',title:'产品类型',width:'80',formatter : formatProductType},
          {field:'productStatus',title:'产品状态',width:'80',formatter : formatProductStatus},
          {field:'productTrait',title:'产品特点',width:'250'},
          {field:'productMark',title:'产品备注',width:'250'},
          {field:'createTime',title:'创建时间',width:'200',formatter : encryption_formatDate},
          {field:'remark',title:'备注',width:'150'}
        ]],
        toolbar:[
		{
		    id : "add",
		    text : "新增",
		    iconCls : "icon-add",
		    handler : productManager.add
		  },
		{
		    id : "modify",
		    text : "修改",
		    iconCls : "icon-edit",
		    handler : productManager.modify
		  },
        {
          id : "startUsing",
          text : "启用",
          iconCls : "icon-noforbidden",
          handler : productManager.startUsing
        },
        {
          id : "forbidden",
          text : "禁用",
          iconCls : "icon-forbidden",
          handler : productManager.forbidden
        }],
        onLoadSuccess:function(data){
        }
      });
    },
    add: function(){
    	addCustomFun.openDialog('${pageContext.request.contextPath}/bas/product/addProductInit.do','新增产品',750,460,"addProduct","auto");
	},
    modify: function(){
    	var rows = productManager.productManagerTable.datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
       addCustomFun.openDialog('${pageContext.request.contextPath}/bas/product/updateProductInit.do?id=' + rows[0].productId,'修改产品',750,460,"updateProduct","auto");
    },
    startUsing: function(){
    	var rows = productManager.productManagerTable.datagrid('getSelections');
        if(rows.length == 0 ){
          $.messager.alert('警告','当前没有选择数据项！'); 
          return ;
        }else{
            updateProductStatus(1);
        }
    },
    forbidden: function(){
    	 var rows = productManager.productManagerTable.datagrid('getSelections');
         if(rows.length ==0 ){
           $.messager.alert('警告','当前没有选择数据项！'); 
           return ;
         }else{
            updateProductStatus(0);
         }
    }
   };
 	//更新状态
   function updateProductStatus(status){
	   var rows = productManager.productManagerTable.datagrid('getSelections');
		if(rows.length ==0 ){
			 $.messager.alert('警告','当前没有选择数据项！'); 
		     return ;
		}
		var ids="";
		for(var i=0; i < rows.length; i++){
			if(status == '0' && rows[i].productStatus=='0'){//禁用
				$.messager.alert('警告','不能禁用已禁用的数据 ！','warning'); 
				return ;
			}
			if(status == '1' && rows[i].productStatus=='1'){//启用
				$.messager.alert('警告','不能启用已启用的数据 ！','warning'); 
				return ;
			}
			ids = ids+rows[i].productId+",";
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
					url: "${pageContext.request.contextPath}/bas/product/updateProductStatus.do?status="+status+"&ids="+ids,
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
							productManager.productManagerTable.datagrid('reload'); 
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
   function searchProductManager(){
	   productManager.productManagerTable.datagrid('load',sy.serializeObject($("#productManager_searchform").form())); 
   }
   //页面初始化
   $(function(){
	   productManager.init();
   	 //加载数据
   	 productManager.renderTable();
   });
   
   //格式化产品类型
   function formatProductType(val,row,index){
	   if(row.productType != null){
		   if(row.productType == 1){
			   return "查询";
		   }else if(row.productType == 2){
			   return "验证";
		   }else{
			   return "-";
		   }
		}else{
			return "-";
		}
   }
   
   //格式化状态描述
   function formatProductStatus(val,row,index){
	   if(row.productStatus != null){
		   if(row.productStatus == 0){
			   return "<font style='color:red'>已下架</font>";
		   }else if(row.productStatus == 1){
			   return "已发布";
		   }else{
			   return "-";
		   }
		}else{
			return "-";
		}
   }
   
   function productManagerClearParam(){
	   $("#productManager_productName").textbox('setValue','');
	   $("#productManager_productFlag").textbox('setValue','');
   }
  </script>
</body>
</html>