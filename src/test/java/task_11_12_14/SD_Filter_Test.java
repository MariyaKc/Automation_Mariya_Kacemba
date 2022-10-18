package task_11_12_14;

import io.qameta.allure.Step;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.ProductPage;
import task_11_12_14.steps.LoginSteps;

public class SD_Filter_Test extends BaseTest {

    @Test(description = "Sorting of products test")
    public void SortFilterTest(){

        get(LoginSteps.class).login();

        get(ProductPage.class)
                .clickFilterBtn()
                .selectByName("Name (A to Z)").VerifySortNameAtoZ()
                .selectByName("Name (Z to A)").VerifySortNameZtoA()
                .selectByName("Price (low to high)").VerifySortPriceLowToHigh()
                .selectByName("Price (high to low)").VerifySortPriceHighToLow();
    }
}
