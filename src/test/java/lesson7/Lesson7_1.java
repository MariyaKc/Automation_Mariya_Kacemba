package lesson7;

import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

public class Lesson7_1 extends BaseTest {

    @Test
    public void loginTest1() {
        //авторизация
        LoginPage loginPage = new LoginPage(); //объект класса LoginPage
        loginPage.open("https://www.saucedemo.com/");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
        // проверка, что мы попали на страницу с продуктами
        ProductPage productPage = new ProductPage();
        productPage.verifyPageTitle();
    }


}
