package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

import static propertyHelper.PropertyReader.getProperties;

public class ChromeDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        WebDriver driver;
        Properties properties = getProperties();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(properties.getProperty("browser.configs")); //для запуска в headless режиме
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        webDriver.set(driver); //сетап для внешнего драйвера
    }
}
