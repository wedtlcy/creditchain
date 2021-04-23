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
  <form class="dialog padding-common form vertical text-center" id="contractUpdate_form" method="post">
  	 <input id="id" name="id" type="hidden" value="${contractInfo.id}">
 	<div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>合同编号</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="contractCode" name="contractCode" data-options="required:true" style="height:40px;" value="${contractInfo.contractCode}">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>合同名称</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="contractName" name="contractName" data-options="required:true" style="height:40px;" value="${contractInfo.contractName}">
    </div>
     <div class="form-group">
		  <label class="textbox-label textbox-label-before">合同类别</label>
		  <select class="easyui-combobox drop-down ipt-sm bg-white" data-options="editable: false" style="height:32px;" id="contractType" name="contractType">
		            <option value="01">战略合作协议</option>
		            <option value="02">保密协议</option>
		  </select>         
	</div>  
	 <div class="form-group">
		  <label class="textbox-label textbox-label-before">机构类型</label>
		  <select class="easyui-combobox drop-down ipt-sm bg-white" data-options="editable: false" style="height:32px;" id="contractCategory" name="contractCategory">
		            <option value="01">金融机构</option>
		            <option value="02">非金融机构</option>
		  </select>         
	</div>  
	 <div class="form-group">
		  <label class="textbox-label textbox-label-before">合作类型</label>
		  <select class="easyui-combobox drop-down ipt-sm bg-white" data-options="editable: false" style="height:32px;" id="custType" name="custType">
		           <option value="01">客户</option>
		           <option value="02">数据源供应商</option>
		           <option value="03">代理商</option>
		           <option value="04">战略合作伙伴</option>
		  </select>         
	</div>  
<!-- 	 <div class="form-group user-manage-select form-show" id="unionMenberBox">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>客户编号</label>      
      <input class="easyui-textbox form-control ipt-sm bg-white" id="userId" name="userId" data-options="" style="height:40px;">
      <a class="link-user-manage-select" id="contractUserManager" href="#">选择客户编号...</a>
    </div> -->
    <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>签订日期</label>
      <input class="easyui-datetimebox form-control ipt-sm bg-white" id="signDateStr" name="signDateStr" data-options="required:true,formatter:formatter" style="height:40px;" value="${contractInfo.signDate}">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>生效日期</label>
      <input class="easyui-datetimebox form-control ipt-sm bg-white" id="effDateStr" name="effDateStr" data-options="required:true,formatter:formatter" style="height:40px;" value="${contractInfo.effDate}">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before"><span class="color-red">*</span>失效日期</label>
      <input class="easyui-datetimebox form-control ipt-sm bg-white" id="expDateStr" name="expDateStr" data-options="required:true,formatter:formatter" style="height:40px;" value="${contractInfo.expDate}">
    </div>
     <div class="form-group">
      <label class="textbox-label textbox-label-before">备注</label>
      <input class="easyui-textbox form-control ipt-sm bg-white" id="remark" name="remark"  style="height:40px;" value="${contractInfo.remark}">
    </div>
    <button class="btn btn-primary block-center" type="button" id="contractUpdateBtn" onclick="javascript:updateContract();" disabled>确认</button>
    <button class="btn btn-default block-center" type="button" onclick="$('#updateContract').dialog('close')">取消</button>
  </form>
  <script type="text/javascript">
    var contractUpdatePage = {
      init: function(){
        var _this = this;
        this.bindEvent();
        setTimeout(function(){
          addCustomFun.validateFormIsPass($('#contractUpdate_form'),$('#contractUpdateBtn'));
        },0);
      },
      bindEvent: function(){
      }
    };

    $(function(){
    	contractUpdatePage.init();
    	$("#signDateStr").datetimebox({editable:false});
    	$("#effDateStr").datetimebox({editable:false});
    	$("#expDateStr").datetimebox({editable:false});
    });
    
    //修改产品
    function updateContract(){
    	$.messager.progress();
    	$('#contractUpdate_form').form(
				'submit',
				{
					url : '${pageContext.request.contextPath}/bas/contract/updateContract.do',
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
							contractManager.contractManagerTable.datagrid('reload'); 
							$('#updateContract').dialog("close");
						}
					},
					onLoadError : function(){
						$.messager.alert('提示', "交易异常！",'error');
						$.messager.progress('close');
					}
				});
    }
    //修改日历框的显示格式
    function formatter(date){
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        month = month < 10 ? '0' + month : month;
        day = day < 10 ? '0' + day : day;
        hour = hour < 10 ? '0' + hour : hour;
        return year + "-" + month + "-" + day;
    }
  </script>
</body>
</html>