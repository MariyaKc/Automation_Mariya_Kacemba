package lesson5;

import driver.SimpleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static driver.SimpleDriver.getWebDriver;

public class Lesson5_2 {
    /**
     * Открыть сайт http://13gp.by/informatsiya/meditsinskie-kalkulyatory/995-raschet-skorosti-klubochkovoj-filtratsii-skf
     * Выбрать пол ‘женский’ из выпадающего списка
     * Ввести ‘Креатинин’ = 75
     * Ввести ‘Возраст’ = 23
     * Ввести ‘Вес’ = 55
     * Ввести ‘Рост’ = 170
     * Нажать на кнопку ‘Рассчитать’
     * Проверить результаты
     * Закрыть окно браузера
     * 2 теста  на других входных параметрах
     */

    @BeforeTest
    public void preconditions() {
        SimpleDriver simpleDriver = new SimpleDriver();
        getWebDriver().get("http://13gp.by/informatsiya/meditsinskie-kalkulyatory/995-raschet-skorosti-klubochkovoj-filtratsii-skf");
    }

    @BeforeMethod
    public void reload() {
        getWebDriver().navigate().refresh();// перезагрузка страницы
    }

    @Test
    public void test1() {
        select("женский");
        enter("oCr", "75");
        enter("oAge", "23");
        enter("oWeight", "55");
        enter("oHeight", "170");
        submit();
        List<String> actualData = getActualData();
        List<String> expectedData = new ArrayList<>() {{
            add("MDRD: 88 (мл/мин/1,73кв.м)");
            add("ХБП: 2 стадия (при наличии почечного повреждения)");
            add("Cockroft-Gault: 86 (мл/мин)");
            add("Поверхность тела:1.63 (кв.м)");
        }};
        Assert.assertEquals(actualData, expectedData);
    }

    @Test
    public void test2() {
        select("мужской");
        enter("oCr", "68");
        enter("oAge", "27");
        enter("oWeight", "78");
        enter("oHeight", "179");
        submit();
        List<String> actualData = getActualData();
        List<String> expectedData = Arrays.asList(
                "MDRD: 129 (мл/мин/1,73кв.м)",
                "ХБП: 1 стадия (при наличии почечного повреждения)",
                "Cockroft-Gault: 159 (мл/мин)",
                "Поверхность тела:1.97 (кв.м)"
        );
        Assert.assertEquals(actualData, expectedData);
    }


    @Test
    public void test3() {
        select("женский");
        enter("oCr", "80");
        enter("oAge", "38");
        enter("oWeight", "55");
        enter("oHeight", "163");
        submit();
        List<String> actualData = getActualData();
        List<String> expectedData = Arrays.asList(
                "MDRD: 74 (мл/мин/1,73кв.м)",
                "ХБП: 2 стадия (при наличии почечного повреждения)",
                "Cockroft-Gault: 70 (мл/мин)",
                "Поверхность тела:1.58 (кв.м)"
        );
        Assert.assertEquals(actualData, expectedData);
    }

    @AfterTest
    public void postconditions() {
        getWebDriver().close();
    }

    private void enter(String id, String value) {
        getWebDriver().findElement(By.id(id)).clear();
        getWebDriver().findElement(By.id(id)).sendKeys(value);
    }

    private void select(String value) {
        Select selectGender = new Select(getWebDriver().findElement(By.id("oSex")));
        selectGender.selectByVisibleText(value);
    }

    private List<String> getActualData() {
        List<String> actualData = new ArrayList<>();
        List<WebElement> result = getWebDriver().findElements(By.cssSelector("li div[id]"));
        result.forEach(webElement -> {
            actualData.add(webElement.getText());
        });
        return actualData;
    }

    private void submit() {
        getWebDriver().findElement(By.cssSelector("[value='Рассчитать']")).click();
    }
}
