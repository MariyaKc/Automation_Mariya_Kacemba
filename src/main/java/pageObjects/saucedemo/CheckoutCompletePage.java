package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By backHomeBtn = By.id("back-to-products");
    private final By finishTitle = By.className("complete-header");
    private final By title = By.xpath("//span[@class='title']");

    public CheckoutCompletePage() {
        verifyPageUri();
        verifyCheckoutCompletePage();
    }

    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("checkout-complete.html"));
    }

    public CheckoutCompletePage verifyCheckoutCompletePage() {
        Assert.assertTrue(waitVisibilityOfElements(backHomeBtn, finishTitle));
        return this;
    }

    public CheckoutCompletePage verifyPageTitle() {
        Assert.assertEquals(getText(title), "CHECKOUT: COMPLETE!");
        return this;

    }

    public  CheckoutCompletePage verifyFinalPageTitle() {
        Assert.assertEquals(getWebDriver().findElement(finishTitle).getText(), "THANK YOU FOR YOUR ORDER");
        return this;
    }
    public CheckoutCompletePage clickBackHome() {
        click(backHomeBtn);
        return this;
    }

}
