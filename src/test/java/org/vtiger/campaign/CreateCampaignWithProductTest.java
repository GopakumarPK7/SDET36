package org.vtiger.campaign;

import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateCampaignWithProductTest {

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
		String expectedCampaignName=excelUtility.getDataFromExcel("Camapign", 4, 1)+num;
		String expectedProductName=excelUtility.getDataFromExcel("Camapign", 4, 2)+num;
		long longTimeouts=Long.parseLong(timeouts);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeouts);
		webDriverUtility.openApplication(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebElement moreIcon=driver.findElement(By.xpath("//a[text()='More']"));
		webDriverUtility.initializeAction();
		webDriverUtility.mouseHoverOnElement(moreIcon);
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		System.out.println("Expected campaign name is="+expectedCampaignName);
		driver.findElement(By.name("campaignname")).sendKeys(expectedCampaignName);
		driver.findElement(By.xpath("//input[@name='product_name']/following-sibling::img")).click();
		String parentId=driver.getWindowHandle();
		Set<String>allChildId=driver.getWindowHandles();
		for(String childId:allChildId)
		{
			if(!parentId.equals(childId))
			{
				driver.switchTo().window(childId);
			}

		}
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.linkText(expectedProductName)).click();
		driver.switchTo().window(parentId);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String actualCampaignName=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		System.out.println("Expected product Name="+expectedProductName);
		System.out.println("Actual campaign name is="+actualCampaignName);
		String actualProductName=driver.findElement(By.xpath("//td[@id='mouseArea_Product']/span/a")).getText();
		System.out.println("Actual product name is="+actualProductName);
		if(expectedCampaignName.equals(actualCampaignName)&&(expectedProductName.equals(actualProductName)))
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
