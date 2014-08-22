package com.stock.util;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@SuppressWarnings("deprecation")
public class DateService {
	private static List<String> timeList;
	private static Map<String, Boolean> hasDone;
	private static DateService instance;
	private static int[][] timeListNum;
	private static Date oldDate;
	
	private  DateService() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		
		timeList = new ArrayList<String>();
		hasDone = new HashMap<String, Boolean>();
		timeListNum = new int[6][2];
		
		timeList.add("0940");
		timeList.add("1040");
		timeList.add("1140");
		timeList.add("1310");
		timeList.add("1410");
		timeList.add("1510");
		
		timeListNum[0][0] = 9;
		timeListNum[0][1] = 40;
		timeListNum[1][0] = 10;
		timeListNum[1][1] = 40;
		timeListNum[2][0] = 11;
		timeListNum[2][1] = 40;
		
		timeListNum[3][0] = 13;
		timeListNum[3][1] = 10;
		timeListNum[4][0] = 14;
		timeListNum[4][1] = 10;
		timeListNum[5][0] = 15;
		timeListNum[5][1] = 10;
		
		oldDate = getInternetDate();
	}
	
	public static DateService getInstance(){
		if (null == instance)
		{
			instance = new DateService();
		}
		return instance;
	}
	
	/**
	 * 获取网络上的时间
	 * */
	public static Date getInternetDate()
	{
		return getDateFrom("http://www.baidu.com");
	}

	public static Date getDateFrom(String urlstr) 
	{
		long ld = 0;
		try {
			URL url = new URL(urlstr);
			URLConnection uc = url.openConnection();
			uc.connect();
			ld = uc.getDate();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Get time error");
		}
		return new Date(ld);
	}

	public static void printDate(Date date) 
	{
		System.out.print((date.getMonth() + 1) + "M" + date.getDate() + "D");
		System.out.print(date.getHours() + "H" + date.getMinutes() + "M" + date.getSeconds() + "S\n");
	}

	/***
	 * 获取当前日期的星期
	 * 星期天为1，星期一为2，以此类推
	 * */
	public static int getWeekOfDate(Date dt) 
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/***
	 * 判断当前日期是否为工作日
	 * 
	 * */
	public static boolean isWorkDay(Date dt)
	{
		int day = getWeekOfDate(dt);
		if (day > 1 && day < 7)
		{
			return true;
		}
		return false;
	}
	
	/***
	 * 判断当前时间是否为当前的交易时间
	 * 1）是当前的交易时间，将会返回true
	 * 2）不是当前的交易时间，将会返回false
	 * 3）如果为新的一天，则将会将前一天的标记清空
	 * 
	 * */
	public static boolean isRunningTime(Date dt)
	{
		int hour = dt.getHours();
		int min = dt.getMinutes();
		for (int i = 0; i < 6; i++)
		{
			if (	hour == timeListNum[i][0]
				&& 	min == timeListNum[i][1] 
				&& 	!hasDone.containsKey(timeList.get(i))
				)
			{
				hasDone.put(timeList.get(i), true);
				return true;
			}
		}
		if (dt != oldDate)
		{
			hasDone.clear();
			oldDate = dt;
		}
		return false;
	}
}
