package com.chan.sel.test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class HandlePopUp {

	private static Selenium sel;

	public static void main(String[] args) {
		sel = new DefaultSelenium("localhost", 4444, "*googlechrome",
				"http://www.google.co.in");
		sel.start();
		sel.windowMaximize();
		sel.setTimeout("60000");
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.click("link=Open a popup window");
		String mainWindowTitle = sel.getTitle();
		String[] titles = sel.getAllWindowTitles();
		for(String currentTitle : titles)
		{
			if(mainWindowTitle.equalsIgnoreCase(currentTitle))
			{
				continue;
			}
			else
			{
				sel.selectWindow(currentTitle);
				sel.close();
			}
		}
		sel.selectWindow(mainWindowTitle);
		sel.type("name=q", "webdriver");
	}
}
