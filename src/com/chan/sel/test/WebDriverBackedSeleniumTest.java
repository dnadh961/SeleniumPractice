package com.chan.sel.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class WebDriverBackedSeleniumTest {

	/**
	 * @param args
	 */
	
	private static Selenium sel;
	private static WebDriver driver;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initializeDriver("chrome");
		sel = new WebDriverBackedSelenium(driver, "http://www.google.co.in/");
		sel.open("http://www.thinkvidya.com/");
		sel.waitForPageToLoad("180000");
		driver.findElement(By.id("firstName")).sendKeys("dev");
		sel.type("id=username", "deepika");
		System.out.println();
	}
	
	private static void initializeDriver(String browser) {
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

}
