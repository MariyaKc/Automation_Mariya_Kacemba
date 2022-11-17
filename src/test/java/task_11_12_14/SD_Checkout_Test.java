package task_11_12_14;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.*;
import pageObjects.saucedemo.lombok.Checkout;
import task_11_12_14.steps.LoginSteps;
import task_11_12_14.steps.ProductStep;

import java.util.List;

public class SD_Checkout_Test extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        get(LoginSteps.class).login();
    }

    @Test(description = "Test with positive checkout data")
    public void positiveCheckoutTest() {
        get(ProductStep.class).addProductByCount(4);

        List<String> basketList = get(BasketPage.class).getListProductInCart();

        get(BasketPage.class).clickCheckout();

        Checkout checkout = new Checkout.CheckoutBuilder()
                .withFirstName(properties.getProperty("firstname"))
                .withLastName(properties.getProperty("lastname"))
                .withZipCode(properties.getProperty("zipcode")).create();

        get(CheckoutOnePage.class).enterData(checkout).clickContinueBtn();

        List<String> checkoutList = new CheckoutTwoPage().getProductListInCheckout();
        Assert.assertEquals(basketList, checkoutList);

        get(CheckoutTwoPage.class).verifyItemTotalPrice().verifyTotalPrice().clickFinishBtn();
        get(CheckoutCompletePage.class).verifyFinalPageTitle();
    }

    @Test(dataProvider = "negative checkout data", description = "Test with negative checkout data")
    public void negativeCheckoutTest(String firstname, String lastname, String zipcode, String errorMessage) {
        get(ProductStep.class).addProductByCount(4);
        get(BasketPage.class).clickCheckout();
        get(CheckoutOnePage.class)
                .enterFirstName(firstname)
                .enterLastName(lastname)
                .enterZipCode(zipcode)
                .clickContinueBtn()
                .verifyErrorMessage(errorMessage);
    }

    @AfterMethod
    public void postcondition() {
        get(HeaderPage.class).clickNavigationBtn().clickResetAppBtn();
    }


    @DataProvider(name = "negative checkout data")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"", "LastName", "12345", "Error: First Name is required"},
                {"FirstName", "", "12345", "Error: Last Name is required"},
                {"FirstName", "LastName", "", "Error: Postal Code is required"},
        };
    }
}
