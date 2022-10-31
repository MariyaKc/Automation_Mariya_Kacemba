package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.SelenideBasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class HomePage extends SelenideBasePage {
    private final SelenideElement getStartedBtn = $(By.partialLinkText("Get started"));
    private final SelenideElement startImg = $("[alt^='MoodPanda home']");
    private final SelenideElement title = $(".is-size-1");
    private final SelenideElement signUpForFreeBtn = $(By.linkText(" Sign up for free"));
    private final SelenideElement appStoreBtn = $(By.xpath("//img[ contains(@alt, 'Apple')]"));
    private final SelenideElement googlePlayBtn = $(By.xpath("//img[ contains(@alt, 'Google')]"));
    private final String handleHomeTab = getWebDriver().getWindowHandle();
    private final SelenideElement storeTitle = $(By.tagName("h1"));

    public HomePage() {
        verifyPageUrl();
        verifyStartImg();
    }

    public HomePage verifyPageUrl() {
        verifyUri("moodpanda.com");
        return this;
    }

    public HomePage verifyTitle(){
        verifyText(this.title, "MoodPanda");
        return this;
    }

    public HomePage verifyStartImg(){
        this.startImg.shouldBe(Condition.exist);
        return this;
    }

    public HomePage clickGetStarted(){
        click(getStartedBtn);
        return this;
    }

    public HomePage signUpForFree(){
       click(signUpForFreeBtn);
        return this;
    }

    public HomePage clickGoToAppStore(){
        click(appStoreBtn);
        return this;
    }

    public HomePage clickGToGooglePlay(){
       click(googlePlayBtn);
        return this;
    }
}
