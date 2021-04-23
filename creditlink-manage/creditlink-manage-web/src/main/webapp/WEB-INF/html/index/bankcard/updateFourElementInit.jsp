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
  <form class="dialog padding-common form vertical text-center" id="fourElementUpdate_form" method="post">
  	 <input id="id" name="id" type="hidden" value="${fourElement.id}">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>索引:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="bankCard" name="bankCard" data-options="required:true" style="height:40px;" value="${fourElement.bankCard}">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>IP 地址:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="ip" name="ip" data-options="required:true" style="height:40px;" value="${fourElement.ip}">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>PATH 地址:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="path" name="path" data-options="required:true" style="height:40px;" value="${fourElement.path}">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="remark" name="remark" data-options="" style="height:40px;" value="${fourElement.remark}">
    </div>
    <button class="btn btn-primary block-center" type="button" id="fourElementUpdateBtn" onclick="javascript:updateFourElement();" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#updateFourElement').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    var fourElementUpdatePage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#fourElementUpdate_form'),$('#fourElementUpdateBtn'));
        },0)
      },
      bindEvent: function(){
      }
    };

    $(function(){
      fourElementUpdatePage.init();
    });
    
    function updateFourElement(){
    	$.messager.progress();
    	$('#fourElementUpdate_form').form(
				'submit',
				{
					url : '${pageContext.request.contextPath}/index/bankcard/updateFourElement.do',
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
							fourElement.fourElementTable.datagrid('reload'); 
							$('#updateFourElement').dialog("close");
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