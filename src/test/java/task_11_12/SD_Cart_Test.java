package task_11_12;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.ProductPage;
import task_11_12.steps.LoginSteps;


public class SD_Cart_Test extends BaseTest {

    @BeforeMethod
    @Step("Login by user: {username} , {password}")
    @Parameters({"url", "username", "password"})
    public void preconditions(String url, String username, String password){
        get(LoginSteps.class).login(url, username, password);
    }

    @Test(description = "Test add and remove all products")
    public void addRemoveAllProductsTest() {
        get(ProductPage.class).addAllProductToBasket();
        get(HeaderPage.class).clickBasketBtn();
        get(BasketPage.class)
                .verifyTitle()
                .removeAllProduct()
                .verifyProductIsRemove();
    }

    @Test(description = "Test to add and remove products by name")
    public void addRemoveProductTest1(){
        get(ProductPage.class).addProductToBasket("Sauce Labs Bolt T-Shirt");
        get(HeaderPage.class).clickBasketBtn();
        get(BasketPage.class)
                .verifyTitle().verifyQuantityProductInCart("Sauce Labs Bolt T-Shirt")
                .clickContinueShopping();
        get(ProductPage.class).addProductToBasket("Sauce Labs Bike Light");
        get(HeaderPage.class).clickBasketBtn();
        get(BasketPage.class)
                .removeProduct("Sauce Labs Bike Light")
                .removeProduct("Sauce Labs Bolt T-Shirt")
                .verifyProductIsRemove();
    }

    @Test(dataProvider = "product data", description = "Add and remove products with DataProvider test")
    public void addRemoveProductTest2(String name) {
        get(ProductPage.class).addProductToBasket(name);
        get(HeaderPage.class).clickBasketBtn();
        get(BasketPage.class)
                .verifyTitle()
                .removeProduct(name)
                .verifyProductIsRemove()
                .clickContinueShopping();
    }

    @DataProvider(name = "product data")
    public Object[][] getData() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }
}
