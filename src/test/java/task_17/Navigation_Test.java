package task_17;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.navigation.LinksEnum;
import pageObjects.moodpanda_Selenide.navigation.NavigationPage;
import static pageObjects.moodpanda_Selenide.navigation.LinksEnum.*;

/** (*) Написать тест по навигации
 Пройти по всем табам
 Home
 What?
 How?
 About
 Contact us
 Проверить что контент этих страниц загрузился
 */

public class Navigation_Test  extends SelenideBaseTest {

    /**1 - вариант*/
    @Test(description = "Go to all navigation page and verify then all tab was opened with Switch Case")
    public void navigationTestWithSwitchCase() {
        get(NavigationPage.class)
                .goToItemAndVerify(What)
                .goToItemAndVerify(How)
                .goToItemAndVerify(About)
                .goToItemAndVerify(Contact)
                .goToItemAndVerify(Home);
    }

    /**2 - вариант*/
    @Test(description = "Go to all navigation page and verify then all tab was opened with Data Provider", dataProvider = "navigation data")
    public void navigationTestWithDataProvider(LinksEnum link, String uri, String title) {
        get(NavigationPage.class)
                .clickNavigationItem(link).verifyPageUri(uri).verifyTitle(title);
    }

    @DataProvider(name = "navigation data")
    public Object [][] data(){
        return new Object[][] {
                {What, "monitor-your-mood", "Monitor your mood"},
                {How, "how-to-use-a-mood-diary", "How to use a mood diary"},
                {About, "about", "About MoodPanda"},
                {Contact, "contact", ""},
                {Home, "moodpanda.com", "MoodPanda"}
        };
    }
}
