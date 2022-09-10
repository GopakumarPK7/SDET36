package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage 
{
public CreateNewCampaignPage(WebDriver driver)
{
	PageFactory.initElements(driver,this);
}
@FindBy(xpath="//input[@name='campaignname']")
private WebElement campaignNameTextField;
@FindBy(xpath="//input[@type='submit']")
private WebElement saveBtn;

//Business library
/**
 * This method is used to enter campaign name and click on save button
 * @param expectedCampaignName
 */
public void createCampaign(String expectedCampaignName)
{
	campaignNameTextField.sendKeys(expectedCampaignName);
	saveBtn.click();
}
}
