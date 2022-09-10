package org.vtiger.campaign;

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
