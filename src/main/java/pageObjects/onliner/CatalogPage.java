package pageObjects.onliner;

import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;

public class CatalogPage extends BasePage {

    private final By searchFiled = By.cssSelector("[class='fast-search__input']");
    private final By modal = By.cssSelector("[class='modal-iframe']");


    private By getSearchProductList(String productName) {
        return By.partialLinkText(productName);
    }

    public CatalogPage open() {
        load();
        return this;
    }

    public CatalogPage search(String text) {
        enter(searchFiled, text);
        return this;
    }

    public CatalogPage clickOnProductSearch(String productName) {
        driver.switchTo().frame(driver.findElement(modal));
        driver.findElement(getSearchProductList(productName)).click();
        driver.switchTo().defaultContent();
        return this;
    }
}
