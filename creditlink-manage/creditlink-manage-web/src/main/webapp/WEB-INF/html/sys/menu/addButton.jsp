<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>新增按钮</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="addButton_form">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>按钮名称</label>
      <input id="addButton_menuName" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" style="height:40px;">
    </div>
	<div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>按钮路径</label>
      <input id="addButton_menuUrl" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input id="addButton_remark" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="addButton_ensureBtn" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#menuManage_addButtonDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
  // 随页面加载
  $(function(){
	  addButton_init();
  })
  
  // 初始化
  function addButton_init() {
	  addButton_bindEvent();
	  setTimeout(function(){
          addCustomFun.validateFormIsPass($('#addButton_form'),$('#addButton_ensureBtn'));
        },0)
  }
  
  // 绑定事件
  function addButton_bindEvent() {
	  var $isLeafUnion = $('#addButton_isLeafUnion');
	  // 是否叶子节点单选框
      $('#addButton_isLeaf').on('click', function(){
        if($(this).is(':checked')){
          $isLeafUnion.removeClass('form-hide').addClass('form-show');
        }else{
          $isLeafUnion.removeClass('form-show').addClass('form-hide');
        }
      });
      // 添加一级菜单
      $('#addButton_ensureBtn').on('click', function() {
    	  var menuName = $("#addButton_menuName").val().trim();
    	  var menuUrl = $("#addButton_menuUrl").val().trim();
    	  var remark = $("#addButton_remark").val().trim();
    	  var rows = $('#menuManage_table').datagrid('getSelections');
    	  var menuParId = rows[0].menuId;
    	  $.ajax({
  			  type: "post",
  			  url: "${pageContext.request.contextPath}/menuManage/addButton.do",
  			  data: {
  				  "menuParId": menuParId,
  				  "menuName": menuName,
  				  "menuUrl": menuUrl,
  				  "remark": remark
  			  },
  			  dataType: "json",
  			  success: function(result) {
  				  $.messager.show({
					  title : '提示',
					  msg : result.msg
				  });
				  if(result.code == "Y") {
					  $('#menuManage_addButtonDialog').dialog('close');
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
      });
  }
  </script>
</body>
</html>