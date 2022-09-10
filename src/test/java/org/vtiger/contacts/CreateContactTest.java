package org.vtiger.contacts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateContactTest 
{
	public static void main(String[] args) throws IOException 
	{
		FileUtility fileUtility=new FileUtility();
		JavaUtility javaUtility=new JavaUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		excelUtility.initilizeExcelFile(IConstants.VTIGEREXCELFILEPATH);
		int num=javaUtility.getRandomNumber();
		String browser=fileUtility.getDataFromProperty("browser");
		String url=fileUtility.getDataFromProperty("url");
		String userName=fileUtility.getDataFromProperty("username");
		String password=fileUtility.getDataFromProperty("password");
		String timeouts=fileUtility.getDataFromProperty("timeout");
		String expectedContactName=excelUtility.getDataFromExcel("Contact", 2, 1)+num;
		long longTimeouts=Long.parseLong(timeouts);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeouts);
		webDriverUtility.openApplication(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		System.out.println("Expected contact name="+expectedContactName);
		driver.findElement(By.name("lastname")).sendKeys(expectedContactName);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualContactName=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		System.out.println("Actual contact name is"+actualContactName);

		if(actualContactName.equals(expectedContactName))
		{
			System.out.println("Validation passed");
		}
		else
		{
			System.out.println("Validation failed");	
		}
		WebElement admin=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.initializeAction();
		webDriverUtility.mouseHoverOnElement(admin);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webDriverUtility.closeBrowser();
	}

}
