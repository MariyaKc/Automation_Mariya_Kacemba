package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SimpleDriver {
    // static - позволяет обратиться к элементу без создания объекта; означает принадлежность к классу
    // WebDriverManager позволяет создать драйвер без локально хранящегося драйвера, библиотека подтягивает необходимый драйвер

    private static WebDriver webDriver; //создали переменную webDriver по умолчанию 0


    //Блок инициализации - код, который будет выполнен при вызове данного класса
    {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();//создание chromedriver
            webDriver = new ChromeDriver(getChromeOptions());// сетапим в chromedriver параметры запуска браузера сюда

        }
    }

    public static void closeWebDriver(){
        //webDriver.close(); // закрыть текущее окно
        webDriver.quit(); //выйти из драйвера и закрыть все окна (напрямую закрывает браузер)
    }

    public static WebDriver getWebDriver() {// получение
        return webDriver;
    }

    private static ChromeOptions getChromeOptions() { //метод, который возвращает опции браузера
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized"); // для запуска в полном окне
        return chromeOptions;
    }
}



