package org.vtiger.practice;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
	//create object for generic utility
		FileUtility fileUtility=new FileUtility();
		JavaUtility javaUtility=new JavaUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
	//initialize data from property and excel file
 	    fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
 	    excelUtility.initilizeExcelFile(IConstants.VTIGEREXCELFILEPATH);
 	//Generate the random number
 	    int num=javaUtility.getRandomNumber();
 	//Fetch the data from property file
		String browser=fileUtility.getDataFromProperty("browser");
		String url=fileUtility.getDataFromProperty("url");
		String userName=fileUtility.getDataFromProperty("username");
		String password=fileUtility.getDataFromProperty("password");
		String timeouts=fileUtility.getDataFromProperty("timeout");
    //Fetch data from excel file
		String expectedOragnizationName=excelUtility.getDataFromExcel("Contact", 4, 2)+num;
		String expectedContactName=excelUtility.getDataFromExcel("Contact",4,1)+num;
	//Convert string to long
		long longTimeouts=Long.parseLong(timeouts);
	//Launching the browser in runtime based on browser key
		WebDriver driver=webDriverUtility.setupDriver(browser);
	//pre setting for the browser
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeouts));
	//Navigating to the application
	    webDriverUtility.openApplication(url);
	    
	    driver.findElement(By.name("user_name")).sendKeys(userName);
	    driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
	    driver.findElement(By.xpath("//input[@type='submit']")).click();
	    driver.findElement(By.xpath("//a[text()='Organizations']")).click();
	    driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	    driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOragnizationName);
	    driver.findElement(By.xpath("//input[@type='button']")).click();
	    String actualOrganization=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
	    driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		System.out.println("Expected contact name="+expectedContactName);
		driver.findElement(By.name("lastname")).sendKeys(expectedContactName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		String parentId=driver.getWindowHandle();
		Set<String>allChildId=driver.getWindowHandles();
		for(String childId:allChildId)
		{
			if(!parentId.equals(childId))
			{
				driver.switchTo().window(childId);
			}
			
		}
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(actualOrganization);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.linkText(actualOrganization)).click();
		driver.switchTo().window(parentId);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualContactName=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		System.out.println("Actual contact name="+actualContactName);
		String actualOrganizationName=driver.findElement(By.xpath("//td[text()='Organization Name']/following-sibling::td/a")).getText();
		System.out.println("Expected organization name="+expectedOragnizationName);
		System.out.println("Actual organization name="+actualOrganizationName);
		if(expectedContactName.equals(actualContactName)&&(expectedOragnizationName.equals(actualOrganizationName)))
			{
		
			System.out.println("Validation passed");
		}
		else
			{
			System.out.println("Validation failed");	
			}

	    WebElement admin = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	    webDriverUtility.mouseHoverOnElement(admin);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webDriverUtility.closeBrowser();

	}

}
