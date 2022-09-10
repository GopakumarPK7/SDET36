package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class fetchDataFromExcelTest {

	public static void main(String[] args) throws IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
		Properties properties=new Properties();
		properties.load(fis);
		
		FileInputStream excel=new FileInputStream("./src/test/resources/VtigerTest.xlsx");
		Workbook book=WorkbookFactory.create(excel);
		Sheet sheet=book.getSheet("Contact");
				
		//Fetch data from properties		
		String browser=properties.getProperty("browser").trim();
		String url=properties.getProperty("url").trim();
		String userName=properties.getProperty("username").trim();
		String password=properties.getProperty("password").trim();
		String timeouts=properties.getProperty("timeout").trim();
		
		//Fetch datafrom excel
		int num=new Random().nextInt(1000);
		String actual=sheet.getRow(2).getCell(1).getStringCellValue()+num;
		
		long longTimeouts=Long.parseLong(timeouts);
		WebDriver driver=null;
		
		//runtime polymorphism
		switch(browser)
		{
		case"chrome":
			WebDriverManager.chromedriver().setup(); //method chaining
			driver = new ChromeDriver();   //abstraction
			break;
		case"firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case"edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Wrong browser key");
			break;
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeouts));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(userName,Keys.TAB,password,Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(actual);
		System.out.println("Expected name ="+actual);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String expected=driver.findElement(By.id("dtlview_Last Name")).getText();
		System.out.println("Actual name="+expected);
		if(actual.equals(expected))
		{
			System.out.println("Validation passed");
		}
		else
		{
			System.out.println("Validation failed");	
		}
		WebElement admin=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(admin).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}


	

	}

