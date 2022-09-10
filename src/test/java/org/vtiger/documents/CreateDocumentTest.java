package org.vtiger.documents;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateDocumentTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException
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
		String expectedTitle=excelUtility.getDataFromExcel("Document", 2, 1)+num;
		String expectedNote=excelUtility.getDataFromExcel("Document", 2, 3);
		long longTimeouts=Long.parseLong(timeouts);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeouts);
		webDriverUtility.openApplication(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.linkText("Documents")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(expectedTitle);
		WebElement note=driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(note);
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(expectedNote); 
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//input[@id='filename_I__']")).sendKeys("D:\\Testyanthra\\Study material\\Vtiger Scenarios.txt");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualTitle=driver.findElement(By.xpath("//span[@id='dtlview_Title']")).getText();
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("TC passed");
		}
		else
		{
			System.out.println("TC Failed");
		}


		WebElement admin=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.initializeAction();
		webDriverUtility.mouseHoverOnElement(admin);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webDriverUtility.closeBrowser();

	}
}