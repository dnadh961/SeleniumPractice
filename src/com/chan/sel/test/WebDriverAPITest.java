package com.chan.sel.test;

import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverAPITest {

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
			//options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
		}
		else
		{
			driver = new FirefoxDriver();
		}
	}
	
	@Test
	public void get()
	{
		driver.get("http://www.w3schools.com/html/html5_draganddrop.asp");
		waitForPageLoaded(driver);
		driver.manage().window().maximize();
	}
	
	@Test
	public void findElement()
	{
		driver.get("http://www.456bereastreet.com/lab/whitespace-around-text-fields/");
		waitForPageLoaded(driver);
		driver.manage().window().maximize();
		final WebElement elmt = driver.findElement(By.id("text-1"));
		elmt.sendKeys("chanu");
		System.out.println();
	}
	
	@Test
	public void getCurrentURL()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		System.out.println(driver.getCurrentUrl());
	}
	
	@Test
	public void getPageSource()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		System.out.println(driver.getPageSource());
	}
	
	@Test
	public void getTitle()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		System.out.println(driver.getTitle());
	}
	
	@Test
	public void handleIframe()
	{
		driver.get("http://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_target");
		waitForPageLoaded(driver);
		System.out.println("Before switch: "+driver.getCurrentUrl());
		WebElement resultFrame = driver.findElement(By.id("iframeResult"));
		driver.switchTo().frame(resultFrame);
		System.out.println("After switch: "+driver.getCurrentUrl());
		driver.findElement(By.xpath("//a[@target='iframe_a']")).click();
		waitForPageLoaded(driver);
		System.out.println(driver.getPageSource());
	}
	
	@Test
	public void navigateBack()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		driver.navigate().back();
	}
	
	@Test
	public void navigateForward()
	{
		driver.get("http://book.theautomatedtester.co.uk/");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Chapter1")).click();
		waitForPageLoaded(driver);
		driver.navigate().back();
		waitForPageLoaded(driver);
		driver.navigate().forward();
	}
	
	@Test
	public void navigateTo()
	{
		driver.get("https://www.youtube.com/");
		waitForPageLoaded(driver);
		driver.navigate().to("http://www.softwaretuitions.com");
		waitForPageLoaded(driver);
	}
	
	@Test
	public void getCookie()
	{
		driver.get("http://www.thinkvidya.com/login/auth?urlText2=index&urlText1=login");
		waitForPageLoaded(driver);
		driver.findElement(By.id("j_username")).sendKeys("dnadh961@gmail.com");
		driver.findElement(By.id("j_password")).sendKeys("dnadh961");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Log In']")).click();
		waitForPageLoaded(driver);
		System.out.println("SSO: "+driver.manage().getCookieNamed("sso"));
		System.out.println("grails: "+driver.manage().getCookieNamed("grails_remember_me"));
	}
	
	@Test
	public void alertOK()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.id("alertbox")).click();
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void isAlertPresent()
	{
		boolean isAlert = true;
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.id("alertbox")).click();
		try
		{
			driver.switchTo().alert();
		}
		catch(NoAlertPresentException e)
		{
			isAlert = false;
		}
		if(isAlert)
		{
			System.out.println("alertPresent");
		}
		else
		{
			System.out.println("Alert is not there");
		}
	}
	
	@Test
	public void getAlertText()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.id("alertbox")).click();
		String textInAlertBox = driver.switchTo().alert().getText();
		System.out.println(textInAlertBox);
	}
	
	@Test
	public void getPromptText()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.name("promptbox")).click();
		String textInPromptBox = driver.switchTo().alert().getText();
		System.out.println(textInPromptBox);
	}
	
	@Test
	public void promptOK()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.name("promptbox")).click();
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void answerPromptText()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.name("promptbox")).click();
		driver.switchTo().alert().sendKeys("vijaya");
		driver.switchTo().alert().accept();
		sleep(2);
		Alert confirmAlert = driver.switchTo().alert();
		if(confirmAlert.getText().contains("vijaya"))
		{
			System.out.println("Test is passed");
		}
		confirmAlert.accept();
		System.out.println();
	}
	
	@Test
	public void getConfirmationText()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.xpath("//input[@value='confirm']")).click();
		String textInConfirmBox = driver.switchTo().alert().getText();
		System.out.println(textInConfirmBox);
	}
	
	@Test
	public void confirmOK()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.xpath("//input[@value='confirm']")).click();
		driver.switchTo().alert().accept();
		sleep(2);
		Alert confirmAlert = driver.switchTo().alert();
		if(confirmAlert.getText().contains("OK"))
		{
			System.out.println("Test is passed");
		}
		confirmAlert.accept();
		System.out.println();
	}
	
	@Test
	public void confirmCancel()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.xpath("//input[@value='confirm']")).click();
		driver.switchTo().alert().dismiss();
		sleep(2);
		Alert confirmAlert = driver.switchTo().alert();
		if(confirmAlert.getText().contains("Cancel"))
		{
			System.out.println("Test is passed");
		}
		confirmAlert.accept();
		System.out.println();
	}
	
	@Test
	public void handlePopUpWindow()
	{
		driver.get("http://www.softwaretuitions.com/selenium/how-to-handle-popup-in-selenium-example");
		waitForPageLoaded(driver);
		driver.findElement(By.linkText("Open a popup window")).click();
		sleep(3);
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> windowHandlesI = windowHandles.iterator();
		while(windowHandlesI.hasNext())
		{
			String currentWindow = windowHandlesI.next();
			if(!currentWindow.equals(mainWindowHandle))
			{
				driver.switchTo().window(currentWindow);
				System.out.println("Current window title: "+driver.getTitle());
				driver.close();
			}
		}
		driver.switchTo().window(mainWindowHandle);
		System.out.println(driver.getTitle());
	}
	
	@Test
	public void getCookies()
	{
		driver.get("http://www.thinkvidya.com/login/auth?urlText2=index&urlText1=login");
		waitForPageLoaded(driver);
		System.out.println("Before LogIn: "+driver.manage().getCookies());
		driver.findElement(By.id("j_username")).sendKeys("dnadh961@gmail.com");
		driver.findElement(By.id("j_password")).sendKeys("dnadh961");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Log In']")).click();
		waitForPageLoaded(driver);
		System.out.println("After LogIn: "+driver.manage().getCookies());
	}
	
	@Test
	public void deleteCookieNamed()
	{
		driver.get("http://www.thinkvidya.com/login/auth?urlText2=index&urlText1=login");
		waitForPageLoaded(driver);
		System.out.println("Before LogIn: "+driver.manage().getCookies());
		driver.findElement(By.id("j_username")).sendKeys("dnadh961@gmail.com");
		driver.findElement(By.id("j_password")).sendKeys("dnadh961");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Log In']")).click();
		waitForPageLoaded(driver);
		System.out.println("After LogIn: "+driver.manage().getCookies());
		driver.manage().deleteCookieNamed("sso");
		System.out.println("After sso deletion: "+driver.manage().getCookies());
	}
	
	@Test
	public void deleteAllCookies()
	{
		driver.get("http://www.thinkvidya.com/login/auth?urlText2=index&urlText1=login");
		waitForPageLoaded(driver);
		System.out.println("Before LogIn: "+driver.manage().getCookies());
		driver.findElement(By.id("j_username")).sendKeys("dnadh961@gmail.com");
		driver.findElement(By.id("j_password")).sendKeys("dnadh961");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Log In']")).click();
		waitForPageLoaded(driver);
		System.out.println("After LogIn: "+driver.manage().getCookies());
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		System.out.println();
	}
	
	@Test
	public void refresh()
	{
		driver.get("https://www.youtube.com/");
		waitForPageLoaded(driver);
		driver.navigate().refresh();
		waitForPageLoaded(driver);
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
