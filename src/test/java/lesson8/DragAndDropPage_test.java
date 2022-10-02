package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DragAndDropPage;
import pageObjects.herokuapp.NavigationPage;
import testNgUtils.Listener;

import java.awt.*;
import java.io.IOException;

import static pageObjects.herokuapp.NavigationItems.DRAG_AND_DROP;

/**
 * Перетянуть А к В и проверить что элементы поменялись местами
 * -Перетянуть В к A и проверить что элементы поменялись местами
 * Подсказка: для перитягивания элементов используйте класс Actions
 */
@Listeners(Listener.class)

public class DragAndDropPage_test extends BaseTest {
    @Parameters("url")
    @BeforeMethod
    public void precondition(String url) {
        new NavigationPage()
                .open(url);
    }

    @Test(priority = 2, description = " drag and drop test with js")
    public void test1() throws AWTException, IOException {
        new NavigationPage()
                .navigateTo(DRAG_AND_DROP);
        new DragAndDropPage()
                .FromAToB()
                .verifyChangeA()
                .FromBToA()
                .verifyChangeB();
    }
}