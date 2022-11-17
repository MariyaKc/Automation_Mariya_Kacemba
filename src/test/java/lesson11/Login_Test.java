package lesson11;

import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;

public class Login_Test extends BaseTest {

    @Test
    public void login() {
        get(LoginPage.class)
                .open()
                .enterUsername()
                .enterPassword()
                .clickLoginBtn()
                .verifyThatLoginPageIsClosed();
    }
}
