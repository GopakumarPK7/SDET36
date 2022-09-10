package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.tyss.genericUtility.WebDriverUtility;

public class CommonPage 
{
public CommonPage(WebDriver driver)
{
PageFactory.initElements(driver,this);	
}
@FindBy(xpath="//a[text()='More']")
private WebElement moreTab;
@FindBy(xpath="//a[@name='Campaigns']")
private WebElement campaignsTab;
@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
private WebElement adminIcon;
@FindBy(xpath="//a[text()='Sign Out']")
private WebElement signOutLink;
@FindBy(xpath="//a[text()='Sign Out']")
private WebElement logoutLink;
//Business library
/**
 * This method is used to click on campaign tab in common page
 * @param webdriverUtility
 */
public void clickCampaign(WebDriverUtility webdriverUtility)
{
	webdriverUtility.initializeAction();
	webdriverUtility.mouseHoverOnElement(moreTab);
	campaignsTab.click();
}
/**
 * This method is used to logout and close the browser
 * @param webdriverUtility
 */
public void logoutAndCloseBrowser(WebDriverUtility webdriverUtility)
{
	webdriverUtility.mouseHoverOnElement(adminIcon);
	signOutLink.click();
	webdriverUtility.closeBrowser();
}
/**
 * This method is used to logout from the application
 * @param webdriverUtility
 */
public void logout(WebDriverUtility webdriverUtility)
{
	webdriverUtility.mouseHoverOnElement(adminIcon);
	logoutLink.click();
	
}
}