package com.chan.sel.test.upload;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import com.chan.sel.impl.HtmlElement;
import com.chan.sel.impl.Mouse;
import com.chan.sel.impl.MyKeyboard;
import com.chan.sel.internal.IHtmlElement;
import com.chan.sel.internal.IKeyboard;
import com.chan.sel.utils.DimensionsUtil;
import com.chan.sel.utils.Helper;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * 
 * @author Chanu Vempati
 *
 */
public class UploadThruRC {

	private Selenium sel;
	private Mouse robot;
	private static final String myPageDoc = "selenium.browserbot.getCurrentWindow().document";
	private static final int tabHeaderHeight = 54;
	
	@Before
	public void setUp()
	{
		 sel = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://www.google.co.in");
		 sel.start();
		 sel.windowMaximize();
		 sel.setTimeout("60000");
		 robot = new Mouse();
	}
	
	@Test
	public void uploadFile()
	{
		sel.open("http://encodable.com/uploaddemo/");
		sel.waitForCondition(myPageDoc+".getElementById('newsubdir1')", "60000");
		final IHtmlElement uploadElmt = new HtmlElement("uploadname1");
		int left = DimensionsUtil.getOffsetLeft(sel, uploadElmt.getDomPointer());
		int top = DimensionsUtil.getOffsetTop(sel, uploadElmt.getDomPointer());
		int width = DimensionsUtil.getOffsetWidth(sel, uploadElmt.getDomPointer());
		int height = DimensionsUtil.getOffsetHeight(sel, uploadElmt.getDomPointer());
		robot.mouseMove(left+width/3, top+height/2+tabHeaderHeight);
		robot.click();
		Helper.myWait(5);
		final IKeyboard keyboard = new MyKeyboard(sel);
		keyboard.typeString(new File(".classpath").getAbsolutePath());
		Helper.myWait(2);
		keyboard.keyPressAndRelease(IKeyboard.KEY_ENTER);
		Helper.myWait(2);
		sel.getEval(myPageDoc+".getElementsByName(\"subdir1\")[0].selectedIndex=5");
		sel.type("id=newsubdir1", "chanus");
		sel.click("id=uploadbutton");
		sel.waitForCondition(myPageDoc+".getElementById('fcfiledone1')", "60000");
		String status = sel.getEval(myPageDoc+".getElementById('fcfiledone1').nextElementSibling.innerHTML");
		Assert.assertTrue(status.contains("uploaded successfully"));
	}
	
	@After
	public void tearDown()
	{
		sel.close();
		sel.stop();
	}
}
