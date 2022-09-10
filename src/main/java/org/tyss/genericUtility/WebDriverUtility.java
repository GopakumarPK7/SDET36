package org.tyss.genericUtility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all the webdriver actions
 * @author admin
 *
 */
public final class WebDriverUtility
{
private WebDriver driver;
private Actions act;	
private JavascriptExecutor js;
/**
 * 	This method is used to setup the driver instance
 */
public WebDriver setupDriver(String browser)
{
	switch(browser)
	{
	case"chrome":
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();   
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
	return driver;
}
/**
 * This method is used to maximize the browser
 */
public void maximizeBrowser()
{
	driver.manage().window().maximize();
}
/**
 * This method is used to wait the paging implicit wait
 */
public void implicitWait(long longTimeout)
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
}
/**
 * This method is used to load the web application
 */
public void openApplication(String url)
{
	driver.get(url);
}
/**
 * This method is used to initialize the actions class
 */
public void initializeAction()
{
	act=new Actions(driver);
}
/**
 * This method is used for mouse hover actions
 */
public void mouseHoverOnElement(WebElement element)
{
	act.moveToElement(element).perform();
}
/**
 * This method is used to perform right click action on current mouse location
 */
public void rightclickaction()
{
	act.contextClick().perform();
}
/**
 * This method is used to perform right click action on particular webelement
 */
public void rightclickaction(WebElement element)
{
	act.contextClick(element).perform();
}
/**
 * This method is used to close particular browser
 */
public void closeBrowser()
{
	driver.quit();
}
/**
 * This method is used to close particular tab
 */
public void closeTab()
{
	driver.close();
}
/**
 * This method is used to switch frame based on index
 */
public void switchFrame(int index)
{
	driver.switchTo().frame(index);
}
/**
 * This method is used to switch frame based on Webelement address
 */
public void switchFrame(WebElement element)
{
	driver.switchTo().frame(element);
}
/**
 * This method is used to switch frame based name or id attribute
 */
public void switchFrame(String nameOrID)
{
	driver.switchTo().frame(nameOrID);
}
/**
 * This method is used to switch back from frame to parent web page
 */
public void switchBackFromFrame(String strategy)
{
	switch(strategy.toLowerCase().trim())
	{
	case "default":
		driver.switchTo().defaultContent();
		break;
	case "parent":
		driver.switchTo().parentFrame();
		break;
	default:
		System.out.println("please enter valid strategy either <default or parent>");
		break;
	}
}
/**
 * This method is used to handle <select> tag dropdown by using visible text
 */
public void handleSelectDropdown(WebElement dropDownElement,String visibleText)
{
Select select =new Select(dropDownElement);
select.selectByVisibleText(visibleText);
}	
/**
 * This method is used to handle <select> tag dropdown by using value 
 */
public void handleSelectDropdown(String value,WebElement dropDownElement)
{
Select select =new Select(dropDownElement);
select.selectByValue(value);
}	
/**
 * This method is used to handle <select> tag dropdown by using index
 */
public void handleSelectDropdown(int index,WebElement dropDownElement)
{
Select select =new Select(dropDownElement);
select.selectByIndex(index);
}
/**
 * This method is used to scroll till the element is visible
 */
public void scrollTillElement(WebElement element)
{
	js.executeScript("arguments[0].scrollInttoView()",element);
}
/**
 * This method is used to highlight the element 
 */
public void highlightElement(WebElement element)
{
	js.executeScript("arguments[0].setAttribute('style','border:2px solid red;')", element);
}
/**
 * This mrthod is used to scroll till some position
 */
public void scrollTillSomePosition(int y_position,String strategy)
{
String sign=strategy.equalsIgnoreCase("up")?"-":"+";
js.executeScript("window.scrollBy(0,"+sign+"arguments[0])", y_position);
}
/**
 * This method is used to switch the window
 */
public void switchWindow(String partialText,String strategy)
{
	Set<String> winIds=driver.getWindowHandles();
	for(String id:winIds)
	{
		driver.switchTo().window(id);
		if(strategy.toString().equalsIgnoreCase("url"))
		{
			if(driver.getCurrentUrl().contains(partialText))
			{
				break;
			}
		}
		else if(strategy.toString().equalsIgnoreCase("title"))
		{
			if(driver.getTitle().contains(partialText))
			{
				break;
			}
		}
	}
}
}