package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.network.Network;
import org.testng.Assert;

import java.util.Optional;
import java.util.Properties;

import static propertyHelper.PropertyReader.getProperties;

@Log4j
public class ChromeDriverManager extends DriverManager {
    @Override
    public void createDriver() {
        ChromeDriver driver;
        Properties properties = getProperties();
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        if (getProperties().containsKey("browser.configs")) {
            chromeOptions.addArguments(properties.getProperty("browser.configs"));
            driver = new ChromeDriver(chromeOptions);
        } else {
            driver = new ChromeDriver();
        }
        if (properties.containsKey("interceptor") && !properties.get("interceptor").toString().isEmpty() && Boolean.parseBoolean(properties.getProperty("interceptor")))
            creteDevToolListeners(driver);
        webDriver.set(driver);
    }

    private void creteDevToolListeners(ChromeDriver driver) {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty())); //подключение нетворка
        devTools.addListener(Network.responseReceived(), responseReceived -> { // подключение Listener
            if (responseReceived.getResponse().getUrl().contains(getProperties().getProperty("api"))) { //пишет логи если запрос содержит api
                Assert.assertTrue(responseReceived.getResponse().getStatus().toString().startsWith("2"), "Ops... Something was wrong with request " + responseReceived.getResponse().getUrl()); //все коды должны начинаться с 2
                log.debug("URL :: " + responseReceived.getResponse().getUrl());
                log.debug("Status Code :: " + responseReceived.getResponse().getStatus());
                log.debug("Response Time :: " + responseReceived.getResponse().getResponseTime().get());
            }
        });
    }
}
