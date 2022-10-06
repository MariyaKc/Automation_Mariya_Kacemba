package lesson11;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;

public class Login_Test extends BaseTest {

    @Parameters({"username", "password"})
    @Test
    public void login(String username, String password) {
        get(LoginPage.class)
                .open()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginBtn()
                .verifyThatLoginPageIsClosed();
    }
}
