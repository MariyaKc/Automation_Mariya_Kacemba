package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObjects.baseObjects.SelenideBasePage;


import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends SelenideBasePage {

    private final SelenideElement email = $("[type='text']");
    private final SelenideElement password = $("[type='password']");
    private final SelenideElement loginBtn = $(By.xpath("//button[@class]"));
    private final SelenideElement emailAlert = $(By.xpath("//div[contains(text(), 'email')]"));
    private final SelenideElement passwordAlert = $(By.xpath("//div[contains(text(), 'Password')]"));
    private final SelenideElement validationAlert = $(By.xpath(" //div[contains(text(), 'validation')]"));
    private final SelenideElement dontAccountBtn = $(By.partialLinkText("Don't have"));
    private final SelenideElement forgotBtn = $(By.partialLinkText("Forgot password"));
    private final SelenideElement cantLoginBtn = $(By.partialLinkText("I can't login"));

    private final SelenideElement notification = $(By.cssSelector("[class^=notification]"));


    public LoginPage() {
        verifyPageUri();
    }
    public LoginPage verifyPageUri() {
        verifyUri("login");
        return this;
    }

    public LoginPage enterEmail(String email) {
        enter(this.email, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enter(this.password, password);
        return this;
    }

    public LoginPage clickLogin() {
       click(loginBtn);
        return this;
    }

    public LoginPage clickDontAccount() {
       click(dontAccountBtn);
        return this;
    }

    public LoginPage clickForgotPassword() {
       click(forgotBtn);
        return this;
    }

    public LoginPage clickCantLogin() {
       click(cantLoginBtn);
        return this;
    }

    public LoginPage verify() {
        click(cantLoginBtn);
        return this;
    }

    public LoginPage verifyEmailAlert(String errorMessage) {
        verifyAlert(this.emailAlert, errorMessage);
        return this;
    }

    public LoginPage verifyPasswordAlert(String errorMessage) {
        verifyAlert(this.passwordAlert, errorMessage);
        return this;
    }

    public LoginPage verifyValidationAlert(String errorMessage) {
        verifyAlert(this.validationAlert, errorMessage);
        return this;
    }

    /** for cucumber test */
    public LoginPage verifyErrorMessage(String message) {
        notification.should(Condition.text(message));
        return this;
    }
}
