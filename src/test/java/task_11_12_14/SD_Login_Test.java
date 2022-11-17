package task_11_12_14;

import io.qameta.allure.Link;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

/**
 * Lesson11 - Добавить интеграцию с Allure Report в любой из используемых проектов (SauceDemo)
 * Использовать аннотации:
 *
 * @Step
 * @Attachment
 * @Test(description=””)
 */

public class SD_Login_Test extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        get(LoginPage.class)
                .open();
    }

    @Link("https://www.saucedemo.com/")
    @Test(description = "Test with all user data", dataProvider = "user data")
    public void loginTest(String username, String password, String errorMessage) {
        get(LoginPage.class)
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginBtn();
        if (errorMessage.isEmpty()) {
            get(LoginPage.class).verifyThatLoginPageIsClosed();
            get(ProductPage.class)
                    .verifyPageTitle()
                    .verifyFilterOptions();
        } else {
            get(LoginPage.class)
                    .verifyErrorMessage(errorMessage);
        }
    }

    @Link("https://www.saucedemo.com/")
    @Test(description = "Test with negative user data", dataProvider = "negative user data")
    public void negativeLoginTest(String username, String password, String errorMessage) {
        get(LoginPage.class)
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginBtn()
                .verifyErrorMessage(errorMessage);
    }

    /**
     * В DataProvider данные записывать так же или лучше закинуть креды всех пользователей в saucedemo.properties
     * и забирать отуда ?
     *
     * @DataProvider(name = "name")
     * public Object [][] dat(){
     * return new Object[][] {
     * {properties.getProperty("username"), properties.getProperty("password"), properties.getProperty("error")},
     * {.......},
     * };
     * }
     */

    @DataProvider(name = "user data")
    public Object[][] data() {
        return new Object[][]{
                {"standard_user", "secret_sauce", ""},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"problem_user", "secret_sauce", ""},
                {"performance_glitch_user", "secret_sauce", ""},
        };
    }

    @DataProvider(name = "negative user data")
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "12345", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"", "", "Epic sadface: Username is required"},
                {"performance_glitch_user", "", "Epic sadface: Password is required"}
        };
    }
}
