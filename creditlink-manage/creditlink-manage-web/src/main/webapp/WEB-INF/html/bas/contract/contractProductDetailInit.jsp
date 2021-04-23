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
			<form class="form horizontal form-padding" id="contractProductDetail_searchform">
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">产品名称</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="contractProductDetail_productName" name="productName">
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="contractProductDetailBtnSearch" data-options="iconCls:'icon-search-white'">查询</a>
		           <input class="easyui-linkbutton btn btn-default" type="button" onclick="contractProductDetailClearParam()" value="清空" >
		        </div>
	      </form>
		</div>
		
		<div class="table-outer-wrap" data-options="region:'center'">	
      		<table class="table" id="contractProductDetailTable"></table>
		</div>
  </div>
  <script type="text/javascript">
   var contractProductDetailManager = {
    init: function(){
      _this = this;
      this.contractProductDetailTable = $('#contractProductDetailTable');
      addCustomFun.addAutoClearTextBox();
      _this.bindEvent();
    },
    bindEvent: function(){
        $('#contractProductDetailBtnSearch').on('click', function(){          
        	searchContractProductDetail();
        });
    },
    renderTable: function(){
      var tbSearchPage = contractProductDetailManager.contractProductDetailTable.datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/bas/contract/contractProductManager/query.do?contractId=' + ${id},
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,
        autoRowHeight:true,
        columns:[[
          {field:'ck',checkbox:true},
          {field:'id',title:'合同产品编号',width:'150'},
          {field:'productId',title:'产品编号',width:'150'},
          {field:'productName',title:'产品名称',width:'200'},
          {field:'productFlag',title:'产品标识',width:'200'},
        ]],
        toolbar:[
        {
          id : "forbidden",
          text : "删除",
          iconCls : "icon-forbidden",
          handler : contractProductDetailManager.forbidden
        }],
        onLoadSuccess:function(data){
        }
      });
    },
    forbidden: function(){
    	 var rows = contractProductDetailManager.contractProductDetailTable.datagrid('getSelections');
         if(rows.length ==0 ){
           $.messager.alert('警告','当前没有选择数据项！'); 
           return ;
         }else{
        	 updateContractProductStatus(0);
         }
    }
   };
 	//更新状态
   function updateContractProductStatus(status){
	   var rows = contractProductDetailManager.contractProductDetailTable.datagrid('getSelections');
		if(rows.length ==0 ){
			 $.messager.alert('警告','当前没有选择数据项！'); 
		     return ;
		}
		var ids="";
		for(var i=0; i < rows.length; i++){
			if(status == '0' && rows[i].status=='0'){//禁用
				$.messager.alert('警告','不能删除已删除的数据 ！','warning'); 
				return ;
			}
			if(status == '1' && rows[i].status=='1'){//启用
				$.messager.alert('警告','不能删除已删除的数据 ！','warning'); 
				return ;
			}
			ids = ids+rows[i].id+",";
		}
		ids = ids.substring(0, ids.lastIndexOf(","));
		var showmsg = "";
		if(status == 0){
        	showmsg = "确认要删除选中的产品吗?";
        }else{
        	showmsg = "确认要启用选中的产品吗?";
        }
		$.messager.confirm('确认', showmsg, function(ok) {
			if(ok){
				$.ajax({
					type: "post",
					url: "${pageContext.request.contextPath}/bas/contract/updateContractProductStatus.do?status="+status+"&ids="+ids,
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
							contractProductDetailManager.contractProductDetailTable.datagrid('reload');
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
   function searchContractProductDetail(){
	   contractProductDetailManager.contractProductDetailTable.datagrid('load',sy.serializeObject($("#contractProductDetail_searchform").form())); 
   }
   //页面初始化
   $(function(){
	   contractProductDetailManager.init();
   	 //加载数据
   	 contractProductDetailManager.renderTable();
   });
 	
   
   function contractProductDetailClearParam(){
	   $("#contractProductDetail_productName").textbox('setValue','');
   }
   
  </script>
</body>
</html>