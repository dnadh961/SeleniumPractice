package com.chan.sel.test;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.chan.sel.utils.DriverUtil;

public class LocatorsInDriver {

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
	public void byID()
	{
		driver.get("https://www.facebook.com/");
		DriverUtil.waitForPageLoaded(driver);
		WebElement emailElmt = driver.findElement(By.id("email"));
		emailElmt.sendKeys("shabari");
		System.out.println();
	}
	
	@Test
	public void byName()
	{
		driver.get("http://www.thinkvidya.com/");
		DriverUtil.waitForPageLoaded(driver);
		WebElement firstNameElmt = driver.findElement(By.name("fullName"));
		firstNameElmt.sendKeys("devendra");
		System.out.println();
	}
	
	@Test
	public void byCss()
	{
		driver.get("http://www.thinkvidya.com/");
		DriverUtil.waitForPageLoaded(driver);
		WebElement firstNameElmt = driver.findElement(By.cssSelector("input[class='mb-0'][placeholder='Name']"));
		firstNameElmt.sendKeys("devendra");
		System.out.println();
	}
	
	@Test
	public void byXpath()
	{
		driver.get("http://www.thinkvidya.com/");
		DriverUtil.waitForPageLoaded(driver);
		WebElement firstNameElmt = driver.findElement(By.xpath("//input[@class='mb-0' and @placeholder='Name']"));
		firstNameElmt.sendKeys("devendra");
		System.out.println();
	}
	
	@Test
	public void byLink()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		DriverUtil.waitForPageLoaded(driver);
		WebElement firstNameElmt = driver.findElement(By.linkText("Chapter3"));
		firstNameElmt.click();
		System.out.println();
	}
	
	@Test
	public void byPartialLink()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		DriverUtil.waitForPageLoaded(driver);
		WebElement firstNameElmt = driver.findElement(By.partialLinkText("3"));
		firstNameElmt.click();
		System.out.println();
	}
	
	@Test
	public void byClassName()
	{
		driver.get("http://www.thinkvidya.com/");
		DriverUtil.waitForPageLoaded(driver);
		WebElement mbElmt = driver.findElement(By.className("mb-0"));
		mbElmt.sendKeys("dev");
		System.out.println();
	}
	
	@Test
	public void byTag()
	{
		driver.get("http://www.thinkvidya.com/");
		DriverUtil.waitForPageLoaded(driver);
		List<WebElement> mbElmt = driver.findElements(By.tagName("input"));
		Iterator<WebElement> mbElmtI = mbElmt.iterator();
		while(mbElmtI.hasNext())
		{
			WebElement currElmt = mbElmtI.next();
			System.out.println(currElmt.getAttribute("type"));
		}
	}
	
	
	@After
	public void close()
	{
		driver.close();
		driver.quit();
	}
	
	private void sleep(int seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
