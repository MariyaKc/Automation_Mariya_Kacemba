package task_11_12;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.ProductPage;
import task_11_12.steps.LoginSteps;

public class SD_Filter_Test extends BaseTest {

    @Step("Login by user: {username} , {password}")
    @Parameters({"url", "username", "password"})
    @Test(description = "Sorting of products test")
    public void SortFilterTest(String url, String username, String password){

        get(LoginSteps.class).login(url, username, password);

        get(ProductPage.class)
                .clickFilterBtn()
                .selectByName("Name (A to Z)").VerifySortNameAtoZ()
                .selectByName("Name (Z to A)").VerifySortNameZtoA()
                .selectByName("Price (low to high)").VerifySortPriceLowToHigh()
                .selectByName("Price (high to low)").VerifySortPriceHighToLow();
    }
}
