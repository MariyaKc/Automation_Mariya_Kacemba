package lesson7;

import driver.SimpleDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductsPage;

public class Lesson7_1 extends BaseTest {

    @Test
    public void loginTest1(){
        //авторизация
        LoginPage loginPage = new LoginPage(); //объект класса LoginPage
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
        // проверка, что мы попали на страницу с продуктами
        ProductsPage productsPage = new ProductsPage();
        productsPage.verifyPageTitle();
    }


}
