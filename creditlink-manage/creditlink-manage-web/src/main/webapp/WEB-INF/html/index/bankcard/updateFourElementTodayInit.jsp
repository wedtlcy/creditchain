<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>修改索引</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="fourElementAddedTempUpdate_form" method="post">
  	 <input id="id" name="id" type="hidden" value="${fourElementAddedTemp.id}">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>索引:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="bankCard" name="bankCard" data-options="required:true,editable:false" style="height:40px;" value="${fourElementAddedTemp.bankCard}">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>IP 地址:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="ip" name="ip" data-options="required:true" style="height:40px;" value="${fourElementAddedTemp.ip}">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>PATH 地址:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="path" name="path" data-options="required:true" style="height:40px;" value="${fourElementAddedTemp.path}">
    </div>
    <button class="btn btn-primary block-center" type="button" id="fourElementAddedTempUpdateBtn" onclick="javascript:updateFourElementAddedTemp();" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#updateFourElementToday').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    var fourElementAddedTempUpdatePage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#fourElementAddedTempUpdate_form'),$('#fourElementAddedTempUpdateBtn'));
        },0)
      },
      bindEvent: function(){
      }
    };

    $(function(){
      fourElementAddedTempUpdatePage.init();
    });
    
    function updateFourElementAddedTemp(){
    	$.messager.progress();
    	$('#fourElementAddedTempUpdate_form').form(
				'submit',
				{
					url : '${pageContext.request.contextPath}/index/bankcard/updateFourElementAddedTemp.do',
					onSubmit : function() {
						var isValid = $(this).form('validate');
						if (!isValid){
							$.messager.progress('close');	
						}
						return isValid;
					},
					success : function(data) {
						$.messager.progress('close');
						var result = eval('(' + data + ')');
						if (result.code != "Y") {
							$.messager.alert('提示', result.msg+"; 错误代码："+result.code,'warning');
						} else {
							$.messager.show({
								title : '提示',
								msg : result.msg
							});
							fourElementToday.fourElementTodayTable.datagrid('reload'); 
							$('#updateFourElementToday').dialog("close");
						}
					},
					onLoadError : function(){
						$.messager.alert('提示', "交易异常！",'error');
						$.messager.progress('close');
					}
				});
    }
  </script>
</body>
</html>