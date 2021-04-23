<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>索引加密</title>
</head>
<body>
  <div class="easyui-layout body-content" data-options="fit:true">
		<div class="content-padding" data-options="region:'north'">
			<form class="form horizontal form-padding" id="index_searchform">
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">加密数据</label>
		          <input class="easyui-textbox form-control ipt-xxs" data-options="" style="height:32px;" id="data" name="data">
		        </div>
		        <div class="form-group">
		          <label class="textbox-label textbox-label-before">加密索引:</label>
		           <label id="md5Data"></label>
		        </div>
		        <div class="form-group">
		          <a class="easyui-linkbutton btn btn-primary btn-form-search" id="Search" onclick="indexMd5()" data-options="iconCls:'icon-search-white'">加密</a>
		        </div>
	      </form>
		</div>
  </div>
  <script type="text/javascript">
  function indexMd5(){
	  var data = $("#data").textbox('getValue');
	  if(data == '' || $.trim(data) == ''  || data == null || data == undefined){
		  $.messager.alert('提示', "加密数据不能为空！",'error');
		   return;	  
	   }
	  $.ajax({
			type: "post",
			url: "${pageContext.request.contextPath}/main/md5.do?data="+data,
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
					$("#md5Data").html(result.result);
				}
			},
			error: function() {
				$.messager.alert('提示', "网络异常！",'error');
	      }
		}); 
  }
  </script>
</body>
</html>