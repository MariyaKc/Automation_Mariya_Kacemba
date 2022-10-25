package pageObjects.rabotaby_Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.SelenideBasePage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;


public class SearchPage extends SelenideBasePage {
    private final SelenideElement headerNameOfSearch = $(By.tagName("h1"));
    private final SelenideElement headerCountOfSearch = $("[data-qa='vacancies-total-found']");

    public SearchPage() {
        verifyPageUrl();
    }

    public SearchPage verifyPageUrl() {
        webdriver().shouldHave(urlContaining("vacancies"));
        return this;
    }

    public SearchPage verifySearchCriteria(String text) {
        this.headerNameOfSearch.shouldBe(Condition.partialText(text));
        System.out.println("I'm text :: " + headerNameOfSearch.getText());
        return this;
    }

    public SearchPage verifySearchCount(int countForFeature) {
        if (headerCountOfSearch.getText().isEmpty()==false) {
        int count = Integer.parseInt(headerCountOfSearch.getText().substring(1, 4).trim());
            Assert.assertTrue(count >= countForFeature, "The number of vacancies is less than transferred from the feature file");
            System.out.println("I'm count :: " + count);
        } else {
            System.out.println("Vacancies not found");
        }
        return this;
    }
}
