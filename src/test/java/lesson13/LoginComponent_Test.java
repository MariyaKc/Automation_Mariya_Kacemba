package lesson13;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.baseObjects.BaseTest;
import pageObjects.moodpanda.HomePage;
import pageObjects.moodpanda.LoginPage;

public class LoginComponent_Test extends BaseTest {

    @Parameters({"url", "email", "password"})
    @Test
    public void login_Test(String url, String email, String password) {
        new HomePage()
                .open(url)
                .clickGetStarted();
        new LoginPage()
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .verifyErrorMessage("Your email or password is wrong");
    }
}
