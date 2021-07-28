package com.chan.sel.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.chan.sel.utils.DriverUtil;

public class FindJavaAutomation {
	
	 private static WebDriver driver = null;
	 private static String browserName = "chrome";
	 private static int counter = 0;
	 
	public static void main(String[] args) throws FileNotFoundException {
		initializeDriver();
		Scanner sc = new Scanner(new File("E:/MyBlogPostsLink.txt"));
		while(sc.hasNextLine())
		{
			counter++;
			String currLine = sc.nextLine();
			checkForLinks(currLine);
		}
	}
	
	private static void checkForLinks(String currLine) {
		driver.get(currLine);
		DriverUtil.waitForPageLoaded(driver);
		String pageSource = driver.getPageSource().toLowerCase();
		if(pageSource.contains("javaautomation"))
		{
			System.out.println(counter+". JavaAutomation Link");
		}
		else
		{
			System.out.println(counter);
		}
	}

	private static void initializeDriver()
	{
		if("chrome".equals(browserName))
		{
			System.setProperty("webdriver.chrome.driver", 
					"D:\\Selenium\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		}
		else
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
	}
}
