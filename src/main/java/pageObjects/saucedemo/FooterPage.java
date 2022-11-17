package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.DriverManager.getDriver;

public class FooterPage extends BasePage {

    public final By twitterBtn = By.cssSelector(".social_twitter");
    public final By facebookBtn = By.cssSelector(".social_facebook");
    public final By linkedinBtn = By.cssSelector(".social_linkedin");
    public final By title = By.tagName("h1");

    private final String handleHomeTab = driver.getWindowHandle(); //текущий дескриптор

    public FooterPage goToTwitter() {
        click(twitterBtn);
        return this;
    }

    public FooterPage goToFacebook() {
        click(facebookBtn);
        return this;
    }

    public FooterPage goToLinkedin() {
        click(linkedinBtn);
        return this;
    }

    public FooterPage switchToNewTab() {
        driver.getWindowHandles().forEach(newHandle ->
                driver.switchTo().window(newHandle));
        return this;
    }

    public FooterPage verifyPageTwitter() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("saucelabs"));
        return this;
    }

    public FooterPage verifyPageFacebook() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("saucelabs"));
        Assert.assertEquals(getText(title), "Sauce Labs");
        return this;
    }

    public FooterPage verifyPageLinkedin() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("linkedin.com"), "You must be logged in Linkedin to view this page.");
        Assert.assertEquals(getText(title), "Join LinkedIn");
        return this;
    }

    public FooterPage returnToHomeTab() {
        driver.close();
        driver.switchTo().window(handleHomeTab);
        return this;
    }

    public FooterPage verifyNewTabIsClosed() {
        Assert.assertTrue(driver.getWindowHandles().size() == 1);
        return this;
    }
}
