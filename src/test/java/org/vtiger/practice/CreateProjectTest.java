package org.vtiger.practice;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.DatabaseUtility;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateProjectTest {

	public static void main(String[] args) throws SQLException 
	{ 
		JavaUtility javaUtility=new JavaUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		DatabaseUtility databaseUtility=new DatabaseUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		FileUtility fileUtility=new FileUtility();
		excelUtility.initilizeExcelFile(IConstants.RMGEXCELFILEPATH);
		fileUtility.initializePropertyFile(IConstants.RMGPROPERTYFILEPATH);
		int randomnumber=javaUtility.getRandomNumber();
		String expectedProjectName=excelUtility.getDataFromExcel("Rmg", 1, 1)+randomnumber;
		System.out.println("Expected project name-"+expectedProjectName);
		String dbUrl=fileUtility.getDataFromProperty("dburl");
		String dbUsername=fileUtility.getDataFromProperty("dbusername");
		String dbPassword=fileUtility.getDataFromProperty("dbpassword");
		String dbName=fileUtility.getDataFromProperty("dbname");
		String browser=fileUtility.getDataFromProperty("browser");
		String appUrl=fileUtility.getDataFromProperty("rmgurl");
		String timeouts=fileUtility.getDataFromProperty("timeouts");
		long longTimeout=javaUtility.convertStringToLong(timeouts);
		databaseUtility.getConnectionWithDB(dbUrl+dbName, dbUsername, dbPassword);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		webDriverUtility.openApplication(appUrl);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeout);
		String projectName="SDET36_234";
	    driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
	    driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	    driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	    driver.findElement(By.xpath("//a[text()='Projects']")).click();
	    driver.findElement(By.xpath("//span[text()='Create Project']")).click();
	    driver.findElement(By.name("projectName")).sendKeys(projectName);
	    driver.findElement(By.name("createdBy")).sendKeys("John");
	    WebElement projectStatus=driver.findElement(By.xpath("//label[.='Project Status ' ]/following-sibling::select"));
	    webDriverUtility.handleSelectDropdown(projectStatus,"On Going");
	    driver.findElement(By.xpath("//input[@type='submit']")).click();
	    databaseUtility.getConnectionWithDB(dbUrl+dbName, dbUsername, dbPassword);
	    String query="select * from project;";
	    boolean status=databaseUtility.verifyDataInDB(query,"project_name", projectName);
	    if(status==true)
	    {
	    	javaUtility.printStatement("Project is present in db");
	    	
	    }
	    else
	    {
	    	javaUtility.printStatement("Project is not present in db");
	    }
		
databaseUtility.closeDBConnection();
webDriverUtility.closeBrowser();
	    

	}

}
