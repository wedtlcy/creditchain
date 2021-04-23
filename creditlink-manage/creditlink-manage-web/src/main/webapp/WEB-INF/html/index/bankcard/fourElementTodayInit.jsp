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
			<form class="form horizontal form-padding" id="fourElementAddedTemp_searchform">
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">成员编号</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="fourElementAddedTemp_memberId" name="memberId">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">索引</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="fourElementAddedTemp_bankCard" name="bankCard">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">状态</label>
		          <select class="easyui-combobox drop-down ipt-xxs" data-options="editable: false" style="height:32px;" id="fourElementAddedTemp_status" name="status">
		            <option value="">全部</option>
		            <option value="1">启用</option>
		            <option value="0">警用</option>
		          </select>
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="fourElementTodayBtnSearch" data-options="iconCls:'icon-search-white'">查询</a>
		          <input class="easyui-linkbutton btn btn-default" type="button" onclick="clearParam()" value="清空" >
		        </div>
	      </form>
		</div>
		
		<div class="table-outer-wrap" data-options="region:'center'">	
      		<table class="table" id="fourElementTodayTable"></table>
		</div>
  </div>
  <script type="text/javascript">
   var fourElementToday = {
    init: function(){
      _this = this;
      this.fourElementTodayTable = $('#fourElementTodayTable');
      addCustomFun.addAutoClearTextBox();
      _this.bindEvent();
    },
    bindEvent: function(){
        $('#fourElementTodayBtnSearch').on('click', function(){          
         // _this.renderTable();
        	searchFourElementAddedTemp();
        });
    },
    renderTable: function(){
      var tbSearchPage = fourElementToday.fourElementTodayTable.datagrid({
        method:'post',
        url:'${pageContext.request.contextPath}/index/bankcard/fourElementToday.do',
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
          {field:'ip',title:'ip地址',width:'300'},
          {field:'path',title:'地址',width:'300'},
          {field:'status',title:'状态',width:'70',formatter : fourElementTodayFormatStatus},
          {field:'createTime',title:'创建时间',width:'80'}
        ]],
        toolbar:[
		{
		    id : "modify",
		    text : "修改",
		    iconCls : "icon-edit",
		    handler : fourElementToday.modify
		  },
        {
          id : "startUsing",
          text : "启用",
          iconCls : "icon-noforbidden",
          handler : fourElementToday.startUsing
        },
        {
          id : "forbidden",
          text : "禁用",
          iconCls : "icon-forbidden",
          handler : fourElementToday.forbidden
        }],
        onLoadSuccess:function(data){
        }
      });
    },
    modify: function(){
    	var rows = fourElementToday.fourElementTodayTable.datagrid('getSelections');
		if(rows.length ==0 ){
			$.messager.alert('警告','当前没有选择数据项！','warning'); 
			return ;
		}
		if(rows.length >1 ){
			$.messager.alert('警告','不能同时选择多项数据！','warning'); 
			return ;
		}
       addCustomFun.openDialog('${pageContext.request.contextPath}/index/bankcard/updateFourElementTodayInit.do?id=' + rows[0].id,'修改索引',750,460,"updateFourElementToday","auto");
    },
    startUsing: function(){
      var rows = fourElementToday.fourElementTodayTable.datagrid('getSelections');
      if(rows.length == 0 ){
        $.messager.alert('警告','当前没有选择数据项！'); 
        return ;
      }else{
           updateFourElementAddedTempStatus(1);
      }
    },
    forbidden: function(){
      var rows = fourElementToday.fourElementTodayTable.datagrid('getSelections');
      if(rows.length ==0 ){
        $.messager.alert('警告','当前没有选择数据项！'); 
        return ;
      }else{
          updateFourElementAddedTempStatus(0);
      }
    }
   };
   
   //格式化状态描述
   function fourElementTodayFormatStatus(val,row,index){
	   if(row.status != null){
		   if(row.status == 0){
			   return "<font style='color:red'>不可用</font>";
		   }else if(row.status == 1){
			   return "可用";
		   }else if(row.status == 2){
			   return "移库完成";
		   }else{
			   return "-";
		   }
		}else{
			return "-";
		}
   }
   
   //更新状态
   function updateFourElementAddedTempStatus(status){
	   var rows = fourElementToday.fourElementTodayTable.datagrid('getSelections');
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
					url: "${pageContext.request.contextPath}/index/bankcard/updateStatusFourElementAddedTemp.do?status="+status+"&ids="+ids,
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
							fourElementToday.fourElementTodayTable.datagrid('reload'); 
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
   function searchFourElementAddedTemp(){
	   var memberId = $("#fourElementAddedTemp_memberId").textbox('getValue');
	   if(memberId != '' && $.trim(memberId) != ''  && memberId != null){
		   var re = /^[0-9]+.?[0-9]*$/;
		   var re2 = /^[1-9]+[0-9]*]*$/;
		   if(!re.test(memberId) || !re2.test(memberId)){
			   $.messager.alert('提示', "成员编号格式错误,只能包含数字！",'error');
			   return;
		   }
	   }
	   fourElementToday.fourElementTodayTable.datagrid('load',sy.serializeObject($("#fourElementAddedTemp_searchform").form())); 
   }
   //页面初始化
   $(function(){
   	 fourElementToday.init();
   	 //加载数据
   	 fourElementToday.renderTable();
   });
   
   function clearParam(){
	   $("#fourElementAddedTemp_memberId").textbox('setValue','');
	   $("#fourElementAddedTemp_bankCard").textbox('setValue','');
   }
  </script>
</body>
</html>