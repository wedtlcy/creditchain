/*
 * Copyright (C) 2015 ShenZhen tianlang Co.,Ltd All Rights Reserved.
 * δ������˾��ʽ����ͬ�⣬�����κθ��ˡ����岻��ʹ�á����ơ��޸Ļ򷢲������.
 * ��Ȩ�����������Ƿ������޹�˾ www.tianlang.com.
 */
package com.creditlink.center.provider.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @version 2016年6月8日上午11:06:02
 * @author wuliu
 */
public class DateUtil {

	/**
	 * 获取当天开始时间,如:2016-06-08 00:00:00
	 * 
	 * @version 2016年6月8日上午11:06:20
	 * @author wuliu
	 * @return
	 */
	public static String getTodayStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(todayStart.getTime());
		return dateStr;
	}

	/**
	 * 
	 * 获取当天开始时间,如:2016-06-08 23:59:59
	 * 
	 * @version 2016年6月8日上午11:08:01
	 * @author wuliu
	 * @return
	 */
	public static String getTodayEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(todayEnd.getTime());
		return dateStr;
	}

	/**
	 * 指定日期添加指定分钟后的时间
	 * 
	 * @version 2016年6月8日上午11:17:26
	 * @author wuliu
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date addMin(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, i);
		return cal.getTime();
	}

	/**
	 * 指定日期添加指定天数后的日期
	 * 
	 * @version 2016年6月8日上午11:11:05
	 * @author wuliu
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date addDay(Date date, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, i);
		return calendar.getTime();
	}

	/**
	 * 指定日期添加指定月数后的日期
	 * 
	 * @version 2016年6月8日上午11:15:37
	 * @author wuliu
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date addMonth(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, i);
		return cal.getTime();
	}

	/**
	 * 获取当月第一天的日期 2016-06-01 00:00:00
	 * 
	 * @version 2016年6月8日上午11:20:43
	 * @author wuliu
	 * @return
	 */
	public static Date getThisMonthStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当月最后一天的日期 2016-06-01 00:00:00
	 * 
	 * @version 2016年6月8日上午11:20:43
	 * @author wuliu
	 * @return
	 */
	public static Date getThisMonthEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取前月第一天的日期 2016-06-01 00:00:00
	 * 
	 * @version 2016年6月8日上午11:20:43
	 * @author wuliu
	 * @return
	 */
	public static Date getBeforeMonthStartDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取前月最后一天的日期 2016-06-01 00:00:00
	 * 
	 * @version 2016年6月8日上午11:20:43
	 * @author wuliu
	 * @return
	 */
	public static Date getBeforeMonthEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 得到指定日期的前几天
	 * 
	 * @version 2016年6月8日上午11:33:23
	 * @author wuliu
	 * @param dt
	 * @param days
	 * @return
	 */
	public static Date getDateBefore(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1 * days);
		return calendar.getTime();
	}

	/**
	 * 得到指定日期的后几天
	 * 
	 * @version 2016年6月8日上午11:33:23
	 * @author wuliu
	 * @param dt
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * 得到一个日期的年份
	 * 
	 * @version 2016年6月8日上午11:36:02
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到一个日期的月份
	 * 
	 * @version 2016年6月8日上午11:37:32
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到一个日期的Day
	 * 
	 * @version 2016年6月8日上午11:38:23
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到一个日期的小时
	 * 
	 * @version 2016年6月8日上午11:39:24
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到一个日期的分钟
	 * 
	 * @version 2016年6月8日上午11:39:33
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static int getMinite(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 得到一个日期的秒
	 * 
	 * @version 2016年6月8日上午11:39:41
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 指定日期是该年的第几周
	 * 
	 * @version 2016年6月8日上午11:41:39
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取指定日期指定字符串的格式字符串
	 * 
	 * @version 2016年6月8日上午11:43:37
	 * @author wuliu
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取指定字符串日期的时间
	 * 
	 * @version 2016年6月8日上午11:50:18
	 * @author wuliu
	 * @param dateStr
	 * @param formatStr
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dateStr, String formatStr) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
		try {
			return simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
		}
		return new Date();
	}

	/**
	 * 计算两个日期之间相差的天数 smdate:2012-09-08 10:10:10 bdate:2012-09-15 00:00:00
	 * 
	 * @version 2016年6月8日上午11:55:17
	 * @author wuliu
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween2Days(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long smdate_time = cal.getTimeInMillis();
		cal.setTime(bdate);
		long bdate_time = cal.getTimeInMillis();
		long between_days = (bdate_time - smdate_time) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 比较相处的小时数
	 * 
	 * @version 2016年6月8日下午12:32:08
	 * @author wuliu
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static double daysBetween2Hours(Date start, Date end)
			throws ParseException {
		double diff = (end.getTime() - start.getTime()) / 1000 / 60 / 60;
		return diff;
	}

	/**
	 * 比较时间大小1：大于，0：等于，-1:小于
	 * 
	 * @version 2016年6月8日下午12:28:31
	 * @author wuliu
	 * @param data1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date data1, Date date2) {
		if (data1.getTime() > date2.getTime()) {
			return 1;
		} else if (data1.getTime() < date2.getTime()) {
			return -1;
		} else {// 相等
			return 0;
		}
	}

	/**
	 * 判断两个时间是否是同一天
	 * 
	 * @version 2016年6月8日下午12:36:17
	 * @author wuliu
	 * @param date
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean isSameDay(Date date, Date date2)
			throws ParseException {
		if (date == null || date2 == null)
			return false;
		return daysBetween2Days(date, date2) == 0 ? true : false;
	}

	/**
	 * 判断参数日期是否为当天
	 * 
	 * @version 2016年6月8日下午12:38:35
	 * @author wuliu
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static boolean isToday(Date date) throws ParseException {
		if (date == null)
			return false;
		return daysBetween2Days(date, new Date()) == 0 ? true : false;
	}

	/**
	 * 获取指定日期是星期几
	 * 
	 * @version 2016年6月8日下午12:40:08
	 * @author wuliu
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekOfDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekOfDays[w];
	}

	/**
	 * 将秒数转换成时分秒
	 * 
	 * @version 2016年6月8日下午12:42:00
	 * @author wuliu
	 * @param second
	 * @return
	 */
	public static String secondToTimeString(String second) {
		try {
			int s = (int) Double.parseDouble(second);
			int shi = s / 3600;
			s = s % 3600;
			int fen = s / 60;
			s = s % 60;
			return (shi > 9 ? shi : ("0" + shi)) + ":"
					+ (fen > 9 ? fen : ("0" + fen)) + ":"
					+ (s > 9 ? s : ("0" + s));
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取本周一的日期
	 * 
	 * @version 2016年6月8日下午12:45:46
	 * @author wuliu
	 * @return
	 */
	public static Date getWeekOfMonday() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
			cal.add(Calendar.WEEK_OF_YEAR, -1);
			return cal.getTime();
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		return cal.getTime();
	}

	/**
	 * 获取本周日的日期
	 * 增加一个星期，才是我们中国人理解的本周日的日期
	 * @version 2016年6月8日下午12:46:24
	 * @author wuliu
	 * @return
	 */
	public static Date getWeekOfSunday() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return cal.getTime();
		}
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return cal.getTime();
	}
	
	/**
	 * 为指定日期添加指定时间量
	 * 分钟:Calendar.MINUTE
	 * 天:Calendar.DATE
	 * 月:Calendar.MONTH
	 * @version 2016年6月8日下午12:47:50
	 * @author wuliu
	 * @param date
	 * @param type
	 * @param value
	 * @return
	 */
	public static Date add(Date date,int type,int value){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, value);
		return calendar.getTime();
	}
	
	public static void main(String[] args) throws ParseException {
	    SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    int i = daysBetween2Days(sdf.parse("2016-08-12 00:00:00"),sdf.parse("2016-09-10 00:00:00"));
	    System.out.println(i);
    }
}
