package pageObjects.saucedemo;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static driver.DriverManager.getDriver;
@Log4j
public class CheckoutTwoPage extends BasePage {
    private final By finishBtn = By.id("finish");
    private final By cancelBtn = By.id("cancel");
    private final By title = By.xpath("//span[@class='title']");
    private final By allProductPrice = By.cssSelector(".inventory_item_price");
    private final By allProductName = By.cssSelector(".inventory_item_name");
    private final By itemPrice = By.cssSelector(".summary_subtotal_label");
    private final By tax = By.cssSelector(".summary_tax_label");
    private final By totalPrice = By.cssSelector(".summary_total_label");

    public CheckoutTwoPage() {
        verifyPageUri();
        verifyCheckoutTwo();
    }

    public void verifyPageUri() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("checkout-step-two.html"));
    }

    public CheckoutTwoPage verifyCheckoutTwo() {
        waitVisibilityOfElements(finishBtn, cancelBtn);
        return this;
    }
    public CheckoutTwoPage verifyPageTitle() {
        Assert.assertEquals(getText(title), "CHECKOUT: OVERVIEW");
        return this;
    }

    public double calculationItemTotalPrice() { //расчет стоимости товаров
        List<Double> allPrice = getValues(allProductPrice);
        double calculationTotalPrice = allPrice.stream().mapToDouble(Double::doubleValue).sum();
        return calculationTotalPrice;
    }

    public CheckoutTwoPage verifyItemTotalPrice() {  //сравнение
        double itemTotalPrice = Double.parseDouble(findElement(itemPrice).getText().replaceAll("[^\\d.]+", ""));
        Assert.assertEquals(itemTotalPrice,calculationItemTotalPrice(), "Total prices are not equal");
        log.debug("Calculation items total price ::" + calculationItemTotalPrice() + " Items total price in checkout page ::" + itemTotalPrice);
        return this;
    }

    public CheckoutTwoPage verifyTotalPrice() {
        double taxPrice = Double.parseDouble(findElement(tax).getText().replaceAll("[^\\d.]+", ""));
        double actualTotalPrice = Double.parseDouble(findElement(totalPrice).getText().replaceAll("[^\\d.]+", ""));

        BigDecimal result = new BigDecimal((calculationItemTotalPrice()+taxPrice));
        result = result.setScale(2, RoundingMode.DOWN);
        double expectedTotalPrice = result.doubleValue();

        Assert.assertEquals(actualTotalPrice,expectedTotalPrice, "Total prices are not equal");
        log.debug("Calculation actual total price ::" + expectedTotalPrice + " Total price in checkout page ::" + actualTotalPrice);
        return this;
    }

    public List<String> getProductListInCheckout() {
        return getTexts(allProductName);
    }

    public CheckoutTwoPage clickFinishBtn() {
        click(finishBtn);
        return this;
    }

    public CheckoutTwoPage clickCancelBtn() {
        click(cancelBtn);
        return this;
    }
}
