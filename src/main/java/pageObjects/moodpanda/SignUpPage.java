package pageObjects.moodpanda;

import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;
import pageObjects.moodpanda.entity.SignUp;
import pageObjects.moodpanda.entity.SignUpBuilder;

public class SignUpPage extends BasePage {

    private By firstName = By.cssSelector("[placeholder='Your first name']");
    private By lastNameInitial = By.cssSelector("[placeholder=\"e.g. 'S'\"]");
    private By email = By.cssSelector("[placeholder='Your email address']");
    private By password = By.cssSelector("[type='password']");
    private By checkbox = By.cssSelector("[type='checkbox']");
    private By signUpBtn = By.xpath("//*[@class='box']//button");


    public SignUpPage enterFirstName(String firstName) {
        enter(this.firstName, firstName);
        return this;
    }

    public SignUpPage enterLastNameInitial(String lastNameInitial) {
        enter(this.lastNameInitial, lastNameInitial);
        return this;
    }

    public SignUpPage enterEmail(String email) {
        enter(this.email, email);
        return this;
    }

    public SignUpPage enterPassword(String password) {
        enter(this.password, password);
        return this;
    }

    public SignUpPage clickCheckbox() {
        click(checkbox);
        return this;
    }

    public SignUpPage clickSignUp() {
        click(signUpBtn);
        return this;
    }

    //общий метод который вызывает все взаимодействия
    public SignUpPage enterData(String firstName, String lastName, String email, String password, Boolean checkbox) {
        enterFirstName(firstName);
        enterLastNameInitial(lastName);
        enterEmail(email);
        enterPassword(password);
        if (checkbox) clickCheckbox();
        clickSignUp();
        return this;
    }

    public SignUpPage enterData(SignUp signUp) {
        enterFirstName(signUp.getFirstName());
        enterLastNameInitial(signUp.getLastName());
        enterEmail(signUp.getEmail());
        enterPassword(signUp.getPassword());
        if (signUp.getCheckbox()) clickCheckbox();
        clickSignUp();
        return this;
    }

    public SignUpPage enterData(SignUpBuilder signUp) {
        enterFirstName(signUp.getFirstName());
        enterLastNameInitial(signUp.getLastName());
        enterEmail(signUp.getEmail());
        enterPassword(signUp.getPassword());
        if (signUp.getCheckbox()) clickCheckbox();
        clickSignUp();
        return this;
    }
}
