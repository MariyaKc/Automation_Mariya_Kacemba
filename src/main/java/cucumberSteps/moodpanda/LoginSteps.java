package cucumberSteps.moodpanda;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.LoginPage;

public class LoginSteps extends SelenideBaseTest {

    @When("i enter email {string}, password {string}")
    public void enterCreads(String email, String password) {
        get(LoginPage.class).enterEmail(email).enterPassword(password);
    }

    @And("i click login button")
    public void clickLoginButton() {
        get(LoginPage.class).clickLogin();
    }

    @Then("i check error message {string}")
    public void checkError(String message) {
        get(LoginPage.class).verifyErrorMessage(message);
    }
}
