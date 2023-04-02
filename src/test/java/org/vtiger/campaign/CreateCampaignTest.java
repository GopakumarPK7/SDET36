package org.vtiger.campaign;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.genericUtility.BaseClass;
@Listeners(org.tyss.genericUtility.ListenerImplementation.class)
public class CreateCampaignTest extends BaseClass {
@Test
	public  void createCampaign()
	{
	
		System.out.println("Expected campaign name is="+expectedCampaignName);
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.)
		String actualCampaignName=campaignInformationPage.getActualcampaignName();
		Assert.fail();
		System.out.println("Actual campaign name is="+actualCampaignName);
		if(expectedCampaignName.equals(actualCampaignName))
		{
			System.out.println("Validation passed");
		}
		else
		{
			System.out.println("Validation failed");	
		}
		
	
	

}
}
