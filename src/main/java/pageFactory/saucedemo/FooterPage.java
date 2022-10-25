package pageFactory.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class FooterPage extends BasePage {

    @FindBy (how = How.CSS,using = ".social_twitter")
    WebElement twitterBtn;

    @FindBy (how = How.CSS,using = ".social_facebook")
    WebElement facebookBtn;

    @FindBy (how = How.CSS,using = ".social_linkedin")
    WebElement linkedinBtn;

    @FindBy (how = How.TAG_NAME,using = ".h1")
    WebElement title;

    private final String handleHomeTab = driver.getWindowHandle();

    public FooterPage(){
        PageFactory.initElements(driver, this);
    }

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
        Assert.assertEquals(getText(title), "Sauce Labs");
        return this;
    }

    public FooterPage verifyPageLinkedin() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("linkedin.com"));
        Assert.assertEquals(getText(title), "Присоединитесь к LinkedIn");
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
