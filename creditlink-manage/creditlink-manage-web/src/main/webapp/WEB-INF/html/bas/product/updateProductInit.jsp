<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>修改产品</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="productUpdate_form" method="post">
  	 <input id="productId" name="productId" type="hidden" value="${productInfo.productId}">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>产品名称:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="productName" name="productName" data-options="required:true" style="height:40px;" value="${productInfo.productName}">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>产品特点:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="productTrait" name="productTrait" data-options="required:true" style="height:40px;" value="${productInfo.productTrait}">
    </div>
     <div class="form-group">
		  <label class="textbox-label textbox-label-before">产品类型</label>
		  <select class="easyui-combobox drop-down ipt-sm bg-white" data-options="editable: false" style="height:32px;" id="productType" name="productType">
		            <option value="1">查询</option>
		            <option value="2">验证</option>
		  </select>         
	</div>        
    <div class="form-group">
      <label class="textbox-label textbox-label-before">产品备注:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="productMark" name="productMark" data-options="" style="height:40px;" value="${productInfo.productMark}">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="remark" name="remark" data-options="" style="height:40px;" value="${productInfo.remark}">
    </div>
    <button class="btn btn-primary block-center" type="button" id="productUpdateBtn" onclick="javascript:updateProduct();" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#updateProduct').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    var productUpdatePage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#productUpdate_form'),$('#productUpdateBtn'));
        },0)
      },
      bindEvent: function(){
      }
    };

    $(function(){
    	productUpdatePage.init();
    });
    
    //修改产品
    function updateProduct(){
    	$.messager.progress();
    	$('#productUpdate_form').form(
				'submit',
				{
					url : '${pageContext.request.contextPath}/bas/product/updateProduct.do',
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
							productManager.productManagerTable.datagrid('reload'); 
							$('#updateProduct').dialog("close");
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