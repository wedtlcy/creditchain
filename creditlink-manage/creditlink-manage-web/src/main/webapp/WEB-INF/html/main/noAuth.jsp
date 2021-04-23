<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>权限</title>
</head>
<body>
<div class="text-center error-hint">
		<div class="error-403">	
			<img class="hint-img" src="${pageContext.request.contextPath}/static/images/hint/403.png" alt="">
			<div class="hint-txt-wrap">
				<h2>对不起</h2>
				<p>您没有访问权限!!!</p>
			</div>
		</div>
	</div>
  <script type="text/javascript">
  var pp = $('#centerBody').tabs('getSelected');
  $('#centerBody').tabs('update',{tab:pp,options:{title:'无权限'}});
  </script>
</body>
</html>