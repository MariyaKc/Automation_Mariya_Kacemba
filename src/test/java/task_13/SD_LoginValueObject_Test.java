package task_13;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.saucedemo.LoginPage;
import pageFactory.saucedemo.ProductPage;
import pageFactory.saucedemo.entity.Login_VObject;
import pageObjects.baseObjects.BaseTest;

/**
 * Implementation of patterns Page Factory, Chain of invocation, Value Object, Data Provider, Loadable Page
 */

public class SD_LoginValueObject_Test extends BaseTest {
    LoginPage loginPage;
    Login_VObject login_VObject;

    @BeforeMethod
    @Parameters("url")
    public void precondition(String url) {
        loginPage = new LoginPage();
        loginPage.open(url);
    }

    @Test(description = "Test(Value Object) with standard user data {username}, {password}")
    @Parameters({"username", "password"})
    public void standardUserTest(String username, String password) {

        login_VObject = new Login_VObject() {{
            setUsername(username);
            setPassword(password);
        }};
        loginPage.enterData(login_VObject).clickLoginBtn();
        new ProductPage().verifyProductPageIsOpened();
    }

    @Test(description = "Login test (Value Object Pattern) with all user data :: {username}, {password}", dataProvider = "user data")
    public void AllUserLoginTest(String username, String password, String errorMessage) {

        login_VObject = new Login_VObject() {{
            setUsername(username);
            setPassword(password);
        }};
        loginPage.enterData(login_VObject).clickLoginBtn();
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
