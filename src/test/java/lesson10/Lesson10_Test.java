package lesson10;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DataTablesPage2;
import pageObjects.herokuapp.NavigationPage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pageObjects.herokuapp.NavigationItems.DATA_TABLES;

public class Lesson10_Test extends BaseTest {
    @BeforeMethod
    public void precondition() {
        new NavigationPage()
                .open();
    }
    @Test
    public void test() {
        new NavigationPage()
                .navigateTo(DATA_TABLES);
        new DataTablesPage2().getTableRowsData().forEach(System.out::println);// для записи в столбик
        Map<String, List<String>> TablesData = new DataTablesPage2().getTableData(); //вызываем всю мапу
        Map<String, List<String>> mapTableData = new DataTablesPage2().clickTableColumn("Last Name").getTableData(); //сортируем колонку через клик и собираем данные
        List<List<String>> tableData = new DataTablesPage2().getTableRowsData();
        Assert.assertTrue(tableData.get(2).contains("$100.00")); //проверяем, что у 3 записи(у джавы 2) есть такая запись
        List<String> lastNameData = mapTableData.get("Last Name"); // берем данные из мапы для столбца Last Name
        Assert.assertEquals(lastNameData, lastNameData.stream().sorted().collect(Collectors.toList())); // сравниваем значение из мапы(отсортировано через клик) и этот же список отсортированный через джаву
    }
}

