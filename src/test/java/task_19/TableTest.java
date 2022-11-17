package task_19;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableTest extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        get(TablePage.class).open();
    }

    @Test(description = "Create collection for first column")
    public void collectByFirstColumn() {
        Map<String, List<String>> allTableData = get(TablePage.class).getTableData();
        List<String> columnData = allTableData.get("Brand").stream().collect(Collectors.toList());
        System.out.println("I'm collcect for first column :: " + columnData);
    }

    @Test(description = "Check sort by Brand ascending")
    public void test1() {
        Map<String, List<String>> allTableData = get(TablePage.class).clickTableColumn("Brand").getTableData();
        List<String> actualData = allTableData.get("Brand").stream().collect(Collectors.toList());
        System.out.println("I'm actual ascending data :: " + actualData);
        List<String> expectedData = actualData.stream().sorted().collect(Collectors.toList());
        System.out.println("I'm expected ascending data :: " + expectedData);
        Assert.assertEquals(actualData, expectedData);
    }

    @Test(description = "Check sort by Flavor descending")
    public void test2() {
        Map<String, List<String>> allTableData = get(TablePage.class).doubleClickTableColumn("Flavor").getTableData();
        List<String> actualData = allTableData.get("Flavor").stream().collect(Collectors.toList());
        System.out.println("I'm actual descending data :: " + actualData);
        List<String> expectedData = actualData.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("I'm expected descending data :: " + expectedData);
        Assert.assertEquals(actualData, expectedData);
    }

    @Test(description = "Check sort by Price descending")
    public void test4() {
        Map<String, List<String>> allTableData = get(TablePage.class).doubleClickTableColumn("Price").getTableData();
        List<Integer> actualData = allTableData.get("Price").stream().map(data -> Integer.parseInt(data.replace("$", ""))).collect(Collectors.toList());
        System.out.println("I'm actual descending data :: " + actualData);
        List<Integer> expectedData = actualData.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("I'm expected descending data :: " + expectedData);
        Assert.assertEquals(actualData, expectedData);
    }
}
