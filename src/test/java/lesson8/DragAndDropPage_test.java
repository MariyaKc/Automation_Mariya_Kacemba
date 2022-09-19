package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DragAndDropPage;
import pageObjects.herokuapp.NavigationPage;

import javax.management.DescriptorRead;
import java.awt.*;
import java.io.IOException;

import static pageObjects.herokuapp.NavigationItems.*;

/**  Перетянуть А к В и проверить что элементы поменялись местами
     -Перетянуть В к A и проверить что элементы поменялись местами
    Подсказка: для перитягивания элементов используйте класс Actions */

public class DragAndDropPage_test extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new NavigationPage()
                .open();
    }

    @Test
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