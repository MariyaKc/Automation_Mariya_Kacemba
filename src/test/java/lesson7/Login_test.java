package lesson7;

import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

/**
 * 4 теста на логин со всеми пользователями
 */

public class Login_test extends BaseTest {
    @Test
    public void test1() {
        new LoginPage()
                .open()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn();
        new ProductPage()
                .verifyPageTitle()
                .verifyFilterOptions();
    }

    @Test
    public void test2() {
        new LoginPage()
                .open()
                .enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn()
                .verifyErrorMessage();
    }

    @Test
    public void test3() {
        new LoginPage()
                .open()
                .enterUsername("problem_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn();
        new ProductPage()
                .verifyPageTitle()
                .verifyFilterOptions();
    }

    @Test
    public void test4() {
        new LoginPage()
                .open()
                .enterUsername("performance_glitch_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn();
        new ProductPage()
                .verifyPageTitle()
                .verifyFilterOptions();
    }
}
