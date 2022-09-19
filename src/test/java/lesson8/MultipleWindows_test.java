package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.MultipleWindowsPage;
import pageObjects.herokuapp.NavigationPage;
import testNgUtils.Listener;

import static pageObjects.herokuapp.NavigationItems.*;
/** - Нажать на Click Here
 - Переключиться на новое окно и проверить текст
 - Вернуться на главное окно и закрыть новое окно
 - Проверить что новое окно было закрыто */
@Listeners(Listener.class)

public class MultipleWindows_test extends BaseTest {
    @Parameters("url")
    @BeforeMethod
    public void precondition(String url) {
        new NavigationPage()
                .open(url);
    }
    @Test (priority = 7, description = "Multiple Windows test")
    public void test1(){
        new NavigationPage()
                .navigateTo(MULTIPLE_WINDOWS);
        new MultipleWindowsPage()
                .clickHere()
                .switchToNewTab()
                .verifyText()
                .returnToHomeTab()
                .verifyNewTabIsClosed();
    }
}
