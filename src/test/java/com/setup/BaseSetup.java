package com.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class BaseSetup {
	public static WebDriver driver;
	public static ChromeOptions coptions;
	public static EdgeOptions eoptions;

	public static WebDriver chromeDriverSetup() {
		coptions = new ChromeOptions();
		coptions.addArguments("--start-maximized");
//		coptions.addArguments("Incognito");
		coptions.addArguments("--disable-notifications");
		coptions.addArguments("--disable-popup-blocking");
		coptions.addArguments("deny-permission-prompts");
		
		driver = new ChromeDriver(coptions);		
		return driver;
	}
	
	public static WebDriver edgeDriverSetup() {
		eoptions = new EdgeOptions();
		eoptions.addArguments("--start-maximized");
//		eoptions.addArguments("InPrivate");
		eoptions.addArguments("--disable-notifications");
		eoptions.addArguments("--disable-popup-blocking");
		eoptions.addArguments("deny-permission-prompts");
		
		driver = new EdgeDriver(eoptions);		
		return driver;
	}
	
	public static void tearDown() {
	    if (driver != null) {
	        try {
	            driver.quit();
	        } catch (Exception e) {
	            System.out.println("Driver already closed or session ended.");
	        }
	    }
	}
}
