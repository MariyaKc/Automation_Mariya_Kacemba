package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BasketPage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");

   //методы возвращают ссылку на самого себя - те могут быть сигнатурами LoginPage
    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return this; //this- каждый из методов возвращает ссылку на данный объект
    }

    public LoginPage enterUsername(String username) {
        enter(this.username, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enter(this.password, password);
        return this;
    }

    public LoginPage verifyThatLoginPageIsClosed(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public LoginPage clickLoginBtn() {
        click(loginBtn);
        return this;
    }
}
