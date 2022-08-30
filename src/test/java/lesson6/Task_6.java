package lesson6;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import driver.SimpleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static driver.SimpleDriver.getWebDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

/**Залогиниться https://www.saucedemo.com/
 Добавить товар в корзину
 Перейти в корзину
 Проверить (assertEquals) стоимость товара и его имя в корзине
 Выполнить поиск локаторов*/

 public class Task_6 {

     @BeforeTest
    public void preconditions(){
         SimpleDriver simpleDriver = new SimpleDriver();
         getWebDriver().get("https://www.saucedemo.com/");

     }


     @Test (enabled = false)
    public void test(){
         getWebDriver().findElement(By.id("user-name")).sendKeys("standard_user");
         getWebDriver().findElement(By.name("password")).sendKeys("secret_sauce");
         getWebDriver().findElement(By.className("submit-button")).click();
         getWebDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
         getWebDriver().findElement(By.name("add-to-cart-sauce-labs-bike-light")).click();
         getWebDriver().findElement(By.cssSelector("[data-test|='add-to-cart-sauce-labs-bolt-t-shirt']")).click();

         getWebDriver().findElement(By.cssSelector("a.shopping_cart_link")).click();

         List<String> actualData = new ArrayList<>();
         List<WebElement> result = getWebDriver().findElements(By.cssSelector(".inventory_item_name, .inventory_item_price"));
         result.forEach(webElement -> {
             actualData.add(webElement.getText());
         });


         List<String> expectedData= new ArrayList<>() {{
             add("Sauce Labs Backpack");
             add("$29.99");
             add("Sauce Labs Bike Light");
             add("$9.99");
             add("Sauce Labs Bolt T-Shirt");
             add("$15.99");
         }};
         Assert.assertEquals(actualData, expectedData);
     }

     @Test
     public void testXpath(){
         getWebDriver().findElement(By.xpath("//input[@name='user-name']")).sendKeys("standard_user"); //по атрибуту
         getWebDriver().findElement(By.xpath( "//input[@id='user-name']/ancestor::div[@class='login-box']//*[@placeholder='Password']")).sendKeys("secret_sauce");//ancestor
         getWebDriver().findElement(By.xpath("//*[contains(@data-test, 'login-b')]")).click(); //по частичному совпадению атрибута

         getWebDriver().findElement(By.xpath("//div[@class='inventory_item_description']/descendant::div[@class='pricebar']//*[@data-test='add-to-cart-sauce-labs-backpack']")).click(); //descendant
         getWebDriver().findElement(By.xpath("//div[@class='inventory_item_description']/following::button[contains(@id, 'bike-light')]")).click();//following
         getWebDriver().findElement(By.xpath("//div[@class='inventory_item']/preceding::button[contains(@name,'labs-bolt-t-shirt')]")).click();//preceding
         getWebDriver().findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory' and @data-test='add-to-cart-sauce-labs-fleece-jacket']")).click();//and
         getWebDriver().findElement(By.xpath("//div[@class='inventory_item_description']/child::div[@class='pricebar']//button[@name='add-to-cart-sauce-labs-onesie']")).click();//child
         getWebDriver().findElement(By.xpath("//a[@id='item_3_title_link']/ancestor::div[@class='inventory_item_description']//*[contains(text(),'cart')]")).click();//ancestor

         getWebDriver().findElement(By.xpath("//*[@class='shopping_cart_link']")).click();

         List<String> actualData= new ArrayList<>();
         List<WebElement> result = getWebDriver().findElements(By.xpath("//div[@class='inventory_item_name' or @class='inventory_item_price']")); //or
         result.forEach(webElement1 -> {
             actualData.add(webElement1.getText());
         });


         List<String> expectedData = new ArrayList<>(){{
             add("Sauce Labs Backpack");
             add("$29.99");
             add("Sauce Labs Bike Light");
             add("$9.99");
             add("Sauce Labs Bolt T-Shirt");
             add("$15.99");
             add("Sauce Labs Fleece Jacket");
             add("$49.99");
             add("Sauce Labs Onesie");
             add("$7.99");
             add("Test.allTheThings() T-Shirt (Red)");
             add("$15.99");
         }};

         Assert.assertEquals(actualData, expectedData);
     }

    @Test
    public void testCssSelector() {
        getWebDriver().findElement(By.cssSelector("[data-test*=user]")).sendKeys("standard_user");
        getWebDriver().findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        getWebDriver().findElement(By.cssSelector(".submit-button.btn_action")).click();

        getWebDriver().findElement(By.cssSelector("[data-test$='backpack']")).click();
        getWebDriver().findElement(with(By.tagName("button")).below(By.id("item_1_title_link"))).click();
        getWebDriver().findElement(with(By.tagName("button")).toRightOf(By.id("item_4_title_link"))).click();
        getWebDriver().findElement(with(By.name("add-to-cart-sauce-labs-onesie")).toLeftOf(By.id("item_3_title_link"))).click();
        getWebDriver().findElement(with(By.tagName("button")).above(By.id("item_3_title_link"))).click();
        getWebDriver().findElement(By.cssSelector("[name$='shirt-(red)']")).click();

        getWebDriver().findElement(with(By.tagName("a")).near(By.cssSelector("[class='header_secondary_container']"))).click();

        List<String> actualData = new ArrayList<>();
        List<WebElement> result = getWebDriver().findElements(By.cssSelector(".inventory_item_name, .inventory_item_price"));
        result.forEach(webElement -> {
            actualData.add(webElement.getText());
        });

        List<String> expectedData = new ArrayList<>(){{
            add("Sauce Labs Backpack");
            add("$29.99");
            add("Sauce Labs Bolt T-Shirt");
            add("$15.99");
            add("Sauce Labs Bike Light");
            add("$9.99");
            add("Sauce Labs Onesie");
            add("$7.99");
            add("Sauce Labs Fleece Jacket");
            add("$49.99");
            add("Test.allTheThings() T-Shirt (Red)");
            add("$15.99");
        }};

        Assert.assertEquals(actualData, expectedData);
    }

    @AfterTest
    public void postconditions(){
         getWebDriver().close();
     }

}
