package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


public class LoginPage extends BasePage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");

    //методы возвращают ссылку на самого себя - те могут быть сигнатурами LoginPage
    public LoginPage open() {
        load();
        return this; //this- каждый из методов возвращает ссылку на данный объект
    }

    //для примера с параметризированными тестами
    public LoginPage open(String url) {//урл прописываем в xml файле
        load(url);
        return this;
    }

    public LoginPage verifyLoginPage() {
        Assert.assertTrue(waitVisibilityOfElements(username, password, loginBtn));
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

    public LoginPage enterUsername() {
        enter(this.username, properties.getProperty("username"));
        return this;
    }

    public LoginPage enterPassword() {
        enter(this.password,  properties.getProperty("password"));
        return this;
    }

    public LoginPage verifyThatLoginPageIsClosed() {
        Assert.assertTrue(elementNotExist(loginBtn));
        return this;
    }

    public LoginPage clickLoginBtn() {
        click(loginBtn);
        return this;
    }

    public LoginPage verifyErrorMessage() {
        Assert.assertEquals(getText(By.xpath("//h3[@data-test='error']")), "Epic sadface: Sorry, this user has been locked out.");
        return this;
    }

    //для dataProvider
    public LoginPage verifyErrorMessage(String errorMessage) {
        Assert.assertEquals(getText(By.xpath("//h3[@data-test='error']")), errorMessage);
        return this;
    }
}
