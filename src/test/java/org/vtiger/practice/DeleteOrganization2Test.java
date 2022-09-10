package org.vtiger.practice;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.WebDriverUtility;

public class DeleteOrganization2Test
{
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileUtility fileUtility=new FileUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		excelUtility.initilizeExcelFile(IConstants.VTIGEREXCELFILEPATH);
		String browser=fileUtility.getDataFromProperty("browser");
		String url=fileUtility.getDataFromProperty("url");
		String userName=fileUtility.getDataFromProperty("username");
		String password=fileUtility.getDataFromProperty("password");
		String timeouts=fileUtility.getDataFromProperty("timeout");
		String expectedOragnizationName="SDET40";
		long longTimeouts=Long.parseLong(timeouts);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(longTimeouts));
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeouts);
		webDriverUtility.openApplication(url);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		String[] arrPageCount=driver.findElement(By.xpath("//span[@name='Accounts_listViewCountContainerName']")).getText().split("");
		int pageCount=Integer.parseInt(arrPageCount[arrPageCount.length-1]);
		boolean flag=true;
		for(int j=0;j<pageCount;j++)
		{
			List<WebElement> listOrganization=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
			for(int i=0;i<listOrganization.size();i++)
			{
				String orgName=listOrganization.get(i).getText();
				System.out.println(orgName);

				if(orgName.equals(expectedOragnizationName))
				{
					driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[\"+(i+2)+\"]/td[1]/input")).click();
					flag=false;
					break;
				}
			}
			if(flag==false)
			{
				break;
			}
			else
			{
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='status']"))));
				driver.findElement(By.xpath("//a[@title='Next']")).click();

			}

		}
		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
		driver.switchTo().alert().accept();
		List<WebElement> listOrganization2=driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
		for(int i=0;i<listOrganization2.size();i++)
		{
			String orgName=listOrganization2.get(i).getText();
			if(orgName.equals(expectedOragnizationName))
			{
				
				System.out.println("TC is failed");
			}
			else
			{
				System.out.println("TC is passed");	

			}

			WebElement admin=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			webDriverUtility.initializeAction();
			webDriverUtility.mouseHoverOnElement(admin);
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			webDriverUtility.closeBrowser();
		}
	}
}

