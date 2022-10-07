package pageObjects.moodpanda;


import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends MoodPandaBasePage {

    private By email = By.cssSelector("[type='text']");
    private By password = By.cssSelector("[type='password']");
    private By loginBtn = By.xpath("//button[@class]");
    private By notification = By.cssSelector("[class^=notification]");

    public LoginPage() { //можно не реализовывать этот паттерн, а добавлять проверки на загрузку страницы в крнструктор
        isPageOpened();
    }

    public LoginPage enterPassword(String password) {
        enter(this.password, password);
        return this;
    }

    public LoginPage enterEmail(String email) {
        enter(this.email, email);
        return this;
    }

    public LoginPage clickLogin() {
        click(loginBtn);
        return this;
    }

    public LoginPage verifyErrorMessage(String message) {
        Assert.assertEquals(getText(notification), message);
        return this;
    }

    @Override
    public void isPageOpened() {
        waitVisibilityOfElement(email);
    }
}
