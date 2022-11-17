package pageFactory.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class HeaderPage extends BasePage {

    @FindBy(className = "shopping_cart_link")
    WebElement basketBtn;

    @FindBy(id = "react-burger-menu-btn")
    WebElement navigationBtn;

    @FindBy(id = "inventory_sidebar_link")
    WebElement allItemsBtn;

    @FindBy(id = "about_sidebar_link")
    WebElement aboutBtn;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutBtn;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppBtn;

    public HeaderPage() {
        PageFactory.initElements(driver, this);
    }

    public HeaderPage clickBasketBtn() {
        click(basketBtn);
        return this;
    }

    public HeaderPage clickNavigationBtn() {
        click(navigationBtn);
        return this;
    }

    public HeaderPage clickAllItemsBtn() {
        click(allItemsBtn);
        return this;
    }

    public HeaderPage clickAboutBtn() {
        click(aboutBtn);
        return this;
    }

    public HeaderPage clickLogoutBtn() {
        click(logoutBtn);
        return this;
    }

    public HeaderPage clickResetAppBtn() {
        click(resetAppBtn);
        return this;
    }

    public HeaderPage verifyAboutPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().equals("https://saucelabs.com/"));
        return this;
    }

    public HeaderPage returnBack() {
        driver.navigate().back();
        return this;
    }
}
