package lesson8;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DropdownListPage;
import pageObjects.herokuapp.NavigationPage;
import static pageObjects.herokuapp.NavigationItems.*;

/** -Выбрать опцию по индексу и проверить что опция выбрана
 -Выбрать опцию по имени и проверить что опция выбрана  */

public class DropdownList_test extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new NavigationPage().open();
    }

    @Test
    public void test1() {
        new NavigationPage().navigateTo(DROPDOWN_LIST);
       new DropdownListPage()
               .selectByName("Option 1")
               .verifyFirstOptionIsSelected()
               .selectByIndex(2)
               .verifySecondOptionIsSelected();

    }
}

