package task_13;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.saucedemo.LoginPage;
import pageFactory.saucedemo.ProductPage;
import pageFactory.saucedemo.entity.LoginBuilder;
import pageObjects.baseObjects.BaseTest;

public class SD_LoginBuilder_Test extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    @Parameters("url")
    public void precondition(String url) {
        loginPage = new LoginPage();
        loginPage.open(url);
    }

    @Test(description = "Test(Builder Pattern) with standard user data {username}, {password}")
    @Parameters({"username", "password"})
    public void standardUserTest(String username, String password) {

        LoginBuilder loginBuilder = new LoginBuilder.Builder()
                .withUsername(username)
                .withPassword(password).build();

        loginPage.enterData(loginBuilder).clickLoginBtn();
        new ProductPage().verifyProductPageIsOpened();
    }

    @Test(description = "Login test (Builder Pattern) with all user data :: {username}, {password}", dataProvider = "user data")
    public void AllUserLoginTest(String username, String password, String errorMessage) {

        LoginBuilder loginBuilder = new LoginBuilder.Builder()
                .withUsername(username)
                .withPassword(password)
                .build();

        loginPage.enterData(loginBuilder).clickLoginBtn();
        if (errorMessage.isEmpty()) {
            new ProductPage().verifyProductPageIsOpened();
        } else {
            loginPage.verifyErrorMessage(errorMessage);
        }
    }

    @DataProvider(name = "user data")
    public Object[][] data() {
        return new Object[][]{
                {"standard_user", "secret_sauce", ""},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"problem_user", "secret_sauce", ""},
                {"performance_glitch_user", "secret_sauce", ""},
        };
    }
}
