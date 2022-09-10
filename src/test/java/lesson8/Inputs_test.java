package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.InputsPage;
import pageObjects.herokuapp.NavigationPage;

import static pageObjects.herokuapp.NavigationItems.*;

/** Провести тестирование поля Number с использованием классов эквивалентности */

public class Inputs_test extends BaseTest {
    @BeforeMethod
    public void precondition(){
        new NavigationPage()
                .open();
    }

    @Test
    public void test1(){
        new NavigationPage()
                .navigateTo(INPUTS);
        new InputsPage()
                .sendKeysAndVerify("0")
                .setDownAndVerify(0,150)
                .sendKeysAndVerify("0")
                .setUpAndVerify(0,150);
    }
}
