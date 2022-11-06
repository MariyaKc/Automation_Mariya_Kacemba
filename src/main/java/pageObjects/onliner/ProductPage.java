package pageObjects.onliner;

import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;

public class ProductPage extends BasePage {

    private final By price = By.cssSelector("[class $= 'price_primary']");

    public String getProductPrice() {
        return getText(price);
    }
}
