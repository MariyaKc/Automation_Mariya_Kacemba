package pageFactory.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class CheckoutTwoPage extends BasePage {

    @FindBy(id = "finish")
    WebElement finishBtn;

    @FindBy(id = "cancel")
    WebElement cancelBtn;

    @FindBy(xpath = "//span[@class='title']")
    WebElement title;

    public CheckoutTwoPage() {
        PageFactory.initElements(driver, this);
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
