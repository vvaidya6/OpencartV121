package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testbase.BaseClass;

public class TC_004_SearchProductTest extends BaseClass {

	@Test(groups= {"Master"})
	public void verify_pruductSearch() throws InterruptedException {
		logger.info(" Starting TC_004_SearchProductTest ");

		try {

			HomePage hm=new HomePage(driver);

			//hm.enterProductName("iPhone");
			hm.enterProductName("mac");

			hm.clickSearch();

			SearchPage sp=new SearchPage(driver);
			sp.isProductExist("MacBook");

			Assert.assertEquals(sp.isProductExist("MacBook"),true);

		} catch (Exception e) {
			Assert.fail();
		}

		logger.info(" Finished TC_004_SearchProductTest ");

	}
}
