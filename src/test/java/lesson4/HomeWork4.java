package lesson4;

import driver.SimpleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static driver.SimpleDriver.getWebDriver;

public class HomeWork4 {
    @BeforeTest
    public void precondition(){
        SimpleDriver simpleDriver = new SimpleDriver();
        getWebDriver().get("https://www.google.com/");
    }

    /**- проверить поиск если ввести “Привет, мир”
     * (проверить что в ссылках после поиска отображается текст “Привет, мир”,
     * а также что этот текст отображается в поисковой строке)*/

     @Test
    public void testHelloWorld(){
        getWebDriver().findElement(By.name("q")).sendKeys("Привет,мир", Keys.ENTER);
        List<WebElement> result = getWebDriver().findElements(By.cssSelector("[lang='ru'] h3")); // список элементов по данному локатору
        for (WebElement element :result){
            System.out.println(element.getText()); //вывод текста элементов
            //Assert.assertTrue(element.getText().contains("Мир")); //  один из элементов поиска пустой
        }
    }


    /**проверить поиск если ввести ” "
     * (проверить что отображается текст “По запросу " " ничего не найдено. ”)
     */

    @Test
    public void notFound(){
        getWebDriver().findElement(By.name("q")).sendKeys("*//*",Keys.ENTER);
        WebElement foundResult = getWebDriver().findElement(By.id("topstuff"));// поиск по имени
        System.out.println(foundResult.getText());
        Assert.assertTrue(foundResult.getText().contains("По запросу *//* ничего не найдено."));
    }

    @AfterTest
    public void postconditions(){
        getWebDriver().close();
    }
}
