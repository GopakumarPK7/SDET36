package org.vtiger.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
public class CreateProductTest {

	public static void main(String[] args) 
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
		String expectedProductName=excelUtility.getDataFromExcel("Product", 2, 1)+num;
		long longTimeouts=Long.parseLong(timeouts);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeouts);
		webDriverUtility.openApplication(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualProductName=driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		if(expectedProductName.equals(actualProductName))
		{
			System.out.println("Validation passed");
		}
		else
		{
			System.out.println("Validation failed");	
		}
		driver.quit();


	}

}
