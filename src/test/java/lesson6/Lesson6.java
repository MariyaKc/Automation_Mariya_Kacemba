package lesson6;

import driver.SimpleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static driver.SimpleDriver.getWebDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Lesson6 {
    @BeforeTest
    public void preconditions() {
        SimpleDriver simpleDriver = new SimpleDriver();
        getWebDriver().get("https://www.saucedemo.com/");
    }

    @Test(enabled = false)
    public void testForLesson7() {
        String productName = "Sauce Labs Backpack";
        getWebDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getWebDriver().findElement(By.name("password")).sendKeys("secret_sauce");
        getWebDriver().findElement(By.xpath("//input[@type = 'submit']")).click();
        String price = getElementProduct(productName).findElement(By.className("inventory_item_price")).getText();//ищем цену внутри элемента и получаем текст
        getElementProduct(productName).findElement(By.tagName("button")).click(); //поиск элемента(button) внутри другого элемента
        System.out.println(price);
        getWebDriver().findElement(By.className("shopping_cart_link")).click();
        String cartPrice = getElementCartItem(productName).findElement(By.className("inventory_item_price")).getText();
        Assert.assertEquals(price, cartPrice);
    }


    //методы, который будет возвращать веб элемент(получаем название товара в каталоге, второй метод - в списке)
    private WebElement getElementProduct(String productName) {
        return getWebDriver().findElement(By.xpath("//*[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']")); //через разделение строки передаем название товара
    }

    private WebElement getElementCartItem(String productName) {
        return getWebDriver().findElement(By.xpath("//*[@class = 'inventory_item_name' and text() = '" + productName + "']//ancestor::div[@class='cart_item']"));
    }


    @Test(enabled = false)
    public void authTest() {
        getWebDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getWebDriver().findElement(By.name("password")).sendKeys("secret_sauce");
        getWebDriver().findElement(By.id("login-button")).click();
        elementExist(By.linkText("Facebook"));
        elementExist(By.partialLinkText("Twi"));
        elementExist(By.className("app_logo"));
    }

    @Test(enabled = false)
    public void aboveTest() {
        System.out.println(getWebDriver().findElements(with(By.tagName("input")).near(By.name("password"))).size());
        System.out.println(getWebDriver().findElements(with(By.tagName("input")).near(By.name("password"), 60)).size());
        getWebDriver().findElement(with(By.tagName("input")).above(By.name("password"))).sendKeys("above");
        getWebDriver().findElement(with(By.tagName("input")).below(By.name("password"))).click();
        System.out.println(getWebDriver().findElement(with(By.tagName("h4")).toLeftOf(By.className("login_password"))).getText());
        System.out.println(getWebDriver().findElement(with(By.tagName("h4")).toRightOf(By.className("login_credentials"))).getText());
    }

    private void elementExist(By by) {
        Assert.assertEquals(getWebDriver().findElements(by).size(), 1);
    }
}

