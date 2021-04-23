<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>索引管理</title>
</head>
<body>
  <div class="easyui-layout body-content" data-options="fit:true">
		<div class="content-padding" data-options="region:'north'">
			<form class="form horizontal form-padding" id="fourElement_searchform">
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">成员编号</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="fourElement_memberId" name="memberId">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">索引</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="fourElement_bankCard" name="bankCard">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">状态</label>
		          <select class="easyui-combobox drop-down ipt-xxs" data-options="editable: false" style="height:32px;" id="fourElement_status" name="status">
		            <option value="">全部</option>
		            <option value="1">启用</option>
		            <option value="0">警用</option>
		          </select>
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="fourElementBtnSearch" data-options="iconCls:'icon-search-white'">查询</a>
		          <input class="easyui-linkbutton btn btn-default" type="button" onclick="clearParam()" value="清空" >
		        </div>
	      </form>
		</div>
		
		<div class="table-outer-wrap" data-options="region:'center'">	
      		<table class="table" id="fourElementTable"></table>
		</div>
  </div>
  <script type="text/javascript">
   var fourElement = {
    init: function(){
      _this = this;
      this.fourElementTable = $('#fourElementTable');
      addCustomFun.addAutoClearTextBox();
      _this.bindEvent();
    },
    bindEvent: function(){
        $('#fourElementBtnSearch').on('click', function(){          
        	searchFourElement();
        });
    },
    renderTable: function(){
      var tbSearchPage = fourElement.fourElementTable.datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/index/bankcard/fourElement.do',
        fit:true,
        ctrlSelect:true,
        pagination: true,
        singleSelect:false,
        striped:true,
        autoRowHeight:true,
        columns:[[
          {field:'ck',checkbox:true},
          {field:'id',title:'编号',width:'80'},
          {field:'productId',title:'产品编号',width:'80'},
          {field:'memberId',title:'成员编号',width:'80'},
          {field:'bankCard',title:'索引',width:'300'},
          {field:'ip',title:'IP地址',width:'300'},
          {field:'path',title:'PATH地址',width:'300'},
          {field:'count',title:'查询次数',width:'80'},
          {field:'success',title:'成功次数',width:'80'},
          {field:'error',title:'失败次数',width:'80'},
          {field:'status',title:'状态',width:'70',formatter : formatStatus},
          {field:'createTime',title:'创建时间',width:'80'}
        ]],
        toolbar:[
		{
		    id : "modify",
		    text : "修改",
		    iconCls : "icon-edit",
		    handler : fourElement.modify
		  },
        {
          id : "startUsing",
          text : "启用",
          iconCls : "icon-noforbidden",
          handler : fourElement.startUsing
        },
        {
          id : "forbidden",
          text : "禁用",
          iconCls : "icon-forbidden",
          handler : fourElement.forbidden
        }],
        onLoadSuccess:function(data){
        }
      });
    },
    modify: function(){
		var rows = fourElement.fourElementTable.datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
       addCustomFun.openDialog('${pageContext.request.contextPath}/index/bankcard/updateFourElementInit.do?id=' + rows[0].id,'修改索引',750,460,"updateFourElement","auto");
    },
    startUsing: function(){
      var rows = fourElement.fourElementTable.datagrid('getSelections');
      if(rows.length == 0 ){
        $.messager.alert('警告','当前没有选择数据项！'); 
        return ;
      }else{
         updateFourElementStatus(1);
      }
    },
    forbidden: function(){
      var rows = fourElement.fourElementTable.datagrid('getSelections');
      if(rows.length ==0 ){
        $.messager.alert('警告','当前没有选择数据项！'); 
        return ;
      }else{
          updateFourElementStatus(0);
      }
    }
   };
   
   //更新状态
   function updateFourElementStatus(status){
	   var rows = fourElement.fourElementTable.datagrid('getSelections');
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
					url: "${pageContext.request.contextPath}/index/bankcard/updateStatusFourElement.do?status="+status+"&ids="+ids,
					dataType:'json', 
					success: function(result){
						if (result.code == 'N') {
							$.messager.alert('提示', "操作:" + result.msg+"; 错误代码："+result.code,
							'error');
						} else {
							$.messager.show({
								title : '提示',
								msg : result.msg
							});
							fourElement.fourElementTable.datagrid('reload'); 
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
   function searchFourElement(){
	   var memberId = $("#fourElement_memberId").textbox('getValue');
	   if(memberId != '' && $.trim(memberId) != ''  && memberId != null){
		   var re = /^[0-9]+.?[0-9]*$/;
		   var re2 = /^[1-9]+[0-9]*]*$/;
		   if(!re.test(memberId) || !re2.test(memberId)){
			   $.messager.alert('提示', "成员编号格式错误,只能包含数字！",'error');
			   return;
		   }
	   }
	   fourElement.fourElementTable.datagrid('load',sy.serializeObject($("#fourElement_searchform").form())); 
   }
   
   //页面初始化
   $(function(){
	   	 fourElement.init();
	   	 //加载数据
	   	 fourElement.renderTable();
   });
   
   function clearParam(){
	   $("#fourElement_memberId").textbox('setValue','');
	   $("#fourElement_bankCard").textbox('setValue','');
   }
  </script>
</body>
</html>