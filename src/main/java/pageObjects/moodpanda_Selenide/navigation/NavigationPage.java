package pageObjects.moodpanda_Selenide.navigation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pageObjects.baseObjects.SelenideBasePage;

import static com.codeborne.selenide.Selenide.$;

public class NavigationPage extends SelenideBasePage {
    private final SelenideElement signUpBtn = $(By.partialLinkText("Sign up"));
    private final SelenideElement loginBtn = $(By.partialLinkText("Login"));
    private final SelenideElement title = $(By.xpath("//p[contains(@class,'title is-size-1')]"));

    public NavigationPage clickSignUp() {
        click(signUpBtn);
        return this;
    }

    public NavigationPage clickLogin() {
        click(loginBtn);
        return this;
    }

    public NavigationPage clickNavigationItem(LinksEnum links) {
        click(links.getElement());
        return this;
    }

    public NavigationPage verifyPageUri(String uri) {
        verifyUri(uri);
        return this;
    }

    public NavigationPage verifyTitle(String title) {
        if (this.title.exists() == true) {
            verifyText(this.title, title);
        }
        return this;
    }

    public NavigationPage goToItemAndVerify(LinksEnum sort) {
        switch (sort) {
            case Home:
                clickNavigationItem(LinksEnum.Home);
                verifyPageUri("moodpanda.com");
                verifyTitle("MoodPanda");
                break;
            case What:
                clickNavigationItem(LinksEnum.What);
                verifyPageUri("monitor-your-mood");
                verifyTitle("Monitor your mood");
                break;
            case How:
                clickNavigationItem(LinksEnum.How);
                verifyPageUri("how-to-use-a-mood-diary");
                verifyTitle("How to use a mood diary");
                break;
            case About:
                clickNavigationItem(LinksEnum.About);
                verifyPageUri("about");
                verifyTitle("About MoodPanda");
                break;
            case Contact:
                clickNavigationItem(LinksEnum.Contact);
                verifyPageUri("contact");
                break;
        }
        return this;
    }


    /**
     * for cucumber test
     */

    private SelenideElement getNavigationLink(String linkText) {
        return $(By.partialLinkText(linkText));
    }


    public NavigationPage clickNavigationItem(String linkText) {
        getNavigationLink(linkText).click();
        return this;
    }
}
