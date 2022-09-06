package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class CheckoutPage extends BasePage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By cancelBtn = By.id("cancel");
    private final By backHomeBtn = By.id("back-to-products");
    private final By finishTitle = By.className("complete-header");

    public CheckoutPage getFirstName(String firstName) {
        enter(this.firstName, firstName);
        return this;
    }

    public CheckoutPage getLastName(String lastName) {
        enter(this.lastName, lastName);
        return this;
    }

    public CheckoutPage getZipCode(String zipCode) {
        enter(this.zipCode, zipCode);
        return this;
    }

    public CheckoutPage clickContinueBtn() {
        click(continueBtn);
        return this;
    }

    public CheckoutPage clickFinishBtn() {
        click(finishBtn);
        return this;
    }

    public CheckoutPage clickCancelBtn() {
        click(cancelBtn);
        return this;
    }

    public CheckoutPage clickBackHome() {
        click(backHomeBtn);
        return this;
    }

    public CheckoutPage verifyFinalPageTitle() {
        Assert.assertEquals(getWebDriver().findElement(finishTitle).getText(), "THANK YOU FOR YOUR ORDER");
        return this;
    }
}
