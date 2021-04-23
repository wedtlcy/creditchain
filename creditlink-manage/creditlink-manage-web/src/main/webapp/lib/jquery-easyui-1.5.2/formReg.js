/**
 * 表单验证
 */
$.extend($.fn.textbox.defaults.rules, {
	/**
	 * 用户名格式验证 长度在6—20之间且为字母数字下划线组合
	 */
	username : {
		validator : function(value, param) {
			return value.length >= 6 && value.length <=20 && /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/.test(value);
		},
		message : '长度必须在6到20个字符之间,且必须以字母开头,由字母数字下划线组成!'
	},
	/**
	 * 汉字
	 */
	CHS : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message : '请输入汉字'
	},
	/** 
	 * 邮编
	 */
	ZIP : {
		validator : function(value, param) {
			return /^[1-9]\d{5}$/.test(value);
		},
		message : '邮政编码不存在'
	},
	/**
	 * QQ
	 */
	QQ : {
		validator : function(value, param) {
			return /^[1-9]\d{4,10}$/.test(value);
		},
		message : 'QQ号码不正确'
	},
	/**
	 * 手机号
	 */
	mobile : {
		validator : function(value, param) {
			//return /^(13[0-9]|15[0-35-9]|18[0-9]|14[57])\d{8}$/.test(value);
			return /^(1)\d{10}$/.test(value);
		},
		message : '手机号码不正确'
	},
	/**
	 * 固定电话
	 */
	tel : {
		validator : function(value, param) {
			return /^(([0]\d{2,3}-\d{7,8})|([0]\d{2,3}\d{7,8}))$/.test(value);
		},
		message : '电话号码不正确'
	},
	/**
	 * 邮箱
	 */
	email : {
		validator : function(value, param) {
			return /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(value);
		},
		message : '邮箱不正确'
	},
	/**
	 * 汉字、英文字母、数字及下划线
	 */
	loginName : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message : '只允许输入汉字、英文字母、数字及下划线。'
	},
	/**
	 * 字母、数字 6位
	 */
	safepass : {
		validator : function(value, param) {
			return safePassword(value);
		},
		message : '密码由字母和数字组成，至少6位'
	},
	/**
	 * 字母、数字
	 */
	numChar : {
		validator : function(value, param) {
			return /^[A-Za-z0-9]+$/.test(value);
		},
		message : '请输入字母、数字'
	},
	/**
	 * 2次输入字符验证
	 */
	equalTo : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次输入的字符不一至'
	},
	/**
	 * 数字
	 */
	number : {
		validator : function(value, param) {
			return /^[1-9]\d*$/.test(value);
		},
		message : '请输入数字'
	},
	/**
	 * 金额
	 */
	money : {
		validator : function(value, param) {
			return /^([1-9]\d*|0)(\.\d{1,2})?$/.test(value);
		},
		message : '请输入金额'
	},
	/**
	 * 身份证号
	 */
	idcard : {
		validator : function(value, param) {
			return idCard(value);
			//return /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/.test(value);
		},
		message : '请输入正确的身份证号码'
	},
	/** 供应商密码 */
	supplierPassword : {
		validator : function(value, param) {
			return supplierPassword(value);
		},
		message : '密码由字母和数字组成，不得超过32位'
	},
	/** 特殊字符 */
	specialCharacter : {
		validator : function(value, param) {
			return !(/[~!@#$%\^\+\*&\\\/\?\|.，！：#￥%……&*（）,。！:\<>《》？“”￥（）{}()'="]/.test(value));
		},
		message : '不得输入特殊字符'
	},
	/**保留两位小数*/
	RDP : {
		validator : function(value, param) {
			return doubleNum(value);
		},
		message : '精确到小数点后2位'
	},
	/**联系方式*/
	phoneNum : {
		validator : function(value, param) {
			return telAndPhone(value);
		},
		message : '请输入正确的联系方式'
	},
	/**银行卡号*/
	cardNum:{
		validator : function(value, param) {
			return /^\d{16,19}$/.test(value);
		},
		message : '请输入正确的银行卡号'
	},
	checkTel :{
		validator : function(value, param){
	    var isMobile = /^0{0,1}(13[0-9]|15[7-9]|153|156|18[7-9]|176|178)[0-9]{8}$/;
	    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	 
	    if(isMob.test(value)||isMobile.test(value)){
	        return true;
	    }
	  },
	   message : '请输入正确的手机号码！'
	}
});

/**联系方式：固定电话或手机号码*/
var telAndPhone = function (value){
	var isPhone=/^(([0]\d{2,3}-\d{7,8})|([0]\d{2,3}\d{7,8}))$/;
	var isMobile=/^(13[0-9]|15[0-35-9]|18[0-9]|14[57])\d{8}$/;
	if(isPhone.test(value) || isMobile.test(value)){
		return true;
	}else{
		return false;
	}
};

/**保留两位小数*/
var doubleNum = function (value){
	return /^\d+(\.\d{2})?$/.test(value);
};

/* 密码由字母和数字组成，至少6位 */
var safePassword = function(value) {
	return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/
			.test(value));
};

/* 密码由字母和数字组成，不超过32位 */
var supplierPassword = function(value) {
	return !(/(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*).{33})$|\s/
			.test(value));
};

/*
	^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$  
	^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}([0-9]|X))))$  18和15位身份证验证规则
*/
var idCard = function(value) {
	if (value.length == 18 && 18 != value.length)
		return false;
	var number = value.toLowerCase();
	var d, sum = 0, v = '10x98765432', w = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7,
			9, 10, 5, 8, 4, 2 ], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
	var re = number
			.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}([0-9]|x))))$/);
	if (re == null || a.indexOf(re[1]) < 0)
		return false;
	if (re[2].length == 9) {
		number = number.substr(0, 6) + '19' + number.substr(6);
		d = [ '19' + re[4], re[5], re[6] ].join('-');
	} else
		d = [ re[9], re[10], re[11] ].join('-');  
	if (!isDateTime.call(d, 'yyyy-MM-dd'))
		return false;
	for (var i = 0; i < 17; i++)
		sum += number.charAt(i) * w[i];
	return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
};

var isDateTime = function(format, reObj) {
	format = format || 'yyyy-MM-dd';
	var input = this, o = {}, d = new Date();
	var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format
			.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
	var len = f1.length, len1 = f3.length;
	if (len != f2.length || len1 != f4.length)
		return false;
	for (var i = 0; i < len1; i++)
		if (f3[i] != f4[i])
			return false;
	for (var i = 0; i < len; i++)
		o[f1[i]] = f2[i];
	o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
	o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
	o.dd = s(o.dd, o.d, d.getDate(), 31);
	o.hh = s(o.hh, o.h, d.getHours(), 24);
	o.mm = s(o.mm, o.m, d.getMinutes());
	o.ss = s(o.ss, o.s, d.getSeconds());
	o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
	if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0)
		return false;
	if (o.yyyy < 100)
		o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
	d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
	var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM
			&& d.getDate() == o.dd && d.getHours() == o.hh
			&& d.getMinutes() == o.mm && d.getSeconds() == o.ss
			&& d.getMilliseconds() == o.ms;
	return reVal && reObj ? d : reVal;
	function s(s1, s2, s3, s4, s5) {
		s4 = s4 || 60, s5 = s5 || 2;
		var reVal = s3;
		if (s1 != undefined && s1 != '' || !isNaN(s1))
			reVal = s1 * 1;
		if (s2 != undefined && s2 != '' && !isNaN(s2))
			reVal = s2 * 1;
		return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
	}
};