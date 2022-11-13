package task_11_12_14.steps;

import io.qameta.allure.Step;
import pageObjects.baseObjects.BasePage;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.ProductPage;

public class ProductStep extends BasePage {
    ProductPage productPage = new ProductPage();
    HeaderPage headerPage = new HeaderPage();

    @Step("Add a '{productName}' in the cart")
    public ProductStep addProductByName(String productName) {
        productPage.verifyFilterOptions().addProductToBasket(productName); // или лучше передавать напрямую, а не из проперти?
        headerPage.clickBasketBtn();
        return this;
    }

    @Step("Remove a '{productName}' to cart")
    public ProductStep removeProductByName(String productName) {
        new BasketPage().removeProduct(productName);
        return this;
    }

    @Step("Add {count} items to cart")
    public ProductStep addProductByCount(int count) {
        productPage.verifyFilterOptions().addProductToBasketForCount(count);
        headerPage.clickBasketBtn();
        return this;
    }

    @Step("Remove'{count}' to the cart")
    public ProductStep removeProductByCount(int count) {
        new BasketPage().removeProductForCount(count);
        return this;
    }

    @Step("Add all items to cart")
    public ProductStep addAllProduct() {
        productPage.verifyFilterOptions().addAllProductToBasket();
        headerPage.clickBasketBtn();
        return this;
    }

    @Step("Remove all items to cart")
    public ProductStep removeAllProduct() {
        new BasketPage().removeAllProduct();
        return this;
    }

}
