package com.chan.sel.utils;

import com.thoughtworks.selenium.Selenium;

public class DimensionsUtil {

	public static int getOffsetLeft(Selenium sel, String pointer)
	{
		String script_calOffsetLeft = "var ele="+pointer+";var left=ele.offsetLeft; var parent = ele.offsetParent;" +
				"while(parent!=null){left=left+parent.offsetLeft;parent=parent.offsetParent}; left;";
		String leftStr = sel.getEval(script_calOffsetLeft);
		return Integer.parseInt(leftStr);
	}
	
	public static int getOffsetTop(Selenium sel, String pointer)
	{
		String script_calOffsetTop = "var ele="+pointer+";var top=ele.offsetTop; var parent = ele.offsetParent;" +
				"while(parent!=null){top=top+parent.offsetTop;parent=parent.offsetParent}; top;";
		String topStr =  sel.getEval(script_calOffsetTop);
		return Integer.parseInt(topStr);
	}
	
	public static int getOffsetWidth(Selenium sel, String pointer)
	{
		String script_calOffsetWidth = "var ele="+pointer+";ele.offsetWidth";
		String widthStr =  sel.getEval(script_calOffsetWidth);
		return Integer.parseInt(widthStr);
	}
	
	public static int getOffsetHeight(Selenium sel, String pointer)
	{
		String script_calOffsetHeight = "var ele="+pointer+";ele.offsetHeight";
		String heightStr =  sel.getEval(script_calOffsetHeight);
		return Integer.parseInt(heightStr);
	}
}
