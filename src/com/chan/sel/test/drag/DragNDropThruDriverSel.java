package com.chan.sel.test.drag;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.chan.sel.impl.HtmlElement;
import com.chan.sel.impl.Mouse;
import com.chan.sel.internal.IHtmlElement;
import com.chan.sel.utils.DimensionsUtil;
import com.chan.sel.utils.Helper;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

/**
 * 
 * @author Chanu Vempati
 *
 */
public class DragNDropThruDriverSel {

	private Selenium sel;
	private WebDriver driver;
	private Mouse robot;
	private static final String myPageDoc = "selenium.browserbot.getCurrentWindow().document";
	private static final int tabHeaderHeight = 54;
	
	@Before
	public void setUp()
	{
		 initializeDriver("chrome");
		 sel = new WebDriverBackedSelenium(driver, "http://www.google.co.in/");
		 driver.manage().window().maximize();
		 robot = new Mouse(sel);
	}
	
	private void initializeDriver(String browser) {
		if("chrome".equalsIgnoreCase(browser))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		}
		else
		{
			driver = new FirefoxDriver();
		}
	}
	
	@Test
	public void dragnDrop()
	{
		sel.open("http://www.w3schools.com/html/html5_draganddrop.asp");
		sel.waitForCondition(myPageDoc+".getElementById('drag1')", "60000");
		final IHtmlElement drag1Img = new HtmlElement("drag1");
		final WebElement drag1Ele = driver.findElement(By.id("drag1"));
		int left = drag1Ele.getLocation().getX();
		int top = DimensionsUtil.getOffsetTop(sel, drag1Img.getDomPointer());
		int width = drag1Ele.getSize().getWidth();
		int height = DimensionsUtil.getOffsetHeight(sel, drag1Img.getDomPointer());
		robot.mouseMove(left+width/3, top+height/2+tabHeaderHeight);
		robot.pressLeftButton();
		robot.mouseMove(left+width/3+15, top+height/2+tabHeaderHeight-15);
		Helper.myWait(1);
		robot.mouseMove(left+width/3+30, top+height/2+tabHeaderHeight-30);
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
	}
}
