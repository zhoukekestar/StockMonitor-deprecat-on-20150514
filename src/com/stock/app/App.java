package com.stock.app;

import com.stock.control.DataCtrl;
import com.stock.control.TimeCtrl;


public class App {
	
	public final static void main(String[] args) throws Exception {
		System.out.println("app begin");
		Thread.sleep(1000*10);
		for (int i = 0; i < 5; i++)
		{
			DataCtrl.insertCurData();
			Thread.sleep(1000*60*(i + 1) * 2);
		}
		TimeCtrl.run();
	}

}