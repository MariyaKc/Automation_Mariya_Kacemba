package lesson9;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

/**Написать тесты на Логин страницу - 1 позитивный и 3 негативных (DataProvider)*/

public class SauceDemoLogin_test extends BaseTest {

    @Parameters("url")
    @BeforeMethod
    public void preconditions(String url) {
        new LoginPage()
                .open(url);
    }

    @Test(dataProvider = "login data")
    public void LoginTest(String username, String password, String errorMessage) {
        new LoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginBtn();
        if(errorMessage.isEmpty()){
            new ProductPage()
                    .verifyPageTitle()
                    .verifyFilterOptions();
        }else{
            new LoginPage()
                    .verifyErrorMessage(errorMessage);
        }
    }

    @DataProvider(name = "login data")
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", ""},
                {"standard_user", "12345", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"performance_glitch_user","","Epic sadface: Password is required"}
        };
    }
}
