package com.chan.sel.test;

import com.thoughtworks.selenium.DefaultSelenium;

public class TryBookAutomatedTester {

	public static void main(String[] args) {
		DefaultSelenium sel = new DefaultSelenium("localhost", 4444, "*googlechrome",
				"http://www.google.co.in");
		sel.start();
		sel.windowMaximize();
		sel.setTimeout("60000");
		sel.open("http://book.theautomatedtester.co.uk/");
		sel.waitForPageToLoad("60000");
		sel.click("link=Chapter8");
		sel.waitForPageToLoad("60000");
		System.out.println("");
	}
}
