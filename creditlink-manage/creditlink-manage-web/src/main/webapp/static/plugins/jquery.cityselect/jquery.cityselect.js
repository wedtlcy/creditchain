/*
Ajax 三级省市联动
http://code.ciaoca.cn/
日期：2012-7-18

settings 参数说明
-----
url:省市数据josn文件路径
prov:默认省份
city:默认城市
dist:默认地区（县）
nodata:无数据状态
------------------------------ */
(function($){
	$.fn.citySelect=function(settings){
		if(this.length<1){return;};

		// 默认值
		settings=$.extend({
			url:"../ent/allArea.do",
			prov:null,
			city:null,
			dist:null,
			nodata:null
		},settings);

		var box_obj=this;
		var prov_obj=box_obj.find(".prov");
		var city_obj=box_obj.find(".city");
		var dist_obj=box_obj.find(".dist");
		var prov_val=settings.prov;
		var city_val=settings.city;
		var dist_val=settings.dist;
		var city_json;
		var prov_id= 0;
		var city_id = 0;
		prov_obj.combobox({
			onSelect: function(node){
				prov_id = node.id;					
				city_obj.combobox({disabled:false});
				dist_obj.combobox({disabled:false});		
			}
		});
		city_obj.combobox({
			onSelect: function(node){
				city_id = node.id;
			}
		});		
		// 赋值市级函数
		var cityStart=function(){									
			if(prov_id<0||typeof(city_json.citylist[prov_id].children)=="undefined"){
				if(settings.nodata=="none"){
					city_obj.combobox({setValue:'',disabled:true});
					dist_obj.combobox({setValue:'',disabled:true});
				};
				return;
			};
			// 遍历赋值市级下拉列表
			temp_html=[];
			$.each(city_json.citylist[prov_id].children,function(i,city){
				var tempdata = {value:city.areaCode,text:city.areaName,id:i};
				temp_html.push(tempdata);
			});
			city_obj.combobox({data:temp_html});		
			city_obj.combobox('setValue',temp_html[0].value);
			distStart();
		};

		// 赋值地区（县）函数
		var distStart=function(){
			dist_obj.empty().attr("disabled",true);
			if(prov_id<0||city_id<0||typeof(city_json.citylist[prov_id].children[city_id])=="undefined"||typeof(city_json.citylist[prov_id].children[city_id].children)=="undefined"){
				if(settings.nodata=="none"){
					dist_obj.combobox({setValue: '',disabled:true});
				};
				return;
			};
			// 遍历赋值市级下拉列表
			temp_html=[];
			$.each(city_json.citylist[prov_id].children[city_id].children,function(i,dist){
				var tempdata = {value:dist.areaCode,text:dist.areaName,id:i};			
				temp_html.push(tempdata)
			});					
			dist_obj.combobox({data:temp_html});	
			dist_obj.combobox('setValue',temp_html[0].value,onchange);

		};

		var init=function(){
			// 遍历赋值省份下拉列表
			temp_html=[];
			$.each(city_json.citylist,function(i,prov){
				var tempdata = {value:prov.areaCode,text:prov.areaName,id:i};
				temp_html.push(tempdata);
			});
			prov_obj.combobox({data:temp_html});

			// 若有传入省份与市级的值，则选中。（setTimeout为兼容IE6而设置）
			setTimeout(function(){
				if(settings.prov!=null){
					prov_obj.combobox('setValue',settings.prov);
					cityStart();
					setTimeout(function(){
						if(settings.city!=null){
							city_obj.combobox('setValue',settings.city);
							distStart();
							setTimeout(function(){
								if(settings.dist!=null){
									dist_obj.combobox('setValue',settings.dist);
								};
							},1);
						};
					},1);
				};
			},1);

			// 选择省份时发生事件
			prov_obj.combobox({
				onChange: function(){
					cityStart();
				}
			});
			// 选择市级时发生事件
			city_obj.combobox({
				onChange: function(){
					distStart();
				}
			});

		};

		// 设置省市json数据
		if(typeof(settings.url)=="string"){
			$.getJSON(settings.url,function(json){
				city_json=json;
				init();
			});
		}else{
			city_json=settings.url;
			init();
		};
	};
})(jQuery);
