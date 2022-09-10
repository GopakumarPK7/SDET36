package org.tyss.genericUtility;

import org.openqa.selenium.WebDriver;

public class ThreadsafeClass {
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	public static void setDriver(WebDriver actDriver)
	{
		driver.set(actDriver);
	}
}
