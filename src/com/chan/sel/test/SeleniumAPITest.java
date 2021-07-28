package com.chan.sel.test;

import java.awt.event.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

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
public class SeleniumAPITest {
	
	private Selenium sel;
	private Mouse robot;
	private static final String myPageDoc = "selenium.browserbot.getCurrentWindow().document";
	private static final int tabHeaderHeight = 54;

	@Before
	public void setUP()
	{
		sel = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://www.google.co.in");
		 sel.start();
		 sel.windowMaximize();
		 sel.setTimeout("60000");
		 robot = new Mouse();
	}
	
	@Test
	public void answerOnNextPrompt()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.answerOnNextPrompt("vijaya");
		sel.click("name=promptbox");
		if(sel.isAlertPresent())
		{
			System.out.println(sel.getAlert());
		}
	}
	
	@Test
	public void cssLocators()
	{
		sel.open("https://www.facebook.com/");
		sel.waitForPageToLoad("180000");
		sel.type("css=input[type='text'][value='First Name']", "swathi");
		System.out.println();
	}
	
	@Test
	public void click()
	{
		sel.open("http://www.w3schools.com/html/default.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		sel.click("xpath=//a[@class='tryitbtn']");
		System.out.println();
	}
	
	@Test
	public void clickAt()
	{
		sel.open("http://www.w3schools.com/html/default.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		sel.clickAt("xpath=//a[@class='tryitbtn']", "10,20");
	}
	
	@Test
	public void check()
	{
		sel.open("http://www.utexas.edu/learn/forms/checkboxes.html");
		sel.waitForPageToLoad("60000");
		sel.check("id=programming");
	}
	
	@Test
	public void uncheck()
	{
		sel.open("http://www.echoecho.com/htmlforms09.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName(\"a\")[0]", "60000");
		sel.uncheck("name=option2");
		System.out.println();
	}
	
	@Test
	public void chooseOkForAlert()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		//sel.chooseOkOnNextConfirmation();
		sel.click("id=alertbox");
		if(sel.isAlertPresent())
		{
			String alertMessgae = sel.getAlert();
			System.out.println(alertMessgae);
			sel.chooseOkOnNextConfirmation();
			if(!sel.isAlertPresent())
			{
				System.out.println("Alert is closed...");
			}
		}
	}
	
	@Test
	public void chooseOkonNextConfirmation()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.chooseOkOnNextConfirmation();
		sel.click("//input[@value='confirm']");
		System.out.println(sel.getAlert());
	}
	
	@Test
	public void chooseCancelonNextConfirmation()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.chooseCancelOnNextConfirmation();
		sel.click("//input[@value='confirm']");
		System.out.println(sel.getAlert());
	}
	
	@Test
	public void captureScreenShot()
	{ 
		sel.open("http://encodable.com/uploaddemo/");
		sel.waitForCondition(myPageDoc+".getElementById('newsubdir1')", "60000");
		sel.refresh();
		sel.captureScreenshot("D:/error.png");
	}
	
	@Test
	public void contextMenu()
	{
		sel.open("http://medialize.github.io/jQuery-contextMenu/demo.html");
		sel.waitForCondition(myPageDoc+".getElementById('demo')", "60000");
		sel.contextMenu("xpath=.//div[@class='context-menu-one box menu-1']");
		System.out.println();
	}
	
	@Test
	public void doubleClick()
	{
		sel.open("http://unixpapa.com/js/testmouse.html");
		sel.waitForPageToLoad("60000");
		sel.doubleClick("link=click here to test");
		System.out.println();
	}
	
	@Test
	public void focus()
	{
		sel.open("http://www.w3schools.com/");
		sel.waitForCondition(myPageDoc+".getElementById('gs_st50')", "60000");
		sel.focus("name=search");
	}
	
	@Test
	public void elmtCord()
	{
		sel.open("http://encodable.com/uploaddemo/");
		sel.waitForCondition(myPageDoc+".getElementById('newsubdir1')", "60000");
		final String pointer = "id=uploadname1";
		int left = (Integer) sel.getElementPositionLeft(pointer);
		int top = (Integer) sel.getElementPositionTop(pointer);
		int width = (Integer) sel.getElementWidth(pointer);
		int height = (Integer) sel.getElementHeight(pointer);
		robot.mouseMove(left+width/2, top+height/2);
		robot.click();
		System.out.println();
	}
	
	@Test
	public void fireEvent()
	{
		sel.open("http://www.w3schools.com/html/default.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		sel.fireEvent("xpath=//a[@class='tryitbtn']", "click");
		System.out.println();
	}
	
	/**
	 * Best example to understand the behavior of iframes. Its not possible to access elements of iframe from main document.
	 * Its proved in this test case.
	 */
	@Test
	public void getAlert()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.click("id=alertbox");
		System.out.println(sel.getAlert());
	}
	
	@Test
	public void getPrompt()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.click("name=promptbox");
		System.out.println(sel.getPrompt());
	}
	
	@Test
	public void getAllButtons()
	{
		sel.open("http://www.w3schools.com/js/tryit.asp?filename=tryjs_alert");
		sel.waitForCondition(myPageDoc+".getElementById('iframeResult')", "60000");
		String[] buttonIds = sel.getAllButtons();
		Helper.printIds(buttonIds);
	}
	
	@Test
	public void getAllFields()
	{
		sel.open("http://www.456bereastreet.com/lab/whitespace-around-text-fields/");
		sel.waitForCondition(myPageDoc+".getElementById('text-2')", "60000");
		String[] fieldIds = sel.getAllFields();
		Helper.printIds(fieldIds);
	}
	
	@Test
	public void getBodyText()
	{
		sel.open("http://www.w3schools.com/js/js_popup.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		System.out.println(sel.getBodyText());
		Assert.assertTrue(sel.getBodyText().contains("JS RegExp"));
	}
	
	@Test
	public void getConfirmation()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.click("//input[@value='confirm']");
		System.out.println(sel.getConfirmation());
	}
	
	@Test
	public void getCookie()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		sel.type("id=username", "9052557770");
		sel.type("id=password", "chanu");
		sel.click("name=Login");
		sel.waitForCondition(myPageDoc+".getElementById('footer')", "60000");
		System.out.println("Cookie: "+sel.getCookie());
	}
	
	@Test
	public void getSelectedIndex()
	{
		sel.open("http://www.w3schools.com/tags/tryhtml_select.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName('select')[0]", "60000");
		sel.getEval(myPageDoc+".getElementsByTagName('select')[0].selectedIndex=2");
		String selectedI = sel.getSelectedIndex("xpath=.//select");
		System.out.println(selectedI);
	}
	
	@Test
	public void getSelectedIndexes()
	{
		sel.open("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName('select')[0]", "60000");
		String[] selectedI = sel.getSelectedIndexes("xpath=.//select");
		Helper.printIds(selectedI);
	}
	
	@Test
	public void getSelectedLabel()
	{
		sel.open("http://www.w3schools.com/tags/tryhtml_select.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName('select')[0]", "60000");
		sel.getEval(myPageDoc+".getElementsByTagName('select')[0].selectedIndex=2");
		String selectedL = sel.getSelectedLabel("xpath=.//select");
		System.out.println(selectedL);
	}
	
	@Test
	public void getSelectedValue()
	{
		sel.open("http://www.w3schools.com/tags/tryhtml_select.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName('select')[0]", "60000");
		sel.getEval(myPageDoc+".getElementsByTagName('select')[0].selectedIndex=1");
		String selectedV = sel.getSelectedValue("xpath=.//select");
		System.out.println(selectedV);
	}
	
	@Test
	public void getTable()
	{
		sel.open("http://www.w3schools.com/html/html_tables.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		String text = sel.getTable("//table[@class='reference'].2.1");
		System.out.println(text);
	}
	
	@Test
	public void getText()
	{
		sel.open("http://www.w3schools.com/html/html_tables.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		String text = sel.getText("xpath=//div[@class='prev']");
		System.out.println(text);
	}
	
	@Test
	public void getTitle()
	{
		sel.open("http://www.w3schools.com/html/html_tables.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		String title = sel.getTitle();
		System.out.println(title);
	}
	
	@Test
	public void getValue()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		sel.type("id=username", "9052557770");
		String value = sel.getValue("id=username");
		System.out.println(value);
	}
	
	@Test
	public void getEval()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		sel.getEval("selenium.browserbot.getCurrentWindow().document.getElementById('username').value='9052557770';");
		System.out.println();
	}
	
	@Test
	public void goBack()
	{
		sel.open("http://www.w3schools.com/html/html_tables.asp");
		sel.waitForCondition(myPageDoc+".getElementById('main')", "60000");
		sel.click("xpath=.//a[@class='chapter']");
		sel.goBack();
	}
	
	/**
	 * Explain prompt and confirm too
	 */
	@Test
	public void isAlertPresent()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.click("id=alertbox");
		System.out.println(sel.isAlertPresent());
	}
	
	@Test
	public void iSConfirmationPresent()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.click("//input[@value='confirm']");
		System.out.println(sel.isConfirmationPresent());
	}
	
	@Test
	public void isPromptPresent()
	{
		sel.open("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		sel.waitForPageToLoad("60000");
		sel.click("name=promptbox");
		System.out.println(sel.isPromptPresent());
	}
	
	@Test
	public void isChecked()
	{
		sel.open("http://www.echoecho.com/htmlforms09.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName(\"a\")[0]", "60000");
		System.out.println(sel.isChecked("name=option1"));
		System.out.println(sel.isChecked("name=option2"));
	}
	
	@Test
	public void isEditable()
	{
		sel.open("http://book.theautomatedtester.co.uk/");
		sel.waitForPageToLoad("180000");
		System.out.println(sel.isEditable("id=q"));
		System.out.println(sel.isEditable("id=q"));
	}
	
	@Test
	public void isElementPresent()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		System.out.println(sel.isElementPresent("id=username"));
		System.out.println(sel.isElementPresent("id=pass"));
	}
	
	@Test
	public void isTextPresent()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		System.out.println(sel.isTextPresent("New user?"));
	}
	
	@Test
	public void isVisible()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		System.out.println(sel.isVisible("id=username"));
		System.out.println(sel.isVisible("id=gval"));
	}
	
	@Test
	public void refresh()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		sel.refresh();
	}
	
	@Test
	public void removeAllSelections()
	{
		sel.open("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName('select')[0]", "60000");
		sel.removeAllSelections("xpath=.//select");
	}
	
	@Test
	public void removeSelection()
	{
		sel.open("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName('select')[0]", "60000");
		sel.getEval(myPageDoc+".getElementsByTagName('select')[0].selectedIndex=1");
		sel.removeSelection("xpath=.//select", "Saab");
	}
	
	@Test
	public void select()
	{
		sel.open("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		sel.waitForCondition(myPageDoc+".getElementsByTagName('select')[0]", "60000");
		sel.select("xpath=.//select", "Saab");
		System.out.println();
	}
	
	@Test
	public void type()
	{
		sel.open("http://site3.way2sms.com/content/index.html");
		sel.waitForCondition(myPageDoc+".getElementById('username')", "60000");
		sel.type("id=username", "9052557770");
		System.out.println();
	}
	
	@Test
	public void ctrlKeyDownTest()
	{
		sel.open("http://www.w3schools.com/html/tryit.asp?filename=tryhtml_intro");
		sel.waitForCondition(myPageDoc+".getElementById('submitBTN')", "60000");
		final IHtmlElement txtAreaElmt = new HtmlElement("textareaCode");
		int left = DimensionsUtil.getOffsetLeft(sel, txtAreaElmt.getDomPointer());
		int top = DimensionsUtil.getOffsetTop(sel, txtAreaElmt.getDomPointer());
		int width = DimensionsUtil.getOffsetWidth(sel, txtAreaElmt.getDomPointer());
		int height = DimensionsUtil.getOffsetHeight(sel, txtAreaElmt.getDomPointer());
		robot.mouseMove(left+width/3, top+height/2+tabHeaderHeight);
		robot.click();
		//sel.controlKeyDown();
		sel.keyDownNative(String.valueOf(KeyEvent.VK_CONTROL));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_A));
		sel.controlKeyUp();
		sel.keyUpNative(String.valueOf(KeyEvent.VK_CONTROL));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_M));
		System.out.println();
	}
	
	@Test
	public void shiftKeyDownTest()
	{
		sel.open("http://www.w3schools.com/html/tryit.asp?filename=tryhtml_intro");
		sel.waitForCondition(myPageDoc+".getElementById('submitBTN')", "60000");
		final IHtmlElement txtAreaElmt = new HtmlElement("textareaCode");
		int left = DimensionsUtil.getOffsetLeft(sel, txtAreaElmt.getDomPointer());
		int top = DimensionsUtil.getOffsetTop(sel, txtAreaElmt.getDomPointer());
		int width = DimensionsUtil.getOffsetWidth(sel, txtAreaElmt.getDomPointer());
		int height = DimensionsUtil.getOffsetHeight(sel, txtAreaElmt.getDomPointer());
		robot.mouseMove(left+width/3, top+height/2+tabHeaderHeight);
		robot.click();
		//sel.shiftKeyDown();
		sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_A));
		//sel.shiftKeyUp();
		sel.keyUpNative(String.valueOf(KeyEvent.VK_SHIFT));
		System.out.println();
	}
	
	@After
	public void tearDown()
	{
		if(!sel.isAlertPresent())
		{
			sel.close();
		}
		sel.stop();
	}
}
