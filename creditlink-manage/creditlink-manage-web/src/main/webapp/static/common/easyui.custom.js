var addCustomFun = {
  addClearTextBox: function(jq){
    var theObj = $(jq);
    theObj.textbox({
      icons:[{
        iconCls:'icon-clear',
        handler: function(e){
          $(e.data.target).textbox('clear').textbox('textbox').focus();
          theObj.textbox('getIcon', 0).css('visibility', 'hidden');           
          if($(this).parent().parent().parent().find('.icon-validate')){
            $(this).parent().parent().parent().find('.icon-validate').remove();
          }
        }
      }],
      inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
        keyup: function (e) {
          var icon = theObj.textbox('getIcon', 0);
          if($(e.target).val().length>0){
            icon.css('visibility','visible');
          }else{
            icon.css('visibility','hidden');
          }
        }
      })        
    });
  },
  addAutoClearTextBox: function(){
    var _this = this,
      arr = $("input[addClear]");
    for(var i=0;i<arr.length;i++){
      var oneInput = $(arr[i]),
        theId = oneInput.attr("id").replace('.', '\\.'),
        theClass = oneInput.attr("class");
      if(theClass.indexOf("easyui-textbox") != -1){
        _this.addClearTextBox('#'+theId);
      }
    }
  },
  validateFormIsPass: function(formObj,btnObj){
    $(formObj).find('input[id*=_easyui_textbox_input]').on('input', function(){
      if($(this).val().length>0 ){
        $(formObj).form('validate')?$(btnObj).removeAttr('disabled'):$(btnObj).attr('disabled',true);
        $(this).focus();
      }else{
        if(!$(formObj).form('validate')){
          $(btnObj).attr('disabled',true);
        }
      }
    });
    $(formObj).find('.easyui-combobox').combobox({
        onHidePanel: function(){
          $(formObj).form('validate')?$(btnObj).removeAttr('disabled'):$(btnObj).attr('disabled',true);
        }
    });
    $(formObj).find('.easyui-datebox').datebox({
      onHidePanel: function(){
        $(formObj).form('validate')?$(btnObj).removeAttr('disabled'):$(btnObj).attr('disabled',true);
      }
    });
    $(formObj).find('.easyui-datetimebox').datetimebox({
      onHidePanel: function(){
        $(formObj).form('validate')?$(btnObj).removeAttr('disabled'):$(btnObj).attr('disabled',true);
      }
    });
    $(formObj).find('.icon-clear').on('click',function(){
      var $this = $(this),
        options = new Object()
        str = $this.parents('.textbox').siblings('.easyui-textbox').data('options') || 'required:false';
        strs = str.split(','); 
      for(var i = 0; i < strs.length; i ++) {
        options[strs[i].split(':')[0]]=(strs[i].split(':')[1]);
      }
      if($(formObj).form('validate') && options.required == 'true'){       
        $(btnObj).attr('disabled',true);
      }
    });
  },
  /**
   * 打开对话框
   * @param url      请求地址
   * @param title    标题
   * @param w        宽
   * @param h        高
   * @param divname  id值
   * @param overflow 滚动条
   */
  openDialog: function(url,title,w,h,divname,footerBtn,overflow,isCloseDialog){
    if($("#"+divname).length>=1){
      return false;
    }
    if(overflow==null || overflow=="" || overflow == undefined){
      overflow = "hidden";
    }    
    var timestamp = Date.parse(new Date());	
    var dlgname ;
    if(divname == null || divname == ""){
      dlgname = "openDialogDiv_"+timestamp;
    }else{
      dlgname = divname;
    }
    if(url.indexOf("?") != -1){
      url = url + '&timestamp='+timestamp;
    }else{
      url = url + '?timestamp='+timestamp;
    }
    var win = "<div id=\""+dlgname+"\" style=\"overflow: "+overflow+"\"></div>" ;
      $(parent.window.document.body).append(win);
      var obj = parent.window.$('#'+dlgname);
      obj.dialog({
        href:url,
        title : title,
        width : w,
        height : h,
        closed : false,
        cache : false,
        modal : true,
        buttons: footerBtn,
        closable:isCloseDialog, //关闭按钮功能
        onClose : function() { 
          obj.dialog('destroy').remove();
        }
    });	
  }
};


/* - ----------------------------------------------------------------------------------*/
var sy = $.extend({}, sy);/*定义一个全局变量 表单参数序列化*/
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

//阻止backspace 使IE Firefox页面回退的问题
function banBackSpace(e){
  var ev = e || window.event;
  //各种浏览器下获取事件对象
  var obj = ev.relatedTarget || ev.srcElement || ev.target ||ev.currentTarget;
  //按下Backspace键
  if(ev.keyCode == 8){
    var tagName = obj.nodeName //标签名称
    //如果标签不是input或者textarea则阻止Backspace
    if(tagName!='INPUT' && tagName!='TEXTAREA'){
      return stopIt(ev);
    }
    var tagType = obj.type.toUpperCase();//标签类型
    //input标签除了下面几种类型，全部阻止Backspace     
    if(tagName=='INPUT' && (tagType!='TEXT' && tagType!='TEXTAREA' && tagType!='PASSWORD')){
      return stopIt(ev);
    }
  //input或者textarea输入框如果不可编辑则阻止Backspace
    if((tagName=='INPUT' || tagName=='TEXTAREA') && (obj.readOnly==true || obj.disabled ==true)){
    return stopIt(ev);
    }
  }
}
function stopIt(ev){
  if(ev.preventDefault ){
    //preventDefault()方法阻止元素发生默认的行为
    ev.preventDefault();
  }
  if(ev.returnValue){
    //IE浏览器下用window.event.returnValue = false;实现阻止元素发生默认的行为
    ev.returnValue = false;
  }
  return false;
}