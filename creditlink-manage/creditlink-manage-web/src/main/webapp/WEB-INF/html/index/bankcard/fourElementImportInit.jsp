<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>导入</title>
</head>
<body>
  <div class="easyui-layout body-content" data-options="fit:true">
		<div class="content-padding" data-options="region:'north'">
			<form  id="importForm" method="post" enctype="multipart/form-data" class="form form-padding text-center">
		        <div class="form-group">
		          <input class="easyui-filebox form-control ipt-l" id="importFile" name="file" data-options="buttonIcon:'icon-folder',prompt:'请选择文件',buttonText:'选择文件夹'" style="height: 40px;">
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary" id="importBtnImport" onclick="bankCardFourElementImport()" data-options="iconCls:'icon-start'" disabled>导入</a>
		        </div>
	       </form>
		</div>
  </div>
  <script type="text/javascript">
    var fourElementImport = {
      init: function() {
        this.bindEvent();
      },
      bindEvent: function(){
        var $importBtnImport = $('#importBtnImport');
        $('#importFile').filebox({
          onChange: function(file){
            Boolean(file)?$importBtnImport.linkbutton('enable'):$importBtnImport.linkbutton('disable');
          }
        });
      }
    };
    
    $(function(){
    	fourElementImport.init();
    });
    
    //导入银行卡四要素
    function bankCardFourElementImport(){
    	var fval = $("#importFile").filebox("getValue");
    	if (fval == "" || fval == undefined) {
			//icon四种设置："error"、"info"、"question"、"warning"
			$.messager.alert("操作提示", "请选择导入文件文件", "warning");
			return;
		}
		if (fval.indexOf(".xls") == -1) {
			$.messager.alert("操作提示", "导入文件格式只能为EXCEL2003文件，文件后缀为.xls", "warning");
			return;
		}
		$.messager.progress();
    	$('#importForm').form('submit', {
	 		url : '${pageContext.request.contextPath}/index/bankcard/fourElementImport.do',   
			onSubmit : function() {
				return true;
			},
			success : function(data) {
				$.messager.progress('close');
				var returnData = eval('('+data+')');
				var returnMsg = returnData.msg;
				$.messager.show({
					title:'提示',
					msg:returnMsg,
					showType:'show'
				});
			},
			error:function (){
				$.messager.progress('close');
				$.messager.show({
					title:'提示',
					msg:'上传失败',
					showType:'show'
				});
			}  
		});
    } 
  </script>
</body>
</html>