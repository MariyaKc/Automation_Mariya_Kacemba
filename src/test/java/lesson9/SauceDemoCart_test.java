package lesson9;

import org.testng.annotations.*;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;
import testNgUtils.Listener;

@Listeners(Listener.class)
public class SauceDemoCart_test extends BaseTest {

    @Parameters("url")
    @BeforeClass
    public void preconditions(String url) {
        new LoginPage()
                .open(url)
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn();
        new ProductPage()
                .verifyPageTitle()
                .verifyFilterOptions();
    }

    /** Написать тесты на создание/редактирование и удаление продуктов (invocationCount) */

    @Test(invocationCount = 3, priority = 1,description = "Test add product with invocationCount")
    public void addProductsTest1(){
        new ProductPage()
                .addAllProductToBasket();
        new HeaderPage()
                .clickBasketBtn();
        new BasketPage()
                .verifyTitle()
                .removeAllProduct()
                .verifyProductIsRemove()
                .clickContinueShopping();
    }

    /** Написать тесты на создание/редактирование и удаление продуктов с использованием DataProvider */
    @Test(dataProvider = "product data", priority = 2, description = "Test ad product with DataProvider", dependsOnMethods ="addProductsTest1")
    public void addProductsTest2(String name){
        new ProductPage()
                .addProductToBasket(name);
        new HeaderPage()
                .clickBasketBtn();
        new BasketPage()
                .verifyTitle()
                .removeProduct(name)
                .verifyProductIsRemove()
                .clickContinueShopping();
    }

    @DataProvider(name = "product data")
    public Object[][] getData(){
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }
}
