package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.Arrays;
import java.util.List;

import static driver.SimpleDriver.getWebDriver;

//описываем страницу с товарами
public class ProductPage extends BasePage {
    private final By title = By.xpath("//span[@class='title']");
    private final List<WebElement> getFilterOptions = getWebDriver().findElements(By.tagName("option"));
    private final By addToCartBtn = By.cssSelector("[id|=add-to-cart]");

    //тк uri уникальна для данной страницы, проверку можно вызвать при создании сущности
    public ProductPage() {
        verifyPageUri();
    }

    //метод, который позволяет обратиться к форме товара
    private WebElement getElementProduct(String productName) {
        return getWebDriver().findElement(By.xpath("//*[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
    }

    private WebElement getProductPrice(String productName) {
        return getElementProduct(productName).findElement(By.className("inventory_item_price"));
    }

    private WebElement getAddToCartBtn(String productName) {
        return getElementProduct(productName).findElement(By.tagName("button"));
    }

    //проверка Uri страницы
    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("inventory.html"));
    }


    public ProductPage verifyPageTitle() {
        Assert.assertEquals(getWebDriver().findElement(title).getText(), "PRODUCTS");
        return this;

    }

    public ProductPage verifyFilterOptions() {
        List<String> expectedData = Arrays.asList(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)");
        Assert.assertEquals(getText(getFilterOptions), expectedData);
        return this;
    }

    public ProductPage addProductToBasket(String productName) {
        click(getAddToCartBtn(productName));
        return this;
    }

    //для реализации с invocationCount
    public ProductPage addAllProductToBasket() {
        clickAll(addToCartBtn);
        return this;
    }

    public String getProductCost(String productName) {
        return getText(getProductPrice(productName));
    }
}
