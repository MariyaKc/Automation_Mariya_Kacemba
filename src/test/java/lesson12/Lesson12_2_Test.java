package lesson12;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DataTablesPage2;
import pageObjects.herokuapp.DropdownListPage;
import pageObjects.herokuapp.NavigationItems;
import pageObjects.herokuapp.NavigationPage;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lesson12_2_Test extends BaseTest {

    @Test(priority = 1)
    public void dropdown_Test() {
        get(NavigationPage.class).open().navigateTo(NavigationItems.DROPDOWN_LIST);
        get(DropdownListPage.class)
                .verifyPageTitle("Dropdown List")
                .select(2)
                .verifySelectedValue("Option 2");
    }

    @Test(priority = 2)
    public void sortCurrency_Test() {
        get(NavigationPage.class).open().navigateTo(NavigationItems.DATA_TABLES);
        Map<String, List<String>> mapTableData = get(DataTablesPage2.class).checkTableIsDisplayed().clickTableColumn("Due").getTableData();

        //из строки делаем дабл и меняем $ на ""
        List<Double> currencyData = mapTableData.get("Due").stream().map(currency -> Double.parseDouble(currency.replace("$", ""))).collect(Collectors.toList());

        List<Double> sortedData = currencyData;
        System.out.println(currencyData);
        Collections.sort(sortedData);
        System.out.println(sortedData);
        Assert.assertEquals(currencyData, sortedData);
    }

}
