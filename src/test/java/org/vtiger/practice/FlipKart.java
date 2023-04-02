package org.vtiger.practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class FlipKart {

	public static void main(String[] args) {
		WebDriverUtilities driverUtility=new WebDriverUtilities();
		HomePage home=new HomePage(driver);
		WebDriver Driver=driverUtility.setupDriver("chrome");
		driverUtility.openApplication("https://www.flipkart.com/");
		driverUtility.implicitWait(10);
		driverUtility.maximizeBrowser();
		
//		WebDriverManager.chromedriver().setup();;
//		WebDriver driver=new ChromeDriver();
//		driver.get("https://www.flipkart.com/");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();
//		SoftAssert asert=new SoftAssert();
//		driver.findElement(By.xpath("//button[text()='✕']")).click();
//		driver.findElement(By.name("q")).sendKeys("Winter heater");
//		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		driver.findElement(By.xpath("//a[contains(@title,\"DARSHANAM WORLD 220v 500w Portable\")]")).click();
		String pid = driver.getWindowHandle();
		Set<String> cid = driver.getWindowHandles();
		for(String aid : cid)
		{
			if(!pid.equals(aid))
			{
				driver.switchTo().window(aid);
			}
		}
		String exp = driver.findElement(By.xpath("//span[contains(text(),\"DARSHANAM WORLD 220v 500w\")]")).getText();
		driver.findElement(By.xpath("//button[@class=\"_2KpZ6l _2U9uOA _3v1-ww\"]")).click();
		String act = driver.findElement(By.xpath("//a[contains(text(),\"DARSHANAM WORLD 220v 500w Portable\")]")).getText();
		asert.assertEquals(act, exp);
		System.out.println("validation passed");
		driver.quit();
	}

}
