package pageFactory.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.Arrays;
import java.util.List;

import static driver.SimpleDriver.getWebDriver;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//span[@class='title']")
    WebElement title;

    @FindBys({
            @FindBy(tagName = "option")
    })
    List<WebElement> getFilterOptions;

    @FindBy(css = "[id|=add-to-cart]")
    WebElement addToCartBtn;

    @FindBy(css = ".app_logo")
    WebElement logo;

    @FindBy(className = "product_sort_container")
    WebElement filterBtn;

    @FindBys({
            @FindBy(className = "inventory_item_name")
    })
    List<WebElement> allProducts;

    @FindBys({
            @FindBy(className = "inventory_item_price")
    })
    List<WebElement> allProductPrices;

    @FindBy(className = "product_sort_container")
    WebElement productSort;

    public ProductPage() {
        PageFactory.initElements(driver, this);
        verifyPageUri();
        verifyProductPageIsOpened();
    }

    public ProductPage verifyProductPageIsOpened() {
        waitVisibilityOfElements(addToCartBtn, logo);
        return this;
    }

    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("inventory.html"));
    }

    private WebElement getElementProduct(String productName) {
        return getWebDriver().findElement(By.xpath("//*[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
    }

    private WebElement getProductPrice(String productName) {
        return getElementProduct(productName).findElement(By.className("inventory_item_price"));
    }

    private WebElement getAddToCartBtn(String productName) {
        return getElementProduct(productName).findElement(By.tagName("button"));
    }

    public ProductPage verifyPageTitle() {
        Assert.assertEquals(getText(title), "PRODUCTS");
        return this;
    }

    public ProductPage verifyFilterOptions() {
        List<String> expectedData = Arrays.asList(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)");
        Assert.assertEquals(getTexts(getFilterOptions), expectedData);
        return this;
    }

    public ProductPage addProductToBasket(String productName) {
        click(getAddToCartBtn(productName));
        return this;
    }

    public ProductPage addAllProductToBasket() {
        clickAll(addToCartBtn);
        return this;
    }

    public ProductPage clickFilterBtn() {
        click(filterBtn);
        return this;
    }

    public ProductPage selectByName(String value) {
        select(productSort, value);
        return this;
    }

    public String getProductCost(String productName) {
        return getText(getProductPrice(productName));
    }

    public ProductPage VerifySortNameAtoZ() {
        Assert.assertEquals(getTexts(allProducts), getSortAscendingByTexts(allProducts));
        return this;
    }

    public ProductPage VerifySortNameZtoA() {
        Assert.assertEquals(getTexts(allProducts), getSortDescendingByTexts(allProducts));
        return this;
    }

    public ProductPage VerifySortPriceLowToHigh() {
        Assert.assertEquals(getValues(allProductPrices), getSortAscendingByValues(allProductPrices));
        return this;
    }

    public ProductPage VerifySortPriceHighToLow() {
        Assert.assertEquals(getValues(allProductPrices), getSortDescendingByValues(allProductPrices));
        return this;
    }
}
