package task_13;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.saucedemo.*;
import pageFactory.saucedemo.entity.CheckoutBuilder;
import pageFactory.saucedemo.entity.LoginBuilder;
import pageObjects.baseObjects.BaseTest;

public class SD_CheckoutBuilder_Test extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    @Parameters("url")
    public void precondition(String url) {
        loginPage = new LoginPage();
        loginPage.open(url);
    }

    @Test(description = "Test (Builder Pattern) :: with standard user data {username}, {password}")
    @Parameters({"username", "password", "firstname", "lastname", "zipcode"})
    public void checkoutTest(String username, String password, String firstname, String lastname, String zipcode) {

        LoginBuilder loginBuilder = new LoginBuilder.Builder()
                .withUsername(username)
                .withPassword(password)
                .build();

        loginPage.enterData(loginBuilder).clickLoginBtn();
        new ProductPage()
                .verifyFilterOptions()
                .selectByName("Price (low to high)").VerifySortPriceLowToHigh()
                .addAllProductToBasket();
        new HeaderPage().clickBasketBtn();
        new BasketPage().clickCheckout();

        CheckoutBuilder checkoutBuilder = new CheckoutBuilder.Builder()
                .withFirstName(firstname)
                .withLastName(lastname)
                .withZipCode(zipcode)
                .build();

        new CheckoutOnePage().enterData(checkoutBuilder).clickContinueBtn();
        new CheckoutTwoPage().clickFinishBtn();
        new CheckoutCompletePage().verifyCheckoutCompletePage();
    }
}

