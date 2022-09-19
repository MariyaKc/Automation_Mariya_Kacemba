package lesson8;

import org.testng.annotations.*;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.InputsPage;
import pageObjects.herokuapp.NavigationPage;
import testNgUtils.Listener;

import java.lang.ref.SoftReference;

import static pageObjects.herokuapp.NavigationItems.*;

/** Провести тестирование поля Number с использованием классов эквивалентности */

@Listeners(Listener.class)
public class Inputs_test extends BaseTest {
    @Parameters("url")
    @BeforeMethod
    public void precondition(String url){
        new NavigationPage()
                .open(url);
    }

    @Test(priority = 5, description = "Multiple Windows test1")
    public void test1(){
        new NavigationPage()
                .navigateTo(INPUTS);
        new InputsPage()
                .sendKeysAndVerify("0")
                .setDownAndVerify(0,150)
                .sendKeysAndVerify("0")
                .setUpAndVerify(0,150);
    }

    @Test(priority = 6, description = "Multiple Windows test2",dataProvider = "value data", dependsOnMethods = "test1")
    public void test2 (String value, int startValue, int countIteration) {
        new NavigationPage()
                .navigateTo(INPUTS);
        new InputsPage()
                .sendKeysAndVerify(value)
                .setDownAndVerify(startValue, countIteration)
                .sendKeysAndVerify(value)
                .setUpAndVerify(startValue, countIteration);
    }

    @DataProvider(name = "value data")
    public Object[][] getData() {
        return new Object[][]{
                {"0",0,150},
                {"1",1,200},
                {"-1",-1,100},
                {"10",10,120},
                {"15432",15432,10}
        };
    }
}
