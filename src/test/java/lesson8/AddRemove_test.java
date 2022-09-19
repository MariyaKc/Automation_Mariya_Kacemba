package lesson8;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.AddRemoveElementsPage;
import pageObjects.herokuapp.NavigationPage;

import static pageObjects.herokuapp.NavigationItems.*;

/** - Кликнуть один раз Add Element
 - Проверить что кнопка Delete добавилась
 - Кликнуть еще 5 раз Add Element
 - Проверить что отображается верное количество кнопок Delete
 - Очистить окно от кнопок Delete и проверить что эта кнопка не отображается после очистки */

public class AddRemove_test extends BaseTest {
    @BeforeMethod
    public void precondition() {
        new NavigationPage()
                .open();
    }

    @Test
    public void test1() {
        new NavigationPage()
                .navigateTo(ADD_REMOVE_ELEMENTS);
        new AddRemoveElementsPage()
                .clickAddElement(1)
                .verifyAddBtnDelete()
                .clickAddElement(5)
                .verifyQuantityDeleteBtn()
                .clickDelete(6)
                .verifyDeleteBtnNotExist();
    }
}
