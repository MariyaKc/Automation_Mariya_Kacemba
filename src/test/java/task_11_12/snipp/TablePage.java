package task_11_12.snipp;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablePage extends BasePage {
    private final By table = By.className("table_sort");
    private By headers = By.xpath("//table[@class='table_sort']//th");

    private By getTableRow(String index){
        return By.xpath("//table[@class='table_sort']//tbody//tr[" + index +"]");
    }

    public TablePage open(){
        driver.get("file:///" +System.getProperty("user.dir") +"/files/tableForTest.html");
        return this;
    }

    public TablePage checkTableIsDisplayed() {
        Assert.assertTrue(driver.findElement(table).isDisplayed());
        return this;
    }

    public TablePage clickTableColumn(String columnName) {
        findElement(headers).findElement(By.xpath("//th[text()='" + columnName + "']")).click();
        return this;
    }

    public TablePage doubleClickTableColumn(String columnName) {
       actions.doubleClick(findElement(headers).findElement(By.xpath("//th[text()='" + columnName + "']"))).build().perform();
        return this;
    }

    public List<List<String>> getTableRowsData(){
        Integer rowCount = driver.findElements(getTableRow("..")).size();
        List<List<String>> data = new ArrayList<>();
        for(int row=0; row<rowCount; row++){
            List<String> columData=new ArrayList<>();
            Integer columnCount = driver.findElement(getTableRow(Integer.toString(row + 1))).findElements(By.xpath(".//td")).size();
            for(int column=0;column<columnCount;column++){
                columData.add(driver.findElement(getTableRow(Integer.toString(row+1))).findElement(By.xpath(".//td[" + (column+1)+"]")).getText());
            }
            data.add(columData);
        }return data;
    }

    public Map<String ,List<String>> getTableData(){
        Map<String ,List<String>> mapData= new HashMap<>();
        Integer headerCount = driver.findElements(headers).size();
        List<List<String>> tableData= getTableRowsData();
        for (int header=0; header<headerCount; header++){
            List<String> columnData = new ArrayList<>();
            for (List<String> data:tableData){
                columnData.add(data.get(header));
            }
            mapData.put(driver.findElements(headers).get(header).getText(), columnData);
        }
        return mapData;
    }
}
