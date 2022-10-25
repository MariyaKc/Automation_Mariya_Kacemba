package task_11_12_14.snipp;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;

import java.util.*;
import java.util.stream.Collectors;

/** 1. На основе https://snipp.ru/html-css/table-style создать свой html документ с таблицей
 2. Открыть драйвером созданный html документ
 3. Написать методы, которые позволяют переформировать таблицу web в коллекции java
 4. Написать пару тестов по валидации данных из таблицы */

public class Table_Test extends BaseTest {

    @BeforeMethod
    public void preconditions(){
        get(TablePage.class).open().checkTableIsDisplayed();
    }

    @Test(description = "Check sort by Name ascending")
    public void test1(){
        Map<String ,List<String >> allTableData = get(TablePage.class).clickTableColumn("Name").getTableData();
        List<String> actualData = allTableData.get("Name").stream().collect(Collectors.toList());
        System.out.println(actualData);
        List<String> expectedData = actualData.stream().sorted().collect(Collectors.toList());
        System.out.println(expectedData);
        Assert.assertEquals(actualData,expectedData);
    }

    @Test(description = "Check sort by Average ascending")
    public void test2(){
        Map<String ,List<String >> allTableData = get(TablePage.class).clickTableColumn("Average").getTableData();
        List<Double> actualData = allTableData.get("Average").stream().map(data->Double.parseDouble(data.replace(",","."))).collect(Collectors.toList());
        System.out.println(actualData);
        List<Double> expectedData = actualData.stream().sorted().collect(Collectors.toList());
        System.out.println(expectedData);
        Assert.assertEquals(actualData,expectedData);
    }

    @Test(description = "Check sort by Amount descending")
    public void test3(){
        Map<String ,List<String >> allTableData = get(TablePage.class).doubleClickTableColumn("Amount").getTableData();
        List<Double> actualData = allTableData.get("Amount").stream().map(data->Double.parseDouble(data.replace("$",""))).collect(Collectors.toList());
        System.out.println(actualData);
        List<Double> expectedData = actualData.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(expectedData);
        Assert.assertEquals(actualData,expectedData);
    }

    @Test(description = "Check sort by Birthday data descending")
    public void test4(){
        Map<String ,List<String >> allTableData = get(TablePage.class).doubleClickTableColumn("Birthday").getTableData();
        List<Integer> actualData = allTableData.get("Birthday").stream().map((data->Integer.parseInt(data.split("/")[0]))).collect(Collectors.toList());
        System.out.println(actualData);
        List<Integer> expectedData = actualData.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(expectedData);
        Assert.assertEquals(actualData,expectedData);
    }
}
