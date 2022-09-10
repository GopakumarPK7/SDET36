package org.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
@FindBy(xpath="//input[@name='user_name']")
private WebElement userNameTextField;
@FindBy(xpath="//input[@name='user_password']")
private WebElement passwordTextField;
@FindBy(xpath="//input[@type='submit']")
private WebElement loginBtn;

public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver,this);
}

//Business library
/**
 * This method is used to login to the application
 * @param userName
 * @param password
 */

public void loginAction(String userName,String password)
{
	userNameTextField.sendKeys(userName);	
	passwordTextField.sendKeys(password);
	loginBtn.click();
}

}
