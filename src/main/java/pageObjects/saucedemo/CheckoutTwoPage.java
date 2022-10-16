package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class CheckoutTwoPage extends BasePage {
    private final By finishBtn = By.id("finish");
    private final By cancelBtn = By.id("cancel");
    private final By title = By.xpath("//span[@class='title']");

    public CheckoutTwoPage() {
        verifyPageUri();
        verifyCheckoutTwo();
    }

    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("checkout-step-two.html"));
    }

    public CheckoutTwoPage verifyCheckoutTwo() {
        Assert.assertTrue(waitVisibilityOfElements(finishBtn, cancelBtn));
        return this;
    }
    public CheckoutTwoPage verifyPageTitle() {
        Assert.assertEquals(getText(title), "CHECKOUT: OVERVIEW");
        return this;
    }


    public CheckoutTwoPage clickFinishBtn() {
        click(finishBtn);
        return this;
    }

    public CheckoutTwoPage clickCancelBtn() {
        click(cancelBtn);
        return this;
    }
}
