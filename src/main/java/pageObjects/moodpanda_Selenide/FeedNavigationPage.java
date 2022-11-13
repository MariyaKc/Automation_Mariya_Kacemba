package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObjects.baseObjects.SelenideBasePage;

import static com.codeborne.selenide.Selenide.$;


public class FeedNavigationPage extends SelenideBasePage {
    private final SelenideElement title = $(By.xpath("//div[contains(@class,'content-pane')]/child::div[contains(@class, 'is-hidden-touch')]//p[@class='subtitle']"));
    private final SelenideElement homeBtn = $(By.partialLinkText("Home"));
    private final SelenideElement diaryBtn = $(By.partialLinkText("Your diary"));
    private final SelenideElement dashboardBtn = $(By.partialLinkText("Your dashboard"));
    private final SelenideElement patronsBtn = $(By.partialLinkText("Patrons"));
    private final SelenideElement logoutFirstBtn = $(By.partialLinkText("Logout First"));

    public FeedNavigationPage() {
        verifyPageUri();
    }

    public FeedNavigationPage verifyPageUri() {
        verifyUri("/feed/global");
        return this;
    }

    public FeedNavigationPage verifyPageTitle() {
        verifyText(this.title, "This is a community. Be kind.");
        return this;
    }

    public FeedNavigationPage clickHome() {
        click(homeBtn);
        return this;
    }

    public FeedNavigationPage clickDiary() {
        click(diaryBtn);
        return this;
    }

    public FeedNavigationPage clickDashboard() {
        click(dashboardBtn);
        return this;
    }

    public FeedNavigationPage clickPatron() {
        click(patronsBtn);
        return this;
    }

    public FeedNavigationPage clickLogout() {
        click(logoutFirstBtn);
        return this;
    }

}
