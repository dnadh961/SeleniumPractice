package com.chan.sel.test;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InputDevicesTest {

	private WebDriver driver;

	@Before
	public void setUp()
	{
		initializeDriver("chrome");
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
	public void executeJavaScript()
	{
		driver.get("http://www.softwaretuitions.com/");
		waitForPageLoaded(driver);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("alert('hi')");
		System.out.println();
	}
	
	@Test
	public void mouseHover()
	{
		driver.get("http://www.softwaretuitions.com/selenium/category/input-devices");
		waitForPageLoaded(driver);
		WebElement author = driver.findElement(By.linkText("Chanukya Vempati"));
		Actions actions = new Actions(driver);
		actions.moveToElement(author).build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}
	
	@Test
	public void rightClick()
	{
		System.out.println();
		driver.get("http://medialize.github.io/jQuery-contextMenu/demo.html");
		waitForPageLoaded(driver);
		WebElement elmt = driver.findElement(By.xpath(".//div[@class='context-menu-one box menu-1']"));
		Actions actions = new Actions(driver);
		actions.contextClick(elmt).build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
