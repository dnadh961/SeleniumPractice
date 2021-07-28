package com.chan.sel.test.drag;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chan.sel.impl.Mouse;
import com.chan.sel.utils.Helper;

/**
 * 
 * @author Devendra Vempati
 *
 */
public class DragNDropThruDriver {
	
	private WebDriver driver;
	private Mouse robot;
	private static final int tabHeaderHeight = 54;

	@Before
	public void setUp()
	{
		initializeDriver("chrome");
		driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
		waitForPageLoaded(driver);
		driver.manage().window().maximize();
		robot = new Mouse();
	}
	
	private void initializeDriver(String browser) {
		if("chrome".equalsIgnoreCase(browser))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		}
		else if("ie".equalsIgnoreCase(browser))
		{
			System.setProperty("webdriver.ie.driver", "chromedriver.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{
			driver = new FirefoxDriver();
		}
	}
	
	@Test
	public void dragNDrop()
	{
		final WebElement drag1Ele = driver.findElement(By.id("drag1"));
		int height = drag1Ele.getSize().getHeight();
		int width = drag1Ele.getSize().getWidth();
		int left = drag1Ele.getLocation().getX();
		int top = drag1Ele.getLocation().getY();
		robot.mouseMove(left+width/3, top+height/2+tabHeaderHeight);
		robot.pressLeftButton();
		robot.mouseMove(left+width/3+15, top+height/2+tabHeaderHeight-15);
		Helper.myWait(1);
		robot.mouseMove(left+width/3+30, top+height/2+tabHeaderHeight-30);
		final WebElement div2Elmt = driver.findElement(By.id("div2"));
		int leftDiv2 = div2Elmt.getLocation().getX();
		int widthDiv2 = div2Elmt.getSize().getWidth();
		robot.mouseMove(leftDiv2+widthDiv2/3, top+height/3+tabHeaderHeight);
		Helper.myWait(1);
		robot.releaseLeftButton();
		System.out.println();
	}
	
	@After
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
	
	private void waitForPageLoaded(WebDriver driver) {
	     ExpectedCondition<Boolean> expectation = new
	     ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	      };

	     Wait<WebDriver> wait = new WebDriverWait(driver,30);
	      try {
	              wait.until(expectation);
	      } catch(Throwable error) {
	              Assert.assertFalse("Timeout waiting for Page Load Request to complete.",true);
	      }
	 } 
}
