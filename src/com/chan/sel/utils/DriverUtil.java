package com.chan.sel.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class DriverUtil {

	public static void waitForPageLoaded(WebDriver driver) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 900);
		try {
			wait.until(checkPageLoadStatus());
		} catch (Throwable error) {

		}
	}

	public static void waitForElementPresence(final WebDriver driver,
			final By locator) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 900);
		try {
			wait.until(presenceOfElementLocated(locator));
		} catch (Throwable error) {

		}
	}
	
	public static void waitForCondition(final WebDriver driver,
			final String condition) {
		Wait<WebDriver> wait = new WebDriverWait(driver, 900);
		try {
			wait.until(myCondition(condition));
		} catch (Throwable error) {

		}
	}

	private static Function<WebDriver, WebElement> presenceOfElementLocated(
			final By locator) {
		return new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		};
	}

	private static Function<WebDriver, Boolean> checkPageLoadStatus() {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
	}
	
	private static Function<WebDriver, Boolean> myCondition(final String condition) {
		return new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver).executeScript(
						"return "+condition);
			}
		};
	} 
}
