package pageObjects.baseObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static driver.SimpleDriver.getWebDriver;

//Класс для инициализации объектов страниц
public class BasePage {
    protected void enter(WebElement webElement, String enterData) {
        System.out.println("I'm enter :: " + enterData + ", by web element :: " + webElement);
        webElement.clear();
        webElement.sendKeys(enterData);
    }

    protected void enter(By locator, String enterData) {
        System.out.println("I'm enter :: " + enterData + ", by locator :: " + locator);
        getWebDriver().findElement(locator).clear();
        getWebDriver().findElement(locator).sendKeys(enterData);
    }

    protected void click(By locator) {
        System.out.println("I'm click by :: " + locator);
        getWebDriver().findElement(locator).click();
    }

    protected void click(WebElement webElement) {
        System.out.println("I'm click by :: " + webElement);
        webElement.click();
    }

    protected String getText(By locator) {
        System.out.println("I'm get text by  :: " + locator);
        return getWebDriver().findElement(locator).getText();
    }

    protected String getText(WebElement webElement) {
        System.out.println("I'm get text by  :: " + webElement);
        return webElement.getText();
    }
}

