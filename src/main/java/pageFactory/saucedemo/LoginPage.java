package pageFactory.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageFactory.saucedemo.entity.LoginBuilder;
import pageFactory.saucedemo.entity.Login_VObject;
import pageObjects.baseObjects.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement error;


    public LoginPage() {
        PageFactory.initElements(driver, this);
    }


    public LoginPage open(String url) {
        load(url);
        verifyLoginPageIsOpened();
        return this;
    }

    public LoginPage verifyLoginPageIsOpened() {
        waitVisibilityOfElements(username, password, loginBtn);
        return this;
    }

    public LoginPage enterUsername(String username) {
        enter(this.username, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enter(this.password, password);
        return this;
    }

    public LoginPage enterData(Login_VObject login_vObject) {
        enterUsername(login_vObject.getUsername());
        enterPassword(login_vObject.getPassword());
        return this;
    }

    public LoginPage enterData(LoginBuilder loginBuilder) {
        enterUsername(loginBuilder.getUsername());
        enterPassword(loginBuilder.getPassword());
        return this;
    }

    public LoginPage clickLoginBtn() {
        click(loginBtn);
        return this;
    }

    public LoginPage verifyErrorMessage() {
        Assert.assertEquals(getText(error), "Epic sadface: Sorry, this user has been locked out.");
        return this;
    }

    //для dataProvider
    public LoginPage verifyErrorMessage(String errorMessage) {
        Assert.assertEquals(getText(error), errorMessage);
        return this;
    }
}

