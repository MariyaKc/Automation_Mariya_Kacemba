package pageObjects.baseObjects;

import driver.SimpleDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import testNgUtils.ExtentReportListener;
import testNgUtils.InvokedMethodListener;

import java.lang.reflect.InvocationTargetException;

import static driver.SimpleDriver.closeWebDriver;

// класс содержит методы, которые могут быть многократно использованы в конкретных классах страниц
@Listeners({InvokedMethodListener.class, ExtentReportListener.class})

public abstract class BaseTest {

    @BeforeTest
    public void setUp() {
        System.out.println("I'm started new wed driver!");
        new SimpleDriver(); //instance объекта обращение к конструктору объекта
    }

    //дженерик для создания инстанс
    protected <T> T get(Class<T> page) {
        T instance = null;
        try {
            instance = page.getDeclaredConstructor().newInstance(); //передаем в него класс, берем конструктор (пустой),создаем инстанс
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @AfterTest
    public void stop() {
        System.out.println("I'm close wed driver!");
        closeWebDriver();
    }

}
