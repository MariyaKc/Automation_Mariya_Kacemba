package pageObjects.baseObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static driver.SimpleDriver.getWebDriver;

//Класс для инициализации объектов страниц
public class BasePage {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected Actions actions;

    //выполняется, когда создается экземпляр класса BasePage
    protected BasePage() {
        driver = getWebDriver();
        wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(20));
        actions = new Actions(driver);
    }

    //гибкое ожидание
    protected FluentWait<WebDriver> fluentWait(long timeout, long pollingEvery) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }


    protected void enter(WebElement webElement, String enterData) {
        System.out.println("I'm enter :: " + enterData + ", by web element :: " + webElement);
        webElement.clear();
        webElement.sendKeys(enterData);
    }

    protected void enter(By locator, CharSequence... enterData) {
        System.out.println("I'm enter :: " + enterData + ", by locator :: " + locator);
        driver.findElement(locator).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
        driver.findElement(locator).sendKeys(enterData);
    }

    protected void click(By locator) {
        System.out.println("I'm click by :: " + locator);
        driver.findElement(locator).click();
    }

    protected void click(WebElement webElement) {
        System.out.println("I'm click by :: " + webElement);
        webElement.click();
    }

    protected String getText(By locator) {
        System.out.println("I'm get text by  :: " + locator);
        return driver.findElement(locator).getText();
    }

    protected String getText(WebElement webElement) {
        System.out.println("I'm get text by  :: " + webElement);
        return webElement.getText();
    }

    protected List<String> getText (List<WebElement> elements) {
        System.out.println("I'm get text by  :: " + elements);
        List<String> actualData = new ArrayList<>();
        elements.forEach(webElement -> {
            actualData.add(webElement.getText());
        });
        return actualData;
    }
}

