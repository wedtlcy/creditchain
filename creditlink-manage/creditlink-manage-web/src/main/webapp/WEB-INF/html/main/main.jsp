<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1,requiresActiveX=ulue">
	<meta name="viewport" content="user-scalable=yes">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/static/images/fav.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/themes/custom/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/xldfPages.css">
  <title>信链多方可信数据链系统</title>
</head>
<body class="easyui-layout layout-body" data-options="fit:true">
	<div class="nav-left" data-options="region:'west',split:false,title:'West'">
		<div class="nav-header account-wrap">
			<div class="logo-wrap"><img src="${pageContext.request.contextPath}/static/images/idx_title_logo.png" alt=""></div>
			<div class="user-info-wrap">
				<div class="avatar-box"><img src="" onerror="javascript:this.src='${pageContext.request.contextPath}/static/images/bg/default_avatar.png';"alt=""></div>
				<h1 class="user-name"><span class="txt-name">${username}</span><button class="icon-setting" id="btnModifyPWD" type="button" title="修改密码">修改密码</button></h1>
				<!-- <p class="txt-hint">最近登录时间：<span class="txt-time">2017-12-22 15:02:27</span></p> -->
			</div>
		</div>
		<ul class="nav-body" id="leftMenuTree"></ul>
		<div class="nav-footer">
			<a class="easyui-linkbutton btn btn-block btn-info btn-quit" id="btnQuitLogin" data-options="iconCls:'icon-quit'">退出登录</a>
		</div>
	</div>
	<div class="content-center" data-options="region:'center'">
		<div id="centerBody" class="easyui-tabs content-body" data-options="fit:true,border:false,tabHeight:40">
			<div title="默认首页">
	    </div>
		</div>
	</div>

	<!-- script -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/easyui-lang-zh_CN.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/common/easyui.custom.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/plugins/formReg.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery-easyui-1.5.2/formReg.js"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/common.js"></script>
	<!-- <script type="text/javascript" src="./static/plugins/echarts.full.min.js"></script> -->
	
	<script>
	var sy = $.extend({}, sy);/*定义一个全局变量*/
	sy.serializeObject = function(form) { /*将form表单内的元素序列化为对象，扩展Jquery的一个方法*/
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
	
		var indexPage = {
			init: function(){
				_this = this;
				this.initMenuTree();
				this.showNavLeft();
				this.bindLeftNavEvent();
			},
			bindLeftNavEvent: function(){
				var $leftMenu = $('#leftMenu');
				$leftMenu.on('click', '.sub-link-item', function(e){
					var $this = $(this),
						$menuItem = $('.sub-link-item'),
						options = {
							'title': $this.text(),
							'url': $this.data('linkurl')
						};

					$menuItem.removeClass('selected');
					$this.addClass('selected');
					_this.openMenu(options);
				});

				$('#btnModifyPWD').on('click',function(){
					addCustomFun.openDialog('${pageContext.request.contextPath}/main/modifyPwdView.do','修改密码',600,400,"modifyPwdDialog","auto");
				});

				$('#btnQuitLogin').on('click',function(){
					$.messager.confirm('退出登录', '您确定要退出登录?', function(r){
            			if (r){
              				window.location = '${pageContext.request.contextPath}/login/logout.do';
            			}
          			});
				});
			},
			openMenu: function(options){
				var $centerBody = $('#centerBody');

				if ($centerBody.tabs('exists',options.title)){
					$centerBody.tabs('select', options.title);
				} else {
					$centerBody.tabs('add',{
						title:options.title,
						href:options.url,
						closable:true
					});
				}
			},
			showNavLeft: function(){
				var sw = $(window).width();
				if (sw < 1024){
					$('body').layout('collapse', 'west');
				}
			},
			initMenuTree: function(){
				$('#leftMenuTree').tree({
				    url:'${pageContext.request.contextPath}/main/menu.do',
				    method: 'get',
				    animate: true,
				    cascadeCheck: false,
				    onClick: function(node){
				    	var reqUrl = '${pageContext.request.contextPath}'  + node.url;
				    	if(node.url != '' && node.url != null){
								indexPage.openMenu({title:node.text,url:reqUrl});
				    	}
						}
				});
			}
		};
		$(function(){
			indexPage.init();
		});
	</script>
	
</body>
</html>