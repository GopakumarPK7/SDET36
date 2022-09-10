package org.vtiger.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateOrganizationWithIndustryAndTypeTest
{

	public static void main(String[] args) {

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
		String expectedOragnizationName=excelUtility.getDataFromExcel("Organization", 4, 1)+num;
		String expectedIndustry=excelUtility.getDataFromExcel("Organization", 4, 2);
		String expectedType=excelUtility.getDataFromExcel("Organization", 4, 3);
		long longTimeouts=Long.parseLong(timeouts);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeouts);
		webDriverUtility.openApplication(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOragnizationName);
		WebElement indusrtyDropdown=driver.findElement(By.xpath("//select[@name='industry']"));
		Select dropdown=new Select(indusrtyDropdown);
		dropdown.selectByVisibleText(expectedIndustry);
		WebElement typeDropdown=driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select dropdownB=new Select(typeDropdown);
		dropdownB.selectByValue(expectedType);
		driver.findElement(By.xpath("//input[@value='T']")).click();
		driver.findElement(By.xpath("//input[@type='button']")).click();
		String actualIndustry=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		String actualOrganizationName=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		String actualType=driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if(expectedOragnizationName.equals(actualOrganizationName)&& expectedIndustry.equals(actualIndustry)&& expectedType.equals(actualType))
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