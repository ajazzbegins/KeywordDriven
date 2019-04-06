package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



public class Base {
	
	public WebDriver driver;
	public Properties prop;
	 
	public WebDriver init_Driver(String browserName){
		if(browserName.equals("chrome")){
			 System.out.println("inside if condition in Base.java");
			 System.setProperty("webdriver.chrome.driver", "C:/Selenium_Softwares/chromedriver_win32/chromedriver.exe");
			 driver = new ChromeDriver();
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			 }else{
				 driver = new ChromeDriver();
			 }
		 return driver; //we have changed the return type from Void to WebDriver
			}
	
	/*if(browserName.equals("chrome")){
		 System.out.println("inside if condition in Base.java");
		 System.setProperty("webdriver.ie.driver", "C:/Program Files/internet explorer/iexplore.exe");
		 driver = new InternetExplorerDriver();
		 }else{
			 driver = new ChromeDriver();
		 }
	 return driver; //we have changed the return type from Void to WebDriver
		}
	*/
	
	
	public Properties init_properties(){
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:/Users/mohammed.ali1/workspace/KeywordDrivenHubSpot/src/main/java/com/qa/hs/keyword/config/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop; //we have changed the return type from Void to Properties
	}
	
	
	
	

}
