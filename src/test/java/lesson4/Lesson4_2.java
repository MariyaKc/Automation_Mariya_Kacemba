package lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
//SareLine тест ZipCode на странице регистрации

public class Lesson4_2 {
    WebDriver driver; // объявляем переменную драйвер как экземпляр класса


    @BeforeTest
    //означает, что перед выполнением всех тестов, которые помечены анотацией тест, будет выполнено @BeforeTest
    public void preconditions() {
        driver = new ChromeDriver(); //создание драйвера
        driver.manage().window().maximize(); //для запуска в полном окне
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // неявный тип ожидания: драйвер ждет элемент 10 секунд, плка он изменится
        driver.get("https://www.sharelane.com/cgi-bin/register.py"); // переход на страницу теста

    }
    @BeforeMethod
    public void beforeMetod (){
        driver.get("https://www.sharelane.com/cgi-bin/register.py"); // переход на страницу теста
    }

    //тест на граничные значения 4, 5, 6, Если ввели 5-6 символов, то zipCode больще не отображаетс
    @Test
    public void test1() {
        // System.setProperty("webdriver.chrome.driver", "src/test/java/resources/chromedriver"); //первое ключ, второе щначение - добавили эту строку в VM в Edit Configuration
        WebElement zipCode = driver.findElement(By.name("zip_code"));// поиск по имени
        WebElement continueButton = driver.findElement(By.xpath("//*[@value='Continue']")); //поиск по  локатору
        zipCode.sendKeys("1234"); //передаем значение
        continueButton.click();
        pause(2);
        WebElement updatedZipCode = driver.findElement(By.name("zip_code"));
        Assert.assertTrue(updatedZipCode.isDisplayed()); //проверка отображения;
    }

    @Test
    public void test2() {
        WebElement zipCode = driver.findElement(By.name("zip_code"));
        WebElement continueButton = driver.findElement(By.xpath("//*[@value='Continue']"));
        zipCode.clear(); //чтобы очистить поле
        zipCode.sendKeys("12345"); //передаем значение
        continueButton.click();
        pause(2);
        WebElement updatedZipCode = driver.findElement(By.name("zip_code"));
        Assert.assertFalse(updatedZipCode.isDisplayed()); //проверка отображения;
    }

    @Test
    public void test3() {
        WebElement zipCode = driver.findElement(By.name("zip_code"));
        WebElement continueButton = driver.findElement(By.xpath("//*[@value='Continue']"));
        zipCode.clear();
        zipCode.sendKeys("123456"); //передаем значение
        continueButton.click();
        pause(2);
        WebElement updatedZipCode = driver.findElement(By.name("zip_code"));
        Assert.assertFalse(updatedZipCode.isDisplayed()); //проверка отображения;
    }

    @AfterTest
    public void postconditions(){
        driver.close();
    }

 // метод позволяет преостановить дрвйвер
    private void pause (Integer timeout){
        try {
            Thread.sleep(timeout*1000); // timeout по умолчанию принимает  милисекунлы, поэтому умножаем 1000
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
