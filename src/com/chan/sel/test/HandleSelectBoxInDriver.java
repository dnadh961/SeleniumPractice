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
import org.openqa.selenium.support.ui.Select;

import com.chan.sel.utils.DriverUtil;

public class HandleSelectBoxInDriver {
	
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
	public void selectByIndex()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		mySelect.selectByIndex(2);
		System.out.println();
	}
	
	@Test
	public void selectByValue()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		mySelect.selectByValue("audi");
		System.out.println();
	}
	
	@Test
	public void selectByVisibleText()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		mySelect.selectByVisibleText("Saab");
		System.out.println();
	}
	
	@Test
	public void deselectByIndex()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		mySelect.deselectByIndex(2);
		System.out.println();
	}
	
	@Test
	public void deselectByValue()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		mySelect.deselectByValue("audi");
		System.out.println();
	}
	
	@Test
	public void deselectByVisibleText()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		mySelect.deselectByVisibleText("Saab");
		System.out.println();
	}
	
	@Test
	public void deselectAll()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		mySelect.deselectAll();
	}
	
	@Test
	public void isMultiple()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		System.out.println(mySelect.isMultiple());
	}
	
	@Test
	public void getOptions()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		List<WebElement> myOptions = mySelect.getOptions();
		Iterator<WebElement> myOptionsI = myOptions.iterator();
		while(myOptionsI.hasNext())
		{
			WebElement currentOption = myOptionsI.next();
			System.out.println(currentOption.getText());
		}
	}
	
	@Test
	public void getAllSelectedOptions()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		List<WebElement> myOptions = mySelect.getAllSelectedOptions();
		Iterator<WebElement> myOptionsI = myOptions.iterator();
		while(myOptionsI.hasNext())
		{
			WebElement currentOption = myOptionsI.next();
			System.out.println(currentOption.getText());
		}
	}
	
	@Test
	public void getFirstSelectedOption()
	{
		driver.get("http://www.w3schools.com/tags/tryhtml_select_multiple.htm");
		DriverUtil.waitForPageLoaded(driver);
		WebElement selectElmt = driver.findElement(By.xpath("//select"));
		Select mySelect = new Select(selectElmt);
		WebElement firstSelected = mySelect.getFirstSelectedOption();
		System.out.println(firstSelected.getText());
	}
	
	@After
	public void close()
	{
		driver.close();
		driver.quit();
	}
}
