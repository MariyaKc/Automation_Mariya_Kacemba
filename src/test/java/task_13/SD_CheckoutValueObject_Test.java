package task_13;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.saucedemo.*;
import pageFactory.saucedemo.entity.Checkout_VObject;
import pageFactory.saucedemo.entity.Login_VObject;
import pageObjects.baseObjects.BaseTest;

public class SD_CheckoutValueObject_Test extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    @Parameters("url")
    public void precondition(String url){
        loginPage= new LoginPage();
        loginPage.open(url);
    }

    @Test(description = "Test (Value Object) with standard user data {username}, {password}")
    @Parameters({"username", "password", "firstname", "lastname", "zipcode"})
    public void CheckoutTest(String username, String password, String firstname, String lastname, String zipcode){

        Login_VObject login_VObject = new Login_VObject(){{
            setUsername(username);
            setPassword(password);
        }};

        loginPage.enterData(login_VObject).clickLoginBtn();
        new ProductPage()
                .verifyFilterOptions()
                .selectByName("Name (A to Z)").VerifySortNameAtoZ()
                .addAllProductToBasket();
        new HeaderPage().clickBasketBtn();
        new BasketPage().clickCheckout();

        Checkout_VObject checkout_vObject  = new Checkout_VObject() {{
            setFirstName(firstname);
            setLastName(lastname);
            setZipCode(zipcode);
        }};

        new CheckoutOnePage().enterData(checkout_vObject).clickContinueBtn();
        new CheckoutTwoPage().clickFinishBtn();
        new CheckoutCompletePage().verifyCheckoutCompletePage();
    }
}
