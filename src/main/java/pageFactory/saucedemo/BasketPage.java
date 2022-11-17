package pageFactory.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.List;

import static driver.SimpleDriver.getWebDriver;

public class BasketPage extends BasePage {

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingBtn;

    @FindBy(xpath = "//span[@class='title']")
    WebElement title;

    @FindBys({
            @FindBy(css = ".inventory_item_name")
    })
    List<WebElement> allProductName;

    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    WebElement removeBtn;

    public BasketPage() {
        PageFactory.initElements(driver, this);
        verifyPageUri();  /** Loadable Page pattern */
        verifyBasketPage();
    }

    private WebElement getElementCartItem(String productName) {
        return getWebDriver().findElement(By.xpath("//*[@class = 'inventory_item_name' and text() = '" + productName + "']//ancestor::div[@class='cart_item']"));
    }

    private WebElement getElementProductCost(String productName) {
        return getElementCartItem(productName).findElement(By.className("inventory_item_price"));
    }

    private WebElement getElementCartQuantity(String productName) {
        return getElementCartItem(productName).findElement(By.className("cart_quantity"));
    }

    private WebElement getRemoveInCartBtn(String productName) {
        return getElementCartItem(productName).findElement(By.cssSelector(".btn.btn_secondary.btn_small.cart_button"));
    }

    public String getProductCost(String productName) {
        return getText(getElementProductCost(productName));
    }

    public String enterCartQuantity(String productName) {
        return getText(getElementCartQuantity(productName));
    }

    public BasketPage verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("cart.html"));
        return this;
    }

    public BasketPage verifyBasketPage() {
        Assert.assertTrue(waitVisibilityOfElements(continueShoppingBtn, checkoutBtn));
        return this;
    }

    public BasketPage verifyTitle() {
        Assert.assertEquals(getText(title), "YOUR CART");
        return this;
    }

    public BasketPage verifyQuantityProductInCart(String productName) {
        Assert.assertEquals(enterCartQuantity(productName), "1");
        return this;
    }

    public BasketPage removeProduct(String productName) {
        click(getRemoveInCartBtn(productName));
        return this;
    }

    public BasketPage removeAllProduct() { /** Chain of Invocations pattern */
        clickAll(removeBtn);
        return this;
    }

    public BasketPage clickCheckout() {
        click(checkoutBtn);
        return this;
    }

    public BasketPage clickContinueShopping() {
        click(continueShoppingBtn);
        return this;
    }

    public BasketPage verifyProductIsRemove() {
        fluentWait(20, 1).until(driver -> ExpectedConditions.not(ExpectedConditions.visibilityOfAllElements(allProductName)));
        return this;
    }
}
