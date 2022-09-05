package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;

public class SimpleDriver {

    private static WebDriver webDriver;

    //Блок инициализации - код, который будет выполнен при вызове данного класса
    {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();//создание chromedriver
            webDriver = new ChromeDriver(getChromeOptions());// сетапим в chromedriver параметры запуска браузера сюда
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
        }
    }

    public static void closeWebDriver(){
        webDriver.close(); // закрыть текущее окно
        webDriver.quit(); //выйти из драйвера и закрыть все окна (напрямую закрывает браузер)
        webDriver = null; // обнуляет созданный элемент
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



