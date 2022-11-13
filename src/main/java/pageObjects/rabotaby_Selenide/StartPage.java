package pageObjects.rabotaby_Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObjects.baseObjects.SelenideBasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class StartPage extends SelenideBasePage {

    private final SelenideElement searchBtn = $(By.xpath("//button[contains(@data-qa, 'search')]"));
    private final SelenideElement searchFiled = $("#a11y-search-input");
    private final SelenideElement notification = $(".bloko-notification__plate");
    private final SelenideElement confirmRegionBtn = $("[data-qa='region-clarification-confirm']");
    private final SelenideElement changeRegionBtn = $("[class='supernova-navi-item_area-switcher-button']");

    public StartPage() {
        verifyPageUrl();
    }

    public StartPage verifyPageUrl() {
        webdriver().shouldHave(urlContaining("rabota.by"));
        return this;
    }

    public StartPage clickSearch() {
        click(searchBtn);
        return this;
    }

    public StartPage confirmRegion() {
        if (notification.exists()) {
            click(confirmRegionBtn);
        }
        return this;
    }

    public StartPage changeRegion() {
        if (notification.exists()) {
            click(changeRegionBtn);
        }
        return this;
    }

    private SelenideElement getNavigationLink(String linkText) {
        return $(By.partialLinkText(linkText));
    }

    public StartPage clickNavigationItem(String linkText) {
        getNavigationLink(linkText).click();
        return this;
    }

    public StartPage enterSearch(String text) {
        enter(this.searchFiled, text);
        return this;
    }
}
