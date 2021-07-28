package com.chan.sel.test.drag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chan.sel.impl.HtmlElement;
import com.chan.sel.impl.Mouse;
import com.chan.sel.internal.IHtmlElement;
import com.chan.sel.utils.DimensionsUtil;
import com.chan.sel.utils.Helper;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * 
 * @author Chanu Vempati
 *
 */
public class DragNDropThruRC {

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
	public void dragnDrop()
	{
		sel.open("http://www.w3schools.com/html/html5_draganddrop.asp");
		sel.waitForCondition(myPageDoc+".getElementById('drag1')", "60000");
		final IHtmlElement drag1Img = new HtmlElement("drag1");
		int left = DimensionsUtil.getOffsetLeft(sel, drag1Img.getDomPointer());
		int top = DimensionsUtil.getOffsetTop(sel, drag1Img.getDomPointer());
		int width = DimensionsUtil.getOffsetWidth(sel, drag1Img.getDomPointer());
		int height = DimensionsUtil.getOffsetHeight(sel, drag1Img.getDomPointer());
		robot.mouseMove(left+width/3, top+height/2+tabHeaderHeight);
		robot.pressLeftButton();
		robot.mouseMove(left+width/3+10, top+height/2+tabHeaderHeight-10);
		final IHtmlElement div2 = new HtmlElement("div2");
		int leftDiv2 = DimensionsUtil.getOffsetLeft(sel, div2.getDomPointer());
		int widthDiv2 = DimensionsUtil.getOffsetWidth(sel, div2.getDomPointer());
		robot.mouseMove(leftDiv2+widthDiv2/3, top+height/3+tabHeaderHeight);
		Helper.myWait(1);
		robot.releaseLeftButton();
		System.out.println();
	}
	
	@After
	public void tearDown()
	{
		sel.close();
		sel.stop();
	}
}
