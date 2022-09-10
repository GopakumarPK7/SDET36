package org.vtiger.practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.tyss.genericUtility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutoPractice {

	public static void main(String[] args) 
	{
WebDriverManager.chromedriver().setup();
WebDriver driver=new ChromeDriver();
driver.manage().window().maximize();
JavascriptExecutor js=(JavascriptExecutor)driver;
JavaUtility javaUtility=new JavaUtility();
js.executeScript("window.location='http://localhost:8888'");
WebElement userNameTextField=driver.findElement(By.xpath("//input[@name='user_name']"));
WebElement passwordTextField=driver.findElement(By.xpath("//input[@name='user_password']"));
WebElement loginBtn=driver.findElement(By.xpath("//input[@id='submitButton']"));
js.executeScript("arguments[0].value=argument[1]",userNameTextField,"admin");
js.executeScript("arguments[0].value=argument[1]",passwordTextField,"admin");
js.executeScript("arguments[0].value=argument[1]", loginBtn);
WebElement scrollTillElement=driver.findElement(By.xpath("//b[contains(.='Top Quotes')]"));
js.executeScript("arguments[0].scrollIntoView()", scrollTillElement);
TakesScreenshot ts=(TakesScreenshot)driver;
File src=ts.getScreenshotAs(OutputType.FILE);
File dst=new File("./errorshhots/"+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss"));
try {
	FileUtils.copyFile(src, dst);
} catch (IOException e) {
	e.printStackTrace();
}


	}

}
