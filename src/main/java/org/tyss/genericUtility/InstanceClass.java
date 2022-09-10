package org.tyss.genericUtility;

import org.openqa.selenium.WebDriver;
import org.vtiger.objectRepository.CampaignInformationPage;
import org.vtiger.objectRepository.CampaignPage;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.CreateNewCampaignPage;
import org.vtiger.objectRepository.LoginPage;

public class InstanceClass 
{
	public FileUtility fileUtility;
	public JavaUtility javaUtility;
	public WebDriver driver;
	public ExcelUtility excelUtility;
	public WebDriverUtility webDriverUtility;
	protected String browser;
	protected String url;
	protected String userName;
	protected String password;
	protected String timeouts;
	protected long longTimeouts;
	protected int num;
	public LoginPage loginPage;
	public CommonPage commonPage;
	public CampaignPage campaignPage;
	public CreateNewCampaignPage createNewCampaignPage;
	public CampaignInformationPage campaignInformationPage;
	protected String expectedCampaignName;
}
