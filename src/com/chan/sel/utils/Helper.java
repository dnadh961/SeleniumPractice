package com.chan.sel.utils;

public class Helper {

	public static void myWait(long seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void printIds(String[] buttonIds) {
		for(String s: buttonIds)
		{
			System.out.println(s);
		}
	}
}
