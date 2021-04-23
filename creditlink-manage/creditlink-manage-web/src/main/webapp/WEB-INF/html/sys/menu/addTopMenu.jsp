<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>新增菜单</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="addTopMenu_form">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>菜单名称</label>
      <input id="addTopMenu_menuName" class="easyui-textbox form-control ipt-sm bg-white" data-options="required:true" validType="CHS" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">叶子节点</label>
      <div class="textbox border-0 text-left ipt-sm bg-transparent checkbox-item">
        <input id="addTopMenu_isLeaf" class="ipt-checkbox" type="checkbox">
        <label for="addTopMenu_isLeaf"><i class="icon"></i>是</label>
      </div>
    </div>
    <div class="form-group user-manage-select form-hide" id="addTopMenu_isLeafUnion">
      <label class="textbox-label textbox-label-before">菜单路径</label> 
      <input id="addTopMenu_menuUrl" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input id="addTopMenu_remark" class="easyui-textbox form-control ipt-sm bg-white" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="addTopMenu_ensureBtn" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#menuManage_addTopMenuDialog').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
  // 随页面加载
  $(function(){
	  addTopMenu_init();
  })
  
  // 初始化
  function addTopMenu_init() {
	  addTopMenu_bindEvent();
	  setTimeout(function(){
          addCustomFun.validateFormIsPass($('#addTopMenu_form'),$('#addTopMenu_ensureBtn'));
        },0)
  }
  
  // 绑定事件
  function addTopMenu_bindEvent() {
	  var $isLeafUnion = $('#addTopMenu_isLeafUnion');
	  // 是否叶子节点单选框
      $('#addTopMenu_isLeaf').on('click', function(){
        if($(this).is(':checked')){
          $isLeafUnion.removeClass('form-hide').addClass('form-show');
        }else{
          $isLeafUnion.removeClass('form-show').addClass('form-hide');
        }
      });
      // 添加一级菜单
      $('#addTopMenu_ensureBtn').on('click', function() {
    	  var menuName = $("#addTopMenu_menuName").val().trim();
    	  var menuUrl = $("#addTopMenu_menuUrl").val().trim();
    	  var isLeaf = 0;
    	  if($('#addTopMenu_isLeaf').is(':checked')) {
    		  isLeaf = 1;
    		  if(menuUrl == null || menuUrl == '') {
    			  $.messager.alert('提示','叶子节点菜单路径不能为空！'); 
    			  return ;
    		  }
    	  }
    	  var remark = $("#addTopMenu_remark").val().trim();
    	  // 一级菜单父级菜单编号
    	  var menuParId = 0;
    	  $.ajax({
  			  type: "post",
  			  url: "${pageContext.request.contextPath}/menuManage/addMenu.do",
  			  data: {
  				  "menuParId": menuParId,
  				  "menuName": menuName,
  				  "menuUrl": menuUrl,
  				  "isLeaf": isLeaf,
  				  "remark": remark
  			  },
  			  dataType: "json",
  			  success: function(result) {
  				  $.messager.show({
					  title : '提示',
					  msg : result.msg
				  });
				  if(result.code == "Y") {
					  $('#menuManage_addTopMenuDialog').dialog('close');
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