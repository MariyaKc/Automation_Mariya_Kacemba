package lesson13;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.baseObjects.BaseTest;
import pageObjects.moodpanda.HomePage;
import pageObjects.moodpanda.LoginPage;

public class LoginComponent_Test extends BaseTest {

    @Test
    public void login_Test() {
        new HomePage()
                .open()
                .clickGetStarted();
        new LoginPage()
                .enterEmail(properties.getProperty("email"))
                .enterPassword(properties.getProperty("password"))
                .clickLogin()
                .verifyErrorMessage("Your email or password is wrong");
    }
}
