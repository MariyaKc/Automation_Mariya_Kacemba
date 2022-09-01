package lesson7;

import com.fasterxml.jackson.databind.JsonSerializable;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductsPage;

public class Lesson7_2 extends BaseTest {

     @BeforeClass
     public void loginTest() {
         new LoginPage()
                 .open()
                 .enterUsername("standard_user")
                 .enterPassword("secret_sauce")
                 .clickLoginBtn();
     }


         @Test
    public void productTest() {
         String productName = "Sauce Labs Backpack";
         ProductsPage productPage = new ProductsPage();
         productPage.verifyPageTitle();
         String productCost = productPage.getProductCost(productName);
         productPage.addProductToBasket(productName);
         HeaderPage headerPage = new HeaderPage();
         headerPage.clickBasketBtn();
         BasketPage basketPage = new BasketPage();
         String cartProductCost = basketPage.getProductCost(productName);
         Assert.assertEquals(productCost, cartProductCost); //проверка эквивалентности цены
         Assert.assertEquals(basketPage.enterCartQuantity(productName), "1"); // проверка количества товара
    }
}
