package com.qa.hs.keyword.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestIsolation {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.drive","C:/Program Files/Mozilla Firefox/firefox.exe");
		//System.setProperty("webdriver.ie.driver", "C:/Program Files/internet explorer/iexplore.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		
		
	}

}
