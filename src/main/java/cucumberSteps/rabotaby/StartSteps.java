package cucumberSteps.rabotaby;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.rabotaby_Selenide.StartPage;

public class StartSteps extends SelenideBaseTest {

    @Given("Open start page")
    public void openStartPage() {
        get(StartPage.class);
    }

    @When("I'm confirm with search region")
    public void confirmSearch() {
        get(StartPage.class).confirmRegion();
    }

    @When("I'm click change search region")
    public void changeSearch() {
        get(StartPage.class).changeRegion();
    }

    @And("I'm click on region item {string}")
    public void clickOnMenuItem(String string) {
        get(StartPage.class).clickNavigationItem(string);
    }

    @And("I'm enter search data {string}")
    public void enterSearchData(String data) {
        get(StartPage.class).enterSearch(data);
    }

    @And("I'm click search button")
    public void clickSearch() {
        get(StartPage.class).clickSearch();
    }
}
