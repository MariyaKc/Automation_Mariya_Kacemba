package driver;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverManagerType driverManagerType) { //переиспользуем enum, на контроль ставим тип драйвера
        DriverManager driverManager = null;
        switch (driverManagerType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
        }
        return driverManager;
    }
}
