package lesson7;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

/**
 * 1 тест на добавление товара в корзину
 * 1 тест на удаление товара из корзины
 */

public class AddAndRemove_test extends BaseTest {

    @BeforeClass
    public void login() {
        new LoginPage()
                .open()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn();
    }

    @Test
    public void test1() {
        new ProductPage()
                .verifyPageTitle()
                .verifyFilterOptions()
                .verifyPageTitle()
                .addProductToBasket("Sauce Labs Backpack")
                .addProductToBasket("Sauce Labs Fleece Jacket")
                .addProductToBasket("Sauce Labs Onesie");
        new HeaderPage()
                .clickBasketBtn();
        new BasketPage()
                .verifyTitle()
                .verifyQuantityProductInCart("Sauce Labs Backpack")
                .verifyAllProductInCart()
                .removeProduct("Sauce Labs Backpack")
                .removeProduct("Sauce Labs Fleece Jacket")
                .removeProduct("Sauce Labs Onesie")
                .verifyProductIsRemove();
    }
}
