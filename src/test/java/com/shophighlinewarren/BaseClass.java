package com.shophighlinewarren;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseClass {
	protected static   WebDriver driver;

//	Assert verfication
	
	public static void assertEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
 
		} catch (AssertionError e) {
			System.out.println("Expected Value : " + expected);
			System.out.println("Actual Value : " + actual);
			System.out.println("Actual and Expected Values are Mismatched :" + e.getMessage());
			Assert.fail();
		}
	}
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
 
}
