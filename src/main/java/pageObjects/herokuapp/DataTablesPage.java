package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


/**
 * Data Tables
 * 1. Реализовать методы по сбору данных из таблиц в формате List или Map
 * 2. Реализовать методы по проверке сортировки колонок
 */
public class DataTablesPage extends BasePage {

    private final By table1_body = By.xpath("//*[@id='table1']/tbody");
    private final By lastname = By.xpath("//*[@id='table1']/tbody/tr/td[1]");
    private final By firstname = By.xpath("//*[@id='table1']/tbody/tr/td[2]");
    private final By email = By.xpath("//*[@id='table1']/tbody/tr/td[3]");
    private final By due = By.xpath("//*[@id='table1']/tbody/tr/td[4]");
    private final By website = By.xpath("//*[@id='table1']/tbody/tr/td[5]");

    private final By sortLastname = By.xpath("//table[@id='table1']/thead/tr/th[1]/span");
    private final By sortFirstname = By.xpath("//table[@id='table1']/thead/tr/th[2]/span");
    private final By sortEmail = By.xpath("//table[@id='table1']/thead/tr/th[3]/span");
    private final By sortDue = By.xpath("//table[@id='table1']/thead/tr/th[4]/span");
    private final By sortWebsite = By.xpath("//table[@id='table1']/thead/tr/th[5]/span");
    private final By actions = By.xpath("//table[@id='table1']/thead/tr/th[6]/span");


    private DataTablesPage sortAscendingLastName() {
        Assert.assertEquals(sortAscending(lastname), getActualList(lastname));
        return this;
    }

    private DataTablesPage sortDescendingLastName() {
        Assert.assertEquals(sortDescending(lastname), getActualList(lastname));
        return this;
    }

    private DataTablesPage sortAscendingFirstName() {
        Assert.assertEquals(sortAscending(firstname), getActualList(firstname));
        return this;
    }

    private DataTablesPage sortDescendingFirstName() {
        Assert.assertEquals(sortDescending(firstname), getActualList(firstname));
        return this;
    }

    private DataTablesPage sortAscendingEmail() {
        Assert.assertEquals(sortAscending(email), getActualList(email));
        return this;
    }

    private DataTablesPage sortDescendingEmail() {
        Assert.assertEquals(sortDescending(email), getActualList(email));
        return this;
    }

    private DataTablesPage sortAscendingDue() {
        Assert.assertEquals(sortAscendingPrice(due), getActualList(due));
        return this;
    }


    private DataTablesPage sortDescendingDue() {
        Assert.assertEquals(sortDescendingPrice(due), getActualList(due));
        return this;
    }

    private DataTablesPage sortAscendingWebsite() {
        Assert.assertEquals(sortAscending(website), getActualList(website));
        return this;
    }

    private DataTablesPage sortDescendingWebsite() {
        Assert.assertEquals(sortDescending(website), getActualList(website));
        return this;
    }

    public DataTablesPage verifySorting(DataTablesItems sort) {
        switch (sort) {
            case LASTNAME_ASCENDING:
                click(actions);
                click(sortLastname);
                sortAscendingLastName();
                click(sortLastname);
                break;
            case LASTNAME_DESCENDING:
                click(actions);
                click(sortLastname);
                click(sortLastname);
                sortDescendingLastName();
                break;
            case FIRSTNAME_ASCENDING:
                click(actions);
                click(sortFirstname);
                sortAscendingFirstName();
                click(sortFirstname);
                break;
            case FIRSTNAME_DESCENDING:
                click(actions);
                click(sortFirstname);
                click(sortFirstname);
                sortDescendingFirstName();
                break;
            case EMAIL_ASCENDING:
                click(actions);
                click(sortEmail);
                sortAscendingEmail();
                click(sortEmail);
                break;
            case EMAIL_DESCENDING:
                click(actions);
                click(sortEmail);
                click(sortEmail);
                sortDescendingEmail();
                break;
            case DUE_ASCENDING:
                click(actions);
                click(sortDue);
                sortAscendingDue();
                click(sortDue);
                break;
            case DUE_DESCENDING:
                click(actions);
                click(sortDue);
                click(sortDue);
                sortDescendingDue();
                break;
            case WEBSITE_ASCENDING:
                click(actions);
                click(sortWebsite);
                sortAscendingWebsite();
                click(sortWebsite);
                break;
            case WEBSITE_DESCENDING:
                click(actions);
                click(sortWebsite);
                click(sortWebsite);
                sortDescendingWebsite();
                break;
        }
        return this;
    }
}
