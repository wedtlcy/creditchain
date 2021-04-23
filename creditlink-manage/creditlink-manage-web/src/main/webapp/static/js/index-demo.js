var page = {
  init: function(){
     _this = this;
    this.container = $('body');
    this.bindEvent();
  },
  bindEvent: function(){
    var $leftMenu = $('#leftMenu'),
      $menuItem = $('.sub-link-item'),
      $homeItem= $('#homeItem');
    //首页首次加载,添加选中效果
    $leftMenu.find('.panel').eq(0).addClass('active');
    page.openContent($('#homeItem').data('linkurl'));
    // menu点击事件
    $menuItem.on('click',function(e){
        var $this = $(this);        
        $leftMenu.find('.panel').removeClass('active');
        $menuItem.removeClass('selected');
        $this.addClass('selected');
        page.openContent($this.data('linkurl'));
        if($leftMenu.find('.panel').eq(0).hasClass('active')){
          $leftMenu.find('.panel').eq(0).removeClass('active');
        }
    });
    // 一级栏目点击事件
    $leftMenu.find('.panel').find('.panel-header').on('click',function(){
      var $this = $(this);
      if($this.parent().index() == '0' || $this.parent().index() == '1'){
        $leftMenu.find('.panel').removeClass('active');
        $this.parent().addClass('active');
        $menuItem.removeClass('selected');
        page.openContent($this.next().data('linkurl'));

        //收起所有的menu
        var panels = $leftMenu.accordion("panels");
        $.each(panels, function(){
            this.panel('collapse'); 
        });
      }
    });
  },
  openContent: function(content){
    var $centerContent = $('#tabsContent');
    $centerContent.panel({
      href:content,
      noheader:true,
      cache: false,
      onLoad:function(){
      },
      onOpen: function(){
      }
    });
  }
}
$(function(){
  page.init();
})