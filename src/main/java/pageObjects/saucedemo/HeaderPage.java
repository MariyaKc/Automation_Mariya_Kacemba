package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.DriverManager.getDriver;

public class HeaderPage extends BasePage {
    private final By basketBtn = By.className("shopping_cart_link");
    private final By navigationBtn = By.id("react-burger-menu-btn");
    private final By allItemsBtn = By.id("inventory_sidebar_link");
    private final By aboutBtn = By.id("about_sidebar_link");
    private final By logoutBtn = By.id("logout_sidebar_link");
    private final By resetAppBtn = By.id("reset_sidebar_link");

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
        Assert.assertTrue(getDriver().getCurrentUrl().equals("https://saucelabs.com/"));
        return this;
    }

    public HeaderPage returnBack() {
        driver.navigate().back();
        return this;
    }
}

