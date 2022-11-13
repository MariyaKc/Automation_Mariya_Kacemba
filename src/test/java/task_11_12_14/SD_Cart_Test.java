package task_11_12_14;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.ProductPage;
import task_11_12_14.steps.LoginSteps;
import task_11_12_14.steps.ProductStep;


public class SD_Cart_Test extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        get(LoginSteps.class).login();
    }

    @Test(description = "Test add and remove all products")
    public void addRemoveAllProductsTest() {
        get(ProductStep.class).addAllProduct().removeAllProduct();
        get(HeaderPage.class).clickBasketBtn();
        get(BasketPage.class).verifyProductIsRemove();
    }

    @Test(description = "Test to add and remove products by name")
    public void addRemoveProductTest1() {
        get(ProductStep.class)
                .addProductByName(properties.getProperty("productName3"));
        get(BasketPage.class)
                .verifyQuantityProductInCart(properties.getProperty("productName3"))
                .clickContinueShopping();
        get(ProductStep.class)
                .addProductByName(properties.getProperty("productName2"));
        get(BasketPage.class)
                .removeProduct(properties.getProperty("productName3"))
                .removeProduct(properties.getProperty("productName2"))
                .verifyProductIsRemove();
    }

    @Test(description = "Test to add and remove products by count")
    public void addRemoveProductByCount() {
        get(ProductStep.class).addProductByCount(5);
        get(BasketPage.class).clickContinueShopping();
        get(ProductStep.class).addProductByCount(1).removeProductByCount(6);
        get(BasketPage.class).verifyProductIsRemove();
    }

    @Test(dataProvider = "product data", description = "Add and remove products with DataProvider test")
    public void addRemoveProductDataProviderTest(String name) {
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
