package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DataTablesPage;
import pageObjects.herokuapp.NavigationPage;
import testNgUtils.Listener;

import static pageObjects.herokuapp.DataTablesItems.*;
import static pageObjects.herokuapp.NavigationItems.DATA_TABLES;

/**
 * Data Tables
 * 1. Реализовать методы по сбору данных из таблиц в формате List или Map
 * 2. Реализовать методы по проверке сортировки колонок
 */
@Listeners(Listener.class)
public class DataTables_test extends BaseTest {

    @Parameters("url")
    @BeforeMethod
    public void precondition(String url) {
        new NavigationPage()
                .open(url);
    }

    @Test(priority = 1, description = " data tables test")
    public void test1() {
        new NavigationPage().navigateTo(DATA_TABLES);
        new DataTablesPage()
                .verifySorting(LASTNAME_DESCENDING)
                .verifySorting(LASTNAME_ASCENDING)
                .verifySorting(FIRSTNAME_DESCENDING)
                .verifySorting(FIRSTNAME_ASCENDING)
                .verifySorting(EMAIL_ASCENDING)
                .verifySorting(EMAIL_DESCENDING)
                .verifySorting(WEBSITE_DESCENDING)
                .verifySorting(WEBSITE_ASCENDING)
                .verifySorting(DUE_ASCENDING)
                .verifySorting(DUE_DESCENDING);
    }
}


