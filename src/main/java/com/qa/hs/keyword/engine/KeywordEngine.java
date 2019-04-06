package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.qa.hs.keyword.base.Base;

public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;
	public Base base;
	public WebElement element;
	
	public static Workbook book;
	public static org.apache.poi.ss.usermodel.Sheet sheet;
	
	public final String SCENARIO_SHEET = "C:/Users/mohammed.ali1/workspace/"
			+ "KeywordDrivenHubSpot/src/main/java/com/qa/hs/keyword/scenarios/Automation_Scenario_Sheet.xlsx";
	
	
	public void startExecution(String sheetName){
		System.out.println(sheetName);
		
		String locatorname = null;
		String locatorvalue =  null;
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET);
			System.out.println(SCENARIO_SHEET);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("before getting the sheet name");
		sheet = book.getSheet(sheetName);
		int k = 0;
		for(int i=0; i<sheet.getLastRowNum();i++){
				
			System.out.println(sheet.getLastRowNum());
			try {
				String locatortype = sheet.getRow(i+1).getCell(k+1).toString().trim();
				if(!locatortype.equalsIgnoreCase("NA")){
					locatorname = locatortype.split("=")[0].trim();
					locatorvalue = locatortype.split("=")[1].trim();
					
					System.out.println("iteration is : " + i);
					System.out.println("output of locator name in iteration: " + i + "is " + locatorname);
					System.out.println("output of locator value in iteration: " + i + "is " + locatorvalue);
					
				}		
				String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
				String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
				
				System.out.println("output of action in iteration: " + i + "is " + action);
				System.out.println("output of value in iteration: " + i + "is " + value);
				
								
				
					switch (action){
					
					case "Open Browser":
					base = new Base();
					prop=base.init_properties();
					if(value.isEmpty() || value.equals("NA")){
					driver = base.init_Driver(prop.getProperty("browser"));
					}else{
					driver = base.init_Driver(value);
					
					}
					break;
				
					case "Enter URL":
					base = new Base();
					prop=base.init_properties();
					if(value.isEmpty() || value.equals("NA")){
					driver.get(prop.getProperty("url"));
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}else{
					driver.get(value);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}
					
					case "quit":
					driver.quit();
								
					default:
					break;
					}
				
					switch(locatorname){
					case "id":
					element = driver.findElement(By.id(locatorvalue));
					if(action.equalsIgnoreCase("sendkeys")){
						element.sendKeys(value);
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
					}
					else if(action.equalsIgnoreCase("click")){
						element.click(); 
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						}
					locatorname = null;
					break;
					
					case "linkText":
					element = driver.findElement(By.linkText(locatorvalue));	
					if (locatorname.equalsIgnoreCase("linkText")){
					element.click();
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					locatorname = null;	
					}
					break;
					
					default:
					break;
					
					}
			} catch (Exception e) {
				
			}
			
			
		}
		
		
	}
	
	

}
