package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.baseObjects.BasePage;

import static driver.SimpleDriver.getWebDriver;

public class BasketPage extends BasePage {
    private WebElement getElementCartItem(String productName) { //3 - формируется элемент на уровне productName
        return getWebDriver().findElement(By.xpath("//*[@class = 'inventory_item_name' and text() = '" + productName + "']//ancestor::div[@class='cart_item']"));
    }

    private WebElement getElementProductCost(String productName) { // 2 - передается productName, затем обращение к родительскому getElementCartItem
        return getElementCartItem(productName).findElement(By.className("inventory_item_price")); //4-от родительского формируется элемент inventory_item_price
    }

    private WebElement getElementCartQuantity(String productName){
        return getElementCartItem(productName).findElement(By.className("cart_quantity"));
    }

    public String getProductCost(String productName) {  //1- приходит productName
        return getText(getElementProductCost(productName)); //6- целый элемент передается в метод getText
    }

    public String enterCartQuantity(String productName) {
        return getText(getElementCartQuantity(productName));
    }
}
