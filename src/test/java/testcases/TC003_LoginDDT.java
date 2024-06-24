package testcases;

/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testbase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "datadriven") //getting data provider from different class
    public void verify_loginDDT(String email, String password, String exp){

        logger.info("**** Starting TC003_LoginDDT *****");
        try {
            //Home page
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("clicked on myaccount link on the home page..");
            hp.clickLogin(); //Login link under MyAccount
            logger.info("clicked on login link under myaccount..");

            //Login page
            LoginPage lp = new LoginPage(driver);
            logger.info("Entering valid email and password..");
            lp.setEmail(email);
            lp.setPassword(password);
            lp.clickLogin(); //Login button
            logger.info("clicked on login button..");


            //My Account page
            MyAccountPage map = new MyAccountPage(driver);
            logger.info("verify my account page is exists");
            boolean targetPage = map.isMyAccountPageExists();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage == true) {
                    map.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {
                    map.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (Exception e){
            Assert.fail("An exception occurred: " + e.getMessage());
        }
        logger.info("**** Finished TC003_LoginDDT *****");

    }

}
