package cucumberSteps.moodpanda;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.navigation.NavigationPage;

public class NavigationSteps extends SelenideBaseTest {

    @Given("open home page")
    public void openHomePage() {
        get(NavigationPage.class);
    }

    @When("i click on menu item {string}")
    public void clickOnMenuItem(String string) {
        get(NavigationPage.class).clickNavigationItem(string);
    }

    @Then("i check that uri is {string}")
    public void checkPageUri(String uri) {
        get(NavigationPage.class).verifyPageUri(uri);
    }
}
