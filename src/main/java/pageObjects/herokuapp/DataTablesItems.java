package pageObjects.herokuapp;

import org.openqa.selenium.By;

public enum DataTablesItems {
    LASTNAME_ASCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[1]")),
    LASTNAME_DESCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[1]")),
    FIRSTNAME_ASCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[2]")),
    FIRSTNAME_DESCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[2]")),
    EMAIL_ASCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[3]")),
    EMAIL_DESCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[3]")),
    DUE_ASCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[4]")),
    DUE_DESCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[4]")),
    WEBSITE_ASCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[5]")),
    WEBSITE_DESCENDING(By.xpath("(//*[@id='table1']/tbody/tr/td[5]"));

    private By item;

    DataTablesItems(By item) {
        this.item = item;
    }

    public By getItem() {
        return item;
    }
}
