package com.chan.sel.test.upload;

import java.io.File;

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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Chanu Vempati
 *
 */
public class UploadThruDriver {

	private WebDriver driver;

	@Before
	public void setUp()
	{
		initializeDriver("chrome");
		driver.get("http://encodable.com/uploaddemo/");
		waitForPageLoaded(driver);
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
	public void dragNDrop()
	{
		final WebElement uploadElmt = driver.findElement(By.id("uploadname1"));
		final String absolutePath = new File(".classpath").getAbsolutePath();
		uploadElmt.sendKeys(absolutePath);
		final JavascriptExecutor jsExecutor = ((JavascriptExecutor)driver);
		jsExecutor.executeScript("document.getElementsByName(\"subdir1\")[0].selectedIndex=5");
		driver.findElement(By.id("newsubdir1")).sendKeys("chanus");
		driver.findElement(By.id("uploadbutton")).click();
		waitForElmtLoad(driver, "fcfiledone1");
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
	
	private void waitForElmtLoad(WebDriver driver, final String elmtId) {
	     ExpectedCondition<Boolean> expectation = new
	     ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return (Boolean) ((JavascriptExecutor)driver).executeScript("return document.getElementById('"+elmtId+"')!=null");
	        }
	      };

	     Wait<WebDriver> wait = new WebDriverWait(driver,30);
	      try {
	              wait.until(expectation);
	      } catch(Throwable error) {
	              Assert.assertFalse("Timeout waiting for element load request to complete.",true);
	      }
	 }
}
