package task_11_12;

import io.qameta.allure.Step;
import org.testng.annotations.*;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.CheckoutCompletePage;
import pageObjects.saucedemo.CheckoutOnePage;
import pageObjects.saucedemo.CheckoutTwoPage;
import task_11_12.steps.LoginSteps;
import task_11_12.steps.ProductStep;

public class SD_Checkout_Test extends BaseTest {

    @BeforeMethod
    @Step("Login by user: {username} , {password} and navigate to product page")
    @Parameters({"url", "username", "password", "productName"})
    public void preconditions(String url, String username, String password, String productName){
        get(LoginSteps.class).login(url, username, password);
        get(ProductStep.class).product(productName);
    }

    @Test(dataProvider = "negative checkout data", description = "Test with negative checkout data")
    public void negativeCheckoutTest(String firstname, String lastname, String zipcode,String errorMessage) {
        get(BasketPage.class).verifyTitle().clickCheckout();
        get(CheckoutOnePage.class)
                .verifyPageTitle()
                .getFirstName(firstname)
                .getLastName(lastname)
                .getZipCode(zipcode)
                .clickContinueBtn()
                .verifyErrorMessage(errorMessage);
    }

    @Test( description = "Test with positive checkout data")
    public void positiveCheckoutTest( @Optional("first") String firstname, @Optional("last") String lastname, @Optional("12345") String zipcode) {
        get(BasketPage.class).verifyBasketPage().verifyTitle().clickCheckout();
        get(CheckoutOnePage.class)
                .verifyPageTitle()
                .getFirstName(firstname)
                .getLastName(lastname)
                .getZipCode(zipcode)
                .clickContinueBtn();
        get(CheckoutTwoPage.class).verifyPageTitle().clickFinishBtn();
        get(CheckoutCompletePage.class).verifyPageTitle().verifyFinalPageTitle();
    }

    @DataProvider(name = "negative checkout data")
    public Object[][] checkoutData() {
        return new Object[][]{
                {"", "LastName", "12345", "Error: First Name is required"},
                {"FirstName", "", "12345", "Error: Last Name is required"},
                {"FirstName","LastName", "", "Error: Postal Code is required"},
        };
    }
}
