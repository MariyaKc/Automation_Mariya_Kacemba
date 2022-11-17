package task_11_12_14;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.FooterPage;
import task_11_12_14.steps.LoginSteps;

public class SD_SocialMedia_Test extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        get(LoginSteps.class).login();
    }

    @Test(description = "Test go to Twittter in Footer page")
    public void TwitterTest() {
        get(FooterPage.class)
                .goToTwitter().switchToNewTab().verifyPageTwitter().returnToHomeTab().verifyNewTabIsClosed();
    }

    @Test(description = "Test go to Facebook in Footer page")
    public void FacebookTest() {
        get(FooterPage.class)
                .goToFacebook().switchToNewTab().verifyPageFacebook().returnToHomeTab().verifyNewTabIsClosed();
    }

    @Test(description = "Test go to Linkedin in Footer page")
    public void LinkedinTest() {
        get(FooterPage.class)
                .goToLinkedin().switchToNewTab().verifyPageLinkedin().returnToHomeTab().verifyNewTabIsClosed();
    }
}
