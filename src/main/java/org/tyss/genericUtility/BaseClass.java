package org.tyss.genericUtility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.vtiger.objectRepository.CampaignInformationPage;
import org.vtiger.objectRepository.CampaignPage;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.CreateNewCampaignPage;
import org.vtiger.objectRepository.LoginPage;

public class BaseClass extends InstanceClass
{
	public static WebDriver listenerdriver;
@BeforeSuite
public void suiteSetup()
{
	fileUtility=new FileUtility();
	javaUtility=new JavaUtility();
    excelUtility=new ExcelUtility();
	webDriverUtility=new WebDriverUtility();
	fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
	excelUtility.initilizeExcelFile(IConstants.VTIGEREXCELFILEPATH);
	browser=fileUtility.getDataFromProperty("browser");
	url=fileUtility.getDataFromProperty("url");
	userName=fileUtility.getDataFromProperty("username");
	password=fileUtility.getDataFromProperty("password");
	timeouts=fileUtility.getDataFromProperty("timeout");
	expectedCampaignName=excelUtility.getDataFromExcel("Campaign", 2, 1)+num;
	longTimeouts=Long.parseLong(timeouts);

}
@BeforeClass
public void classSetup()
{
	WebDriver driver=webDriverUtility.setupDriver(browser);
	listenerdriver=driver;
	webDriverUtility.maximizeBrowser();
	webDriverUtility.implicitWait(longTimeouts);
	webDriverUtility.openApplication(url);
	loginPage=new LoginPage(driver);
	commonPage=new CommonPage(driver);
	campaignPage=new CampaignPage(driver);
	createNewCampaignPage=new CreateNewCampaignPage(driver);
	campaignInformationPage=new CampaignInformationPage(driver);
}
@BeforeMethod
public void methodSetup()
{
	num=javaUtility.getRandomNumber();
	loginPage.loginAction(userName, password);
	commonPage.clickCampaign(webDriverUtility);
	campaignPage.ClickOnCreateCampaignBtn();
	createNewCampaignPage.createCampaign(expectedCampaignName);
}
@AfterMethod
public void methodTearDown()
{
	commonPage.logout(webDriverUtility);
}
@AfterClass
public void classTearDown()
{
	webDriverUtility.closeBrowser();
}
}
