package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;

public class SimpleDriver {

    // для распараллеливания драйвера на несколько потоков, т е для каждого потока создается свой драйвер
    private static  ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    //Блок инициализации - код, который будет выполнен при вызове данного класса
    {
        if (webDriver.get() == null) {
            WebDriverManager.chromedriver().setup();
            webDriver.set(new ChromeDriver(getChromeOptions()));
            webDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            webDriver.get().manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
            webDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
        }
    }

    public static void closeWebDriver() {
        if (webDriver!= null) {
            webDriver.get().close(); // закрыть текущее окно
            webDriver.get().quit(); //выйти из драйвера и закрыть все окна (напрямую закрывает браузер)
            webDriver.remove();
        }

    }

        public static WebDriver getWebDriver() {// получение
            return webDriver.get();
        }

    private static ChromeOptions getChromeOptions() { //метод, который возвращает опции браузера
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized"); // для запуска в полном окне
        return chromeOptions;
    }
}



