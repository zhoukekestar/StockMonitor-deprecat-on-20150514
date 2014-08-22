package com.stock.control;

import java.net.UnknownHostException;
import java.util.Date;

import com.stock.util.DateService;

public class TimeCtrl {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		System.out.println("begin");
		DateService ds = DateService.getInstance();
		Date date;
		System.out.println("for");
		for (int i = 1; i < 30; i++)//day
			for (int j = 1; j < 24; j++)//hour
				for (int k = 0; k < 60; k++)
				{
					date = new Date(2014-1900, 8, i);
					date.setHours(j);
					date.setMinutes(k);
					
				
					if (ds.isWorkDay(date) && ds.isRunningTime(date))
					{
						System.out.println(" pass " + date.toString());
					}
				}
		System.out.println("ok");
		Thread.sleep(3000);
		System.out.println("over");
	}
	
	public static void run() throws InterruptedException, UnknownHostException
	{
		DateService ds = DateService.getInstance();
		
		while (true)
		{
			Date date = ds.getInternetDate();
			if (ds.isWorkDay(date))
			{
				if (ds.isRunningTime(date))
				{
					DataCtrl.insertCurData();
				}
				else
				{
					Thread.sleep(1000*20);
				}
			}
			else
			{
				Thread.sleep(1000*60*20);
			}
		}
	}
}
