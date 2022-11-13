package pageFactory.saucedemo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class CheckoutCompletePage extends BasePage {

    @FindBy(id = "back-to-products")
    private WebElement backHomeBtn;

    @FindBy(className = "complete-header")
    private WebElement finishTitle;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement title;

    public CheckoutCompletePage() { /** Loadable Page pattern */
        PageFactory.initElements(driver, this);
        verifyPageUri();
        verifyCheckoutCompletePage();
    }

    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("checkout-complete.html"));
    }

    public CheckoutCompletePage verifyCheckoutCompletePage() {
        waitVisibilityOfElements(backHomeBtn, finishTitle);
        return this;
    }

    public CheckoutCompletePage verifyPageTitle() {
        Assert.assertEquals(getText(title), "CHECKOUT: COMPLETE!");
        return this;
    }

    public CheckoutCompletePage verifyFinalPageTitle() {
        Assert.assertEquals(getText(finishTitle), "THANK YOU FOR YOUR ORDER");
        return this;
    }

    public CheckoutCompletePage clickBackHome() {
        click(backHomeBtn);
        return this;
    }
}
