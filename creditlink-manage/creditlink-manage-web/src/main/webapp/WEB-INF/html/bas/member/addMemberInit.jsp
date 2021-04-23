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
  <form class="dialog padding-common form vertical text-center" id="memberAdd_form" method="post">
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>客户姓名</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="custName" name="custName" data-options="required:true" validType="CHS" style="height:40px;">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>客户简称</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="custSname" name="custSname" data-options="required:true" validType="CHS" style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before">公司地址</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="addr" name="addr"  style="height:40px;">
    </div>
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>公司电话</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="mobile" name="mobile" data-options="required:true" validType="tel" style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before">工商注册名</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="registName" name="registName"  style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before">机构代码</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="orgCode" name="orgCode"  style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before">营业执照号</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="regNo" name="regNo"  style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before">税务证号</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="taxRegistCode" name="taxRegistCode"  style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before">经营内容</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="descInfo" name="descInfo"  style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>联系人姓名</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="contactName" name="contactName" data-options="required:true" validType="CHS" style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>联系人电话</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="contactTel" name="contactTel" data-options="required:true" validType="mobile" style="height:40px;">
    </div>
        <div class="form-group">
      <label class="textbox-label textbox-label-before">联系人邮箱</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="contactEmail" name="contactEmail"  style="height:40px;">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="remark" name="remark"  style="height:40px;">
    </div>
    <button class="btn btn-primary block-center" type="button" id="memberAddBtn" onclick="javascript:addMember();" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#addMember').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    var memberAddPage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#memberAdd_form'),$('#memberAddBtn'));
        },0);
      },
      bindEvent: function(){
      }
    };

    $(function(){
    	memberAddPage.init();
    });
    
    //新增联盟成员
    function addMember(){
    	$.messager.progress();
    	$('#memberAdd_form').form(
				'submit',
				{
					url : '${pageContext.request.contextPath}/bas/member/addMember.do',
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
							memberManager.memberManagerTable.datagrid('reload'); 
							$('#addMember').dialog("close");
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