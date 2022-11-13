package task_19;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static driver.DriverManager.getDriver;

public class TablePage extends BasePage {

    private final By table = By.className("table_sort");
    private final By headers = By.xpath("//table[@class='table_sort']//th");
    private final By usernameInput = By.id("user");
    private final By passwordInput = By.id("pswrd");
    private final By loginBtn = By.id("logIn_btn");
    private final By rememberCheckbox = By.name("remember");
    private final By selectCities = By.id("cities");
    private final By img = By.tagName("img");
    private final By titleP = By.id("logForm");
    private final By twitter = By.className("fa-twitter");
    private final By reddit = By.className("fa-reddit");
    private final By instagram = By.className("fa-instagram");

    public TablePage open() {
        driver.get("file:///" + System.getProperty("user.dir") + "/files/htmlpage/page.html");
        return this;
    }

    public TablePage verifyImg() {
        Assert.assertTrue(findElement(img).isDisplayed());
        return this;
    }

    public TablePage verifyTagP() {
        Assert.assertTrue(findElement(titleP).getText().contains("Login:"));
        return this;
    }

    public TablePage enterInput(String name, String password) {
        enter(usernameInput, name);
        enter(passwordInput, password);
        return this;
    }

    public TablePage clickBtn() {
        click(loginBtn);
        return this;
    }

    public TablePage checkCheckbox() {
        click(rememberCheckbox);
        return this;
    }

    public TablePage selectByValue(int value) {
        select(selectCities, value);
        return this;
    }

    public TablePage verifyTwitter() {
        click(twitter);
        Assert.assertTrue(getDriver().getCurrentUrl().contains("twitter"));
        driver.navigate().back();
        return this;
    }

    private By getTableRow(String index) {
        return By.xpath("//table[@class='table_sort']//tbody//tr[" + index + "]");
    }


    public TablePage checkTableIsDisplayed() {
        Assert.assertTrue(findElement(table).isEnabled());
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

    public List<List<String>> getTableRowsData() {
        Integer rowCount = driver.findElements(getTableRow("..")).size();
        List<List<String>> data = new ArrayList<>();
        for (int row = 0; row < rowCount; row++) {
            List<String> columData = new ArrayList<>();
            Integer columnCount = driver.findElement(getTableRow(Integer.toString(row + 1))).findElements(By.xpath(".//td")).size();
            for (int column = 0; column < columnCount; column++) {
                columData.add(driver.findElement(getTableRow(Integer.toString(row + 1))).findElement(By.xpath(".//td[" + (column + 1) + "]")).getText());
            }
            data.add(columData);
        }
        return data;
    }

    public Map<String, List<String>> getTableData() {
        Map<String, List<String>> mapData = new HashMap<>();
        Integer headerCount = driver.findElements(headers).size();
        List<List<String>> tableData = getTableRowsData();
        for (int header = 0; header < headerCount; header++) {
            List<String> columnData = new ArrayList<>();
            for (List<String> data : tableData) {
                columnData.add(data.get(header));
            }
            mapData.put(driver.findElements(headers).get(header).getText(), columnData);
        }
        return mapData;
    }
}
