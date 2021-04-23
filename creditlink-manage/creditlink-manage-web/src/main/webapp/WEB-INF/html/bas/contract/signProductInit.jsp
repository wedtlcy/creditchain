<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>签订合同</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="signProduct_form" method="post">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>合同编号</label>
      <input id="contractId" name="contractId" type="hidden" value="${id }">
      <input class="easyui-textbox form-control ipt-sm bg-white" id="signProduct_contractId" name="signProduct_contractId" data-options="required:true,editable:false" style="height:40px;" value="${id }">
    </div>
	 <div class="form-group user-manage-select form-show" id="unionMenberBox">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>产品名称</label>      
       <input id="prodId" name="prodId" type="hidden" value="">
      <input class="easyui-textbox form-control ipt-sm bg-white" id="signProduct_productName" name="signProduct_productName" data-options="required:true,editable:false" style="height:40px;">
      <a class="link-user-manage-select" id="productManager" href="#">选择产品</a>
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="remark" name="remark"  style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="signProductSureBtn" onclick="javascript:signProduct();" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#signProductInit').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    var signProductPage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#signProduct_form'),$('#signProductSureBtn'));
        },0);
      },
      bindEvent: function(){
    	  $('#productManager').on('click', function(){
              addCustomFun.openDialog('${pageContext.request.contextPath}/bas/contract/signProductManager.do','选择产品',980,700,"signProductManager",[],"auto");
          });
      }
    };
	
    //初始化
    $(function(){
    	signProductPage.init();
    });
	
    //新增合同
    function signProduct(){
    	$.messager.progress();
    	$('#signProduct_form').form(
				'submit',
				{
					url : '${pageContext.request.contextPath}/bas/contract/signProduct.do',
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
							//contractManager.contractManagerTable.datagrid('reload'); 
							$('#signProductInit').dialog("close");
						}
					},
					onLoadError : function(){
						$.messager.alert('提示', "交易异常！",'error');
						$.messager.progress('close');
					}
				});
    }
	
   //选择产品回调
   function callback_signProductManager(productId, productName,productFlag){
	   $("#prodId").val(productId);
	   $("#signProduct_productName").textbox('setValue',productName);
	   $("#signProductSureBtn").removeAttr("disabled");
   }
  </script>
</body>
</html>