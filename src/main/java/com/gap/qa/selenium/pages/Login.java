package com.gap.qa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gap.qa.selenium.framework.Browser;
import com.gap.qa.selenium.framework.CoreFunctions;
import com.gap.qa.selenium.uimap.UIMapSample;
import com.thoughtworks.selenium.Selenium; 

public class Login {

	public static WebDriver driver = null;
	
	public static void fnLogin()
	{		
		System.out.println("ravi");
		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.get("http://www.flipkart.com");
						
	}
	
	public static void test()
	{
		System.out.println("pwd");
		driver.findElement(By.xpath(UIMapSample.btnLogin)).click();
	}
	
	public static void test1()
	{
		
	}
		
}
