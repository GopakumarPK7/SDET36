package org.vtiger.practice;


import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.DatabaseUtility;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class ValidateDatabaseWrtoGui {

	public static void main(String[] args) throws SQLException {
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
		String query="insert into project values('TY_PROJ_7"+randomnumber+"','David','"+javaUtility.getCurrentDate("dd/MM/yyyy")+"','"+expectedProjectName+"','Not yet started',12)";
		databaseUtility.modifyDataInDB(query);
		WebDriver driver=webDriverUtility.setupDriver(browser);
		webDriverUtility.openApplication(appUrl);
		webDriverUtility.maximizeBrowser();
		webDriverUtility.implicitWait(longTimeout);
	
	    driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
	    driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	    driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	    driver.findElement(By.xpath("//a[text()='Projects']")).click();

	    List<WebElement> listOfProjects=driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));
	    for(WebElement project:listOfProjects)
	    {
	    	String actualProjectName=project.getText();
	    	if(actualProjectName.equals(expectedProjectName))
	    	{
	    		System.out.println("Project is present in list of projects page");
	    		System.out.println(actualProjectName);
	    		break;
	    	}
	    }

	    
		databaseUtility.closeDBConnection();
		webDriverUtility.closeBrowser();
	}

}
