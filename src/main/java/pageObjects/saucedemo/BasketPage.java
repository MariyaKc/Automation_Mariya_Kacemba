package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.Arrays;
import java.util.List;

import static driver.SimpleDriver.getWebDriver;

public class BasketPage extends BasePage {

    private final By checkoutBtn = By.id("checkout");
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By title = By.xpath("//span[@class='title']");
    private final By allProductName = By.cssSelector(".inventory_item_name");
    private final By removeBtn = By.xpath("//button[contains(text(),'Remove')]");

    public BasketPage() {
        verifyPageUri();
        verifyBasketPage();
    }

    private WebElement getElementCartItem(String productName) { //3 - формируется элемент на уровне productName
        return getWebDriver().findElement(By.xpath("//*[@class = 'inventory_item_name' and text() = '" + productName + "']//ancestor::div[@class='cart_item']"));
    }

    private WebElement getElementProductCost(String productName) { // 2 - передается productName, затем обращение к родительскому getElementCartItem
        return getElementCartItem(productName).findElement(By.className("inventory_item_price")); //4-от родительского формируется элемент inventory_item_price
    }

    private WebElement getElementCartQuantity(String productName) {
        return getElementCartItem(productName).findElement(By.className("cart_quantity"));
    }

    private WebElement getRemoveInCartBtn(String productName) {
        return getElementCartItem(productName).findElement(By.cssSelector(".btn.btn_secondary.btn_small.cart_button"));
    }

    public String getProductCost(String productName) {  //1- приходит productName
        return getText(getElementProductCost(productName)); //6- целый элемент передается в метод getText
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

    public BasketPage removeAllProduct() {
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

    public BasketPage verifyAllProductInCart() {
        Assert.assertEquals(getWebDriver().findElements(By.className("inventory_item_name")).size(), 3);
        return this;
    }


    public BasketPage verifyProductIsRemove() {
        fluentWait(20, 1).until(driver -> ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(allProductName)));
        return this;
    }
}
