package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testbase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {



    @Test(groups = {"regression","master"})
    public void verify_account_registration(){
        logger.info("***** Starting TC001_AccountRegistrationTest  ****");
        try{
            HomePage homePage = new HomePage(driver);

        homePage.clickMyAccount();
        logger.info("Clicked on MyAccount Link.. ");
        homePage.clickRegister();
        logger.info("Clicked on Register Link.. ");

        AccountRegistrationPage accountRegistrationPage = new AccountRegistrationPage(driver);

        logger.info("Providing customer details...");
        accountRegistrationPage.setFirstName(randomeString().toUpperCase());
        accountRegistrationPage.setLastName(randomeString().toUpperCase());
        accountRegistrationPage.setEmail(randomeString()+"@test.com");
        accountRegistrationPage.setTelephone(randomeNumber());

        String password = randomAlphaNumeric();
        accountRegistrationPage.setPassword(password);
        accountRegistrationPage.setConfirmPassword(password);

        accountRegistrationPage.setPrivacyPolicy();
        accountRegistrationPage.clickContinue();
        logger.info("Validating expected message..");

        String confmsg = accountRegistrationPage.getConfirmationMsg();
        Assert.assertEquals(confmsg,"Your Account Has Been Created!","Confirmation message mismatch");
        logger.info("Test passed");

    }catch (Exception e){
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }finally {
            logger.info("***** Finished TC001_AccountRegistrationTest *****");
        }
        }





}
