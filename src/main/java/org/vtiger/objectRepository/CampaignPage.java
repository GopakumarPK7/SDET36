package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage 
{
public CampaignPage(WebDriver driver)
{
	PageFactory.initElements(driver,this);
}
@FindBy(xpath="//img[@title='Create Campaign...']")
private WebElement CreateCampaignBtn;

//Business library
/**
 * This method is used to click on create campaign button
 * @return 
 */
public void ClickOnCreateCampaignBtn()
{
	CreateCampaignBtn.click();	
}
}
