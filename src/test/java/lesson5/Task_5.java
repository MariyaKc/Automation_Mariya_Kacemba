package lesson5;

import driver.SimpleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static driver.SimpleDriver.getWebDriver;

/**Написать 3 теста по сценарию ниже с различными данными и вариантами.

 Открыть сайт https://masterskayapola.ru/kalkulyator/laminata.html
 Ввести параметры для расчета.
 Нажать на кнопку ‘Рассчитать’.
 Проверить полученные значения.
 Закрыть окно браузера.
 */
public class Task_5 {
    @BeforeTest
    public void preconditions() {
        SimpleDriver simpleDriver = new SimpleDriver();
        getWebDriver().get("https://masterskayapola.ru/kalkulyator/laminata.html");
    }


    @Test
    public void test1() {
        enter("calc_roomwidth", "8");
        enter("calc_roomheight", "5");

        enter("calc_lamwidth", "150");
        enter("calc_lamheight", "100");
        enter("calc_inpack", "5");
        enter("calc_price", "40");

        select("По длине комнаты");

        enter("calc_bias", "2");
        enter("calc_walldist", "4");
        submit();

        List<String> actualData = getActualData();

        List<String> expectedData = new ArrayList<>() {{
            add("Площадь укладки: 39.90 м2.");
            add("Кол-во панелей: 2700 шт.");
            add("Кол-во упаковок: 540 шт.");
            add("Стоимость: 1620 руб.");
            add("Остатки: 0 шт.");
            add("Отрезки: 50 шт.");
        }};

        Assert.assertEquals(actualData, expectedData);
    }

   @Test
   public void test2 (){
       enter("calc_roomwidth", "6");
       enter("calc_roomheight", "3");

       enter("calc_lamwidth", "1300");
       enter("calc_lamheight", "192");
       enter("calc_inpack", "12");
       enter("calc_price", "500");

       select("По длине комнаты");

       enter("calc_bias", "300");
       enter("calc_walldist", "10");
       submit();

       List<String> actualData = getActualData();

       List<String> expectedData = new ArrayList<>() {{
           add("Площадь укладки: 17.82 м2.");
           add("Кол-во панелей: 76 шт.");
           add("Кол-во упаковок: 7 шт.");
           add("Стоимость: 10483 руб.");
           add("Остатки: 8 шт.");
           add("Отрезки: 4 шт.");
       }};

       Assert.assertEquals(actualData, expectedData);
   }

    @Test
    public void test3 (){
        enter("calc_roomwidth", "15");
        enter("calc_roomheight", "10");

        enter("calc_lamwidth", "5000");
        enter("calc_lamheight", "1000");
        enter("calc_inpack", "100");
        enter("calc_price", "1000");

        select("По ширине комнаты");

        enter("calc_bias", "600");
        enter("calc_walldist", "20");
        submit();

        List<String> actualData = getActualData();

        List<String> expectedData = new ArrayList<>() {{
            add("Площадь укладки: 149.00 м2.");
            add("Кол-во панелей: 32 шт.");
            add("Кол-во упаковок: 1 шт.");
            add("Стоимость: 500000 руб.");
            add("Остатки: 68 шт.");
            add("Отрезки: 6 шт.");
        }};

        Assert.assertEquals(actualData, expectedData);
    }

    @AfterTest
    public void postconditions(){
        getWebDriver().close();
    }

    private void enter(String name, String value) {
        WebElement element = getWebDriver().findElement(By.name(name));
        element.sendKeys(Keys.chord(Keys.COMMAND, "a")); // выделяем значение в поле
        pause(1);
        element.sendKeys(Keys.DELETE); // удаляем
        pause(1);
        element.sendKeys(value); // записываем свое значение
    }

    private void select(String value) {
        Select selectByStowage = new Select(getWebDriver().findElement(By.name("calc_direct")));
        selectByStowage.selectByVisibleText(value);
    }

    private List<String> getActualData() {
        List<String> actualData = new ArrayList<>();
        List<WebElement> result = getWebDriver().findElements(By.cssSelector(".whiteback [class='form_element']"));

        result.forEach(webElement -> {
            actualData.add(webElement.getText());

        });
        return actualData;
    }

    private void submit() {
        getWebDriver().findElement(By.cssSelector("[value='Рассчитать']")).click();
    }


    private void pause(Integer timeout) {
        try {
            Thread.sleep(timeout * 1000); // timeout по умолчанию принимает милисекунлы, поэтому умножаем 1000
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}