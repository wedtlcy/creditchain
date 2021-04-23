<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>新增产品</title>
</head>
<body>
  <form class="dialog padding-common form vertical text-center" id="productAdd_form" method="post">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>产品名称:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="productName" name="productName" data-options="required:true" style="height:40px;">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>产品标识:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="productFlag" name="productFlag" data-options="required:true" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>产品特点:</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="productTrait" name="productTrait" data-options="required:true" style="height:40px;">
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
      <input class="easyui-textbox form-control ipt-sm bg-white" id="productMark" name="productMark" style="height:40px;" >
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="remark" name="remark" data-options="" style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="productAddBtn" onclick="javascript:addProduct();" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#addProduct').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    var productAddPage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#productAdd_form'),$('#productAddBtn'));
        },0)
      },
      bindEvent: function(){
      }
    };

    $(function(){
    	productAddPage.init();
    });
    
    //新增产品
    function addProduct(){
    	$.messager.progress();
    	$('#productAdd_form').form(
				'submit',
				{
					url : '${pageContext.request.contextPath}/bas/product/addProduct.do',
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
							$('#addProduct').dialog("close");
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