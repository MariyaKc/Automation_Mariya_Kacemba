package lesson7;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.*;

public class Checkout_test extends BaseTest {

    @BeforeClass
    public void login() {
        new LoginPage()
                .open()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginBtn();
        new ProductPage()
                .verifyPageTitle()
                .verifyFilterOptions()
                .verifyPageTitle()
                .addProductToBasket("Sauce Labs Backpack")
                .addProductToBasket("Sauce Labs Fleece Jacket")
                .addProductToBasket("Sauce Labs Onesie");
        new HeaderPage()
                .clickBasketBtn();
    }

        @Test
        public void test1(){
        new BasketPage()
                .verifyTitle()
                .clickCheckout();
        new CheckoutPage()
                .getFirstName("Firstname")
                .getLastName("LastName")
                .getZipCode("12345")
                .clickContinueBtn()
                .clickFinishBtn()
                .verifyFinalPageTitle();
    }
}
