package com.chan.sel.test;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chan.sel.utils.DriverUtil;

public class WebElementAPITest {

	private WebDriver driver;

	@Before
	public void setUp()
	{
		initializeDriver("chrome");
		driver.manage().window().maximize();
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
	public void click()
	{
		driver.get("http://www.w3schools.com/html/default.asp");
		DriverUtil.waitForElementPresence(driver, By.id("main"));
		WebElement currentWebElmt = driver.findElement(By.xpath("//a[@class='tryitbtn']"));
		currentWebElmt.click();
	}
	
	@Test
	public void clear()
	{
		driver.get("http://www.456bereastreet.com/lab/whitespace-around-text-fields/");
		waitForPageLoaded(driver);
		WebElement textElmt = driver.findElement(By.id("text-1"));
		textElmt.clear();
	}
	
	@Test
	public void sendKeys()
	{
		driver.get("http://www.456bereastreet.com/lab/whitespace-around-text-fields/");
		waitForPageLoaded(driver);
		WebElement textElmt = driver.findElement(By.id("text-1"));
		textElmt.sendKeys("Hello World");
		System.out.println();
	}
	
	@Test
	public void findElement()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement divCenter = driver.findElement(By.id("divinthecenter"));
		boolean isE = divCenter.findElement(By.id("verifybutton")).isEnabled();
		System.out.println(isE);
	}
	
	@Test
	public void findElements()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		List<WebElement> divCenter = driver.findElements(By.id("divinthecenter"));
		Iterator<WebElement> divCenterI = divCenter.iterator();
		while(divCenterI.hasNext())
		{
			WebElement currElmt = divCenterI.next();
			boolean isE = currElmt.findElement(By.id("verifybutton")).isEnabled();
			System.out.println(isE);
		}
	}
	
	@Test
	public void isEnabled()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement ajaxBtn = driver.findElement(By.id("secondajaxbutton"));
		boolean isEnabled = ajaxBtn.isEnabled();
		System.out.println(isEnabled);
	}
	
	@Test
	public void isDisplayed()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver); 
		WebElement ajaxBtn = driver.findElement(By.id("secondajaxbutton"));
		boolean isDisplayed = ajaxBtn.isDisplayed();
		System.out.println(isDisplayed);
	}
	
	@Test
	public void getAttribute()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement divLeft = driver.findElement(By.id("divontheleft"));
		String attrVal = divLeft.getAttribute("class");
		System.out.println(attrVal);
	}
	
	@Test
	public void getCssValue()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement divLeft = driver.findElement(By.id("divontheleft"));
		String width = divLeft.getCssValue("position");
		System.out.println(width);
	}
	
	@Test
	public void getLocation()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement divLeft = driver.findElement(By.id("divontheleft"));
		Point loc = divLeft.getLocation();
		System.out.println(loc.x);
		System.out.println(loc.y);
	}
	
	@Test
	public void getSize()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement divLeft = driver.findElement(By.id("divontheleft"));
		Dimension dimension = divLeft.getSize();
		System.out.println(dimension.height);
		System.out.println(dimension.width);
	}
	
	@Test
	public void getText()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement divLeft = driver.findElement(By.id("divontheleft"));
		System.out.println(divLeft.getText());
	}
	
	@Test
	public void getTagName()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		WebElement divLeft = driver.findElement(By.id("divontheleft"));
		System.out.println(divLeft.getTagName());
	}
	
	@After
	public void close()
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
