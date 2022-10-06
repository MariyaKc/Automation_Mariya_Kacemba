package lesson4;

import driver.SimpleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static driver.SimpleDriver.getWebDriver;

/**
 * SareLine тест ZipCode на странице регистрации. Тест на граничные значения 4, 5, 6,
 * Если ввели 5-6 символов, то zipCode больше не отображается
 */
public class Lesson4_2 {

    @BeforeTest
    //означает, что перед выполнением всех тестов, которые помечены аннотацией Test, будет выполнено @BeforeTest
    public void preconditions() {
        SimpleDriver simpleDriver = new SimpleDriver();
        getWebDriver().get("https://www.sharelane.com/cgi-bin/register.py"); // переход на страницу теста

    }

    @BeforeMethod
    public void beforeMetod() {
        getWebDriver().get("https://www.sharelane.com/cgi-bin/register.py"); // переход на страницу теста
    }


    @Test
    public void test1() {
        WebElement zipCode = getWebDriver().findElement(By.name("zip_code"));// поиск по имени
        WebElement continueButton = getWebDriver().findElement(By.xpath("//*[@value='Continue']")); //поиск по  локатору
        zipCode.sendKeys("1234"); //передаем значение
        continueButton.click();
        pause(2);
        WebElement updatedZipCode = getWebDriver().findElement(By.name("zip_code"));
        Assert.assertTrue(updatedZipCode.isDisplayed()); //проверка отображения;
    }

    @Test
    public void test2() {
        WebElement zipCode = getWebDriver().findElement(By.name("zip_code"));
        WebElement continueButton = getWebDriver().findElement(By.xpath("//*[@value='Continue']"));
        zipCode.clear(); //чтобы очистить поле
        zipCode.sendKeys("12345"); //передаем значение
        continueButton.click();
        pause(2);
        WebElement updatedZipCode = getWebDriver().findElement(By.name("zip_code"));
        Assert.assertFalse(updatedZipCode.isDisplayed()); //проверка отображения;
    }

    @Test
    public void test3() {
        WebElement zipCode = getWebDriver().findElement(By.name("zip_code"));
        WebElement continueButton = getWebDriver().findElement(By.xpath("//*[@value='Continue']"));
        zipCode.clear();
        zipCode.sendKeys("123456"); //передаем значение
        continueButton.click();
        pause(2);
        WebElement updatedZipCode = getWebDriver().findElement(By.name("zip_code"));
        Assert.assertTrue(updatedZipCode.isDisplayed()); //проверка отображения;
    }

    @AfterTest
    public void postconditions() {
        getWebDriver().close();
    }

    // метод позволяет приостановить драйвер
    private void pause(Integer timeout) {
        try {
            Thread.sleep(timeout * 1000); // timeout по умолчанию принимает миллисекунды, поэтому умножаем 1000
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
