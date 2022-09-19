package pageObjects.baseObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    protected void clickAll(By locator) {
        List<WebElement> buttons = driver.findElements(locator);
        for (WebElement button : buttons) {
            System.out.println("I'm click by :: " + locator);
            button.click();
        }
    }

    protected void selectByText(By locator,String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(value);
        System.out.println("I'm select by text :: " + value);
    }

    protected void selectByIndex(By locator,int value) {
        Select select = new Select(driver.findElement(locator));
        System.out.println("I'm select by index :: " + value);
        select.selectByIndex(value);
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

    protected List<String> sortAscending(By element) {
        List<WebElement> webElementsList = getWebDriver().findElements(element);
        List<String>sortAscendingList = new ArrayList<>();
        webElementsList.forEach(elements -> {
            sortAscendingList.add(elements.getText());
            Collections.sort(sortAscendingList);
        });
        System.out.println("I'm ascending list ::" + sortAscendingList);
        return sortAscendingList;
    }

    protected List<String> sortDescending(By element) {
        List<WebElement> webElementsList = getWebDriver().findElements(element);
        List<String> sortDescendingList = new ArrayList<>();
        webElementsList.forEach(elements -> {
            sortDescendingList.add(elements.getText());
            Collections.sort(sortDescendingList,Collections.reverseOrder());
        });
        System.out.println("I'm descending list ::"+ sortDescendingList);
        return sortDescendingList;
    }

    protected List<String> sortAscendingPrice(By element) {
        List<String> sortAscendingList = getActualList(element);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // если строки разной длины, то более короткая
                if (o1.length() != o2.length())
                    return Integer.compare(o1.length(), o2.length());
                else
                    //если длины равны - сравниваем как строки
                    return o1.compareTo(o2);
            }
        };
        Collections.sort(sortAscendingList, comparator);
        System.out.println("I'm ascending list ::" + sortAscendingList);
        return sortAscendingList;
    }

    protected List<String> sortDescendingPrice(By element) {
        List<String> sortDescendingList = getActualList(element);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length())
                    return Integer.compare(o2.length(), o1.length());
                else
                    return o2.compareTo(o1);
            }
        };
        Collections.sort(sortDescendingList,comparator);
        System.out.println("I'm descending list ::"+ sortDescendingList);
        return sortDescendingList;
    }

    protected List<String> getActualList(By element) {
        List<WebElement> webElementsList = getWebDriver().findElements(element);
        List<String> actualList = new ArrayList<>();
        webElementsList.forEach(elements -> {
            actualList.add(elements.getText());
        });
        System.out.println("I'm actual List :: " + actualList);
        return actualList;
    }
}

