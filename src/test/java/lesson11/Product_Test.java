package lesson11;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.TmsLink;
import jdk.jfr.Description;
import lesson11.steps.LoginSteps;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.ProductPage;

public class Product_Test extends BaseTest {
    @Parameters({"username", "password", "steps"})
    @Test
    @Description("Product test")
    @Step("Login and check product page")
    @Link("https://test.com")
    @Issue("Product Page issue")
    @TmsLink("Product Page tms")
    public void productTest(String username, String password, @Optional("0") String steps) {
        if (steps.equals("0")) get(LoginSteps.class).login(username, password);
        get(ProductPage.class).verifyPageTitle();
    }
}
