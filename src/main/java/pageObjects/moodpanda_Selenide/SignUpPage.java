package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObjects.baseObjects.SelenideBasePage;
import pageObjects.moodpanda_Selenide.lombok.SignUp;

import static com.codeborne.selenide.Selenide.$;


public class SignUpPage extends SelenideBasePage {
    private final SelenideElement firstName = $("[placeholder='Your first name']");
    private final SelenideElement lastNameInitial = $("[placeholder=\"e.g. 'S'\"]");
    private final SelenideElement email = $("[placeholder='Your email address']");
    private final SelenideElement password = $("[type='password']");
    private final SelenideElement checkbox = $("[type='checkbox']");
    private final SelenideElement signUpBtn = $(By.xpath("//*[@class='box']//button"));
    private final SelenideElement signMeUpBtn = $(By.xpath("//*[@class='box']//span[contains(text(),'Sign me up')]"));

    private final SelenideElement informText = $(By.xpath("//div/small"));
    private final SelenideElement alertOver16 = $("[class$='small']");
    private final SelenideElement alertValidation = $("[class$='notification is-danger']");
    private final SelenideElement alertFiledRequired = $("[class$='is-small']");

    public SignUpPage() {
        verifyPageUri();
    }

    public SignUpPage verifyPageUri() {
        verifyUri("signup");
        return this;
    }

    public SignUpPage enterFirstName(String firstname) {
        enter(this.firstName, firstname);
        return this;
    }

    public SignUpPage enterLastNameInitial(String lastname) {
        enter(this.lastNameInitial, lastname);
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

    public SignUpPage enterData(SignUp signUp) {
        enterFirstName(signUp.getFirstName());
        enterLastNameInitial(signUp.getLastName());
        enterEmail(signUp.getEmail());
        enterPassword(signUp.getPassword());
        if ((signUp.getCheckbox() == true & checkbox.isSelected() == false) || (signUp.getCheckbox() == false & checkbox.isSelected() == true)) {
            checkbox.click();
        }
        signUpBtn.click();
        return this;
    }

    public SignUpPage clickSignUpMe() {
        click(signMeUpBtn);
        return this;
    }

    public SignUpPage checkAlertIfCheckboxUncheck() {
        verifyAlert(this.alertOver16, "You must be over 16 and agree to our terms to use MoodPanda.");
        return this;
    }

    public SignUpPage checkAlertValidation(String alert) {
        verifyAlert(this.alertValidation, alert);
        return this;
    }

    public SignUpPage checkAlertFiledRequired(String alert) {
        verifyAlert(this.alertFiledRequired, alert);
        return this;
    }

    public SignUpPage checkInformingText() {
        verifyText(this.informText, "We use essential cookies to allow users to login.");
        return this;
    }
}
