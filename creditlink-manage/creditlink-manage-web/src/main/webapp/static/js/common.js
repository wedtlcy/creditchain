//时间格式化(2018-02-01 09:34:19)
function encryption_formatDate(val,row,index){
        if(null == val || "" == val){
            return "";
        }
        var date = new Date(val);
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var min = date.getMinutes();
        var sec = date.getSeconds();
        if(month < 10){
            month = "0" + month;
        }
        if(day < 10){
            day = "0" + day;
        }
        if(hour < 10){
            hour = "0" + hour;
        }
        if(min < 10){
            min = "0" + min;
        }
        if(sec < 10){
            sec = "0" + sec;
        }
        var rltDate = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
        return rltDate;
}

//格式化状态描述(字段status)
function formatStatus(val,row,index){
	   if(row.status != null){
		   if(row.status == 0){
			   return "<font style='color:red'>禁用</font>";
		   }else if(row.status == 1){
			   return "启用";
		   }else{
			   return "-";
		   }
		}else{
			return "-";
		}
}