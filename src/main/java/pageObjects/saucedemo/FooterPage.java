package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class FooterPage extends BasePage {

    public final By twitterBtn = By.cssSelector(".social_twitter");
    public final By facebookBtn = By.cssSelector(".social_facebook");
    public final By linkedinBtn = By.cssSelector(".social_linkedin");

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
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("saucelabs"));
        return this;
    }

    public FooterPage verifyPageFacebook() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("saucelabs"));
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Sauce Labs");
        return this;
    }

    public FooterPage verifyPageLinkedin() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("linkedin.com"),"You must be logged in Linkedin to view this page.");
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Присоединитесь к LinkedIn");
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
