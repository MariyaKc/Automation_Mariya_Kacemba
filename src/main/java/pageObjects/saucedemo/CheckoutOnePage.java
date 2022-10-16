package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class CheckoutOnePage extends BasePage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By title = By.xpath("//span[@class='title']");
    private final By message = By.xpath("//h3[@data-test='error']");

    public CheckoutOnePage() {
        verifyPageUri();
        verifyCheckoutOne();
    }

    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("checkout-step-one.html"));
    }

    public CheckoutOnePage verifyCheckoutOne() {
        Assert.assertTrue(waitVisibilityOfElements(firstName, lastName, zipCode,continueBtn));
        return this;
    }
    public CheckoutOnePage verifyPageTitle() {
        Assert.assertEquals(getText(title), "CHECKOUT: YOUR INFORMATION");
        return this;

    }

    public CheckoutOnePage getFirstName(String firstName) {
        enter(this.firstName, firstName);
        return this;
    }

    public CheckoutOnePage getLastName(String lastName) {
        enter(this.lastName, lastName);
        return this;
    }

    public CheckoutOnePage getZipCode(String zipCode) {
        enter(this.zipCode, zipCode);
        return this;
    }

    public CheckoutOnePage clickContinueBtn() {
        click(continueBtn);
        return this;
    }

    //для dataProvider
    public CheckoutOnePage verifyErrorMessage(String errorMessage) {
        Assert.assertEquals(getText(message), errorMessage);
        return this;
    }
}
