package lesson8;

import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

public class Lesson8_1 extends BaseTest {
    @Test
    public void login() {

        new LoginPage()
                .open()
                .enterUsername("performance_glitch_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn()
                .verifyThatLoginPageIsClosed();
        new ProductPage().verifyPageTitle();
    }
}
