package pageFactory.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageFactory.saucedemo.entity.CheckoutBuilder;
import pageFactory.saucedemo.entity.Checkout_VObject;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class CheckoutOnePage extends BasePage {

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement zipCode;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(xpath = "//span[@class='title']")
    WebElement title;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement message;

    public CheckoutOnePage() {
        PageFactory.initElements(driver, this);
        verifyPageUri();
        verifyCheckoutOne();
    }

    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("checkout-step-one.html"));
    }

    public CheckoutOnePage verifyCheckoutOne() {
        Assert.assertTrue(waitVisibilityOfElements(firstName, lastName, zipCode, continueBtn));
        return this;
    }

    public CheckoutOnePage verifyPageTitle() {
        Assert.assertEquals(getText(title), "CHECKOUT: YOUR INFORMATION");
        return this;

    }

    public CheckoutOnePage enterFirstName(String firstName) {
        enter(this.firstName, firstName);
        return this;
    }

    public CheckoutOnePage enterLastName(String lastName) {
        enter(this.lastName, lastName);
        return this;
    }

    public CheckoutOnePage enterZipCode(String zipCode) {
        enter(this.zipCode, zipCode);
        return this;
    }

    public CheckoutOnePage enterData(Checkout_VObject checkout_vObject) {
        enterFirstName(checkout_vObject.getFirstName());
        enterLastName(checkout_vObject.getLastName());
        enterZipCode(checkout_vObject.getZipCode());
        return this;
    }

    public CheckoutOnePage enterData(CheckoutBuilder checkoutBuilder) {
        enterFirstName(checkoutBuilder.getFirstName());
        enterLastName(checkoutBuilder.getLastName());
        enterZipCode(checkoutBuilder.getZipCode());
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
