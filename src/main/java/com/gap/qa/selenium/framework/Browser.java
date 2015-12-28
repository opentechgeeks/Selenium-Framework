package com.gap.qa.selenium.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {

	public static RemoteWebDriver getDriver(String brName){
	
	WebDriver driver = null;
	
	switch (brName){
	case("Chrome"):
	System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().deleteAllCookies();
	break;
	case("IE"):
		System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
	 	DesiredCapabilities ieCapabilities= new DesiredCapabilities();
	    ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
	    ieCapabilities.setCapability("trustAllSSLCertificates", true);
	    driver = new InternetExplorerDriver(ieCapabilities);
	    driver.manage().deleteAllCookies();
	break;		
	case("Firefox"):
	driver = new FirefoxDriver();
	driver.manage().deleteAllCookies();
	driver.get("http://www.google.com");
	 break;
	
	}
	return (RemoteWebDriver)driver;
	
}
}
