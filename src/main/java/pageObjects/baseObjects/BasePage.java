package pageObjects.baseObjects;

import driver.UIElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void load(String url) {
        System.out.println("Open page :: " + url);
        driver.get(url);
    }

    protected String getPageUrl() {
        System.out.println("Get page url");
        return driver.getCurrentUrl();
    }

    protected void enter(WebElement webElement, String enterData) {
        System.out.println("I'm enter :: " + enterData + ", by web element :: " + webElement);
        webElement.clear();
        webElement.sendKeys(enterData);
    }

    protected void enter(By locator, CharSequence... enterData) { //по идее должно работать с разными ОС
        System.out.println("I'm enter :: " + enterData + ", by locator :: " + locator);
        String os = System.getProperty("os.name");
        if (os.contains("Mac")) {
            findElement(locator).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
        } else {
            findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }
        findElement(locator).sendKeys(enterData);
    }

    protected void click(By locator) {
        System.out.println("I'm click by :: " + locator);
        verifyElementClickable(locator);
        findElement(locator).click();
    }

    protected void click(WebElement webElement) {
        System.out.println("I'm click by :: " + webElement);
        new UIElement(driver, wait, webElement).click();
    }

    protected void clickAll(By... locators) {
        for (By locator : locators) {
            System.out.println("I'm click by :: " + locator);
            List<WebElement> buttons = driver.findElements(locator);
            for (WebElement button : buttons) {
                button.click();
            }
        }
    }

    protected void clickAll(WebElement... elements) {
        for (WebElement element : elements) {
            System.out.println("I'm click by :: " + element);
            List<WebElement> buttons = new ArrayList<>();
            buttons.add(element);
            for (WebElement button : buttons) {
                button.click();
            }
        }
    }

    protected void select(By locator, Integer index) {
        System.out.println("Select by locator => " + locator + " with index => " + index);
        Select select = new Select(findElement(locator));
        select.selectByIndex(index);
    }

    protected void select(By locator, String value) {
        System.out.println("Select by locator => " + locator + " with value => " + value);
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(value);
    }
    protected void select(WebElement element, Integer index) {
        System.out.println("Select by locator => " + element + " with index => " + index);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    protected void select(WebElement element, String value) {
        System.out.println("Select by locator => " + element + " with value => " + value);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    protected String getText(By locator) {
        System.out.println("I'm get text by  :: " + locator);
        return findElement(locator).getText();
    }

    protected String getText(WebElement webElement) {
        System.out.println("I'm get text by  :: " + webElement);
        return webElement.getText();
    }

    protected List<String> getTexts(By locator) {
        System.out.println("I'm get texts by  :: " + locator);
        return findElements(locator).stream().map(webElement -> webElement.getText()).collect(Collectors.toList());
        //через stream() представили коллекцию веб элементов в качестве потока данных
        //через map перебираем каждый элемент и переделываем его с типа webElement -> webElement.getText() в строку
        //map представляет собой стрим,через collect(Collectors.toList() переводим в лист
    }
    protected List<String> getTexts(List<WebElement> webElements) {
        System.out.println("I'm get texts by  :: " + webElements);
        return webElements.stream().map(webElement -> webElement.getText()).collect(Collectors.toList());
    }

    protected List<String> getSortAscendingByTexts(By locator) {
        System.out.println("I'm sorting texts by  :: " + locator);
        List<String> sortAscendingList = getTexts(locator);
        System.out.println("I'm ascending sorted data :: " + sortAscendingList);
        return sortAscendingList;
    }

    protected List<String> getSortAscendingByTexts(List<WebElement> webElements) {
        System.out.println("I'm sorting texts by  :: " + webElements);
        List<String> sortAscendingList = getTexts(webElements);
        System.out.println("I'm ascending sorted data :: " + sortAscendingList);
        return sortAscendingList;
    }


    protected List<String> getSortDescendingByTexts(By locator) {
        List<String> sortDescendingList = getTexts(locator);
        Collections.sort(sortDescendingList,Collections.reverseOrder());
        System.out.println("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

    protected List<String> getSortDescendingByTexts(List<WebElement> webElements) {
        List<String> sortDescendingList = getTexts(webElements);
        Collections.sort(sortDescendingList,Collections.reverseOrder());
        System.out.println("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

    protected List<Double> getValues(By locator) {
        List<Double> getData = findElements(locator).stream()
                .map(webElement -> webElement.getText())
                .map(webElement -> webElement.replace("$", ""))
                .map(Double::parseDouble).collect(Collectors.toList());
        System.out.println("I'm get values by  :: " + getData);
        return getData;
    }

    protected List<Double> getValues(List<WebElement> webElements) {
        List<Double> getData = webElements.stream()
                .map(webElement -> webElement.getText())
                .map(webElement -> webElement.replace("$", ""))
                .map(Double::parseDouble).collect(Collectors.toList());
        System.out.println("I'm get values by  :: " + getData);
        return getData;
    }

    protected List<Double> getSortAscendingByValues(By locator) {
        List<Double> sortAscendingList = getValues(locator);
        Collections.sort(sortAscendingList);
        System.out.println("I'm ascending sorted data :: " + sortAscendingList);
        return sortAscendingList;
    }

    protected List<Double> getSortAscendingByValues(List<WebElement> webElements) {
        List<Double> sortAscendingList = getValues(webElements);
        Collections.sort(sortAscendingList);
        System.out.println("I'm ascending sorted data :: " + sortAscendingList);
        return sortAscendingList;
    }

    protected List<Double> getSortDescendingByValues(By locator) {
        List<Double> sortDescendingList = getValues(locator);
        Collections.sort(sortDescendingList, Collections.reverseOrder());
        System.out.println("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

    protected List<Double> getSortDescendingByValues(List<WebElement> webElements) {
        List<Double> sortDescendingList = getValues(webElements);
        Collections.sort(sortDescendingList, Collections.reverseOrder());
        System.out.println("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

    protected String getElementAttribute(By by, String attribute) { //получение атрибута элемента
        System.out.println("Get element => " + by + ", attribute :: " + attribute);
        return findElement(by).getAttribute(attribute);
    }

    protected List<String> getElementsAttribute(By by, String attribute) {
        System.out.println("Get element => " + by + ", attribute :: " + attribute);
        return findElements(by).stream().map(webElement -> webElement.getAttribute(attribute)).collect(Collectors.toList());
    }

    //говорит о состоянии элемента в текущий момент времени(спрашиваем о состоянии NotExist)
    public Boolean elementNotExist(By by) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); //отключаем implicitlyWait(драйвер не ждет выполнения элемента)
        for (int counter = 1; counter < 20; counter++) {
            System.out.println("Wait element not exist count = " + counter);
            if (findElements(by).size() == 0) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            }
            waitUntil(1);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); //подключаем обратно, чтобы его использовали другие методы
        return false;
    }


    protected void waitVisibilityOfElement(By locator) {
        System.out.println("wait visibility of element => " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean waitVisibilityOfElements(By... locators) {
        for (By locator : locators) {
            System.out.println("wait visibility of element => " + locator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        return true;
    }

    protected boolean waitVisibilityOfElements(WebElement... webElements) {
        for (WebElement webElement : webElements) {
            System.out.println("wait visibility of element => " + webElement);
            wait.until(ExpectedConditions.visibilityOf(webElement));
        }
        return true;
    }

    protected void verifyElementTextToBe(By locator, String text) {
        System.out.println("verify element text to be => " + locator);
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    protected void verifyElementClickable(By locator) {
        System.out.println("verify element clickable => " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitUntil(Integer timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /** Для варианта реализации herokuapp.DataTablesPage-> */
    protected List<String> sortAscending(By element) {
        List<WebElement> webElementsList = getWebDriver().findElements(element);
        List<String> sortAscendingList = new ArrayList<>();
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
            Collections.sort(sortDescendingList, Collections.reverseOrder());
        });
        System.out.println("I'm descending list ::" + sortDescendingList);
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
        Collections.sort(sortDescendingList, comparator);
        System.out.println("I'm descending list ::" + sortDescendingList);
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

