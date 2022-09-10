package org.vtiger.practice;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class DeleteOrganizationTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
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
		String expectedOragnizationName=excelUtility.getDataFromExcel("Organization", 2, 1)+num;
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
		System.out.println(expectedOragnizationName);
		driver.findElement(By.xpath("//input[@type='button']")).click();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(longTimeouts));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='small']"))));
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();  
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Go to Advanced Search']"))));
		String[] arrPageCount=driver.findElement(By.xpath("//span[@name='Accounts_listViewCountContainerName']")).getText().split("");
		String pageCount=arrPageCount[arrPageCount.length-1];
		System.out.println(pageCount);
		driver.findElement(By.xpath("//input[@class='small']")).clear();
		driver.findElement(By.xpath("//input[@class='small']")).sendKeys(pageCount,Keys.ENTER);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='status']"))));
		List<WebElement> listOrganization = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
		for(int i=0;i<listOrganization.size();i++)
		{
			String orgName = listOrganization.get(i).getText();
			if(orgName.equals(expectedOragnizationName))
			{
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[\"+(i+2)+\"]/td[1]/input")).click();
				break;
			}
		}
		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
		driver.switchTo().alert().accept();
		List<WebElement> listOrg = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
	
		for(int i=0;i<listOrg.size();i++)
		{
			String orgName = listOrg.get(i).getText();
			if(orgName.equals(expectedOragnizationName))
			{
		
				break;
			}  
			else
			{
	
				System.out.println("Tc passed");
			}

			
	
	}
		WebElement admin=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.initializeAction();
		webDriverUtility.mouseHoverOnElement(admin);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webDriverUtility.closeBrowser();
}
}
