package task_11_12_14.steps;

import io.qameta.allure.Step;
import pageObjects.baseObjects.BasePage;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.lombok.User;

public class LoginSteps extends BasePage {
    LoginPage loginPage = new LoginPage();

    @Step("Login by user: {username} , {password}")
    public LoginSteps login(){
        loginPage.open();

        User user = new User.UserBuilder()
                .withUsername(properties.getProperty("username"))
                .withPassword(properties.getProperty("password"))
                .create();

        loginPage.enterData(user).clickLoginBtn().verifyThatLoginPageIsClosed();
        return this;
    }
}
