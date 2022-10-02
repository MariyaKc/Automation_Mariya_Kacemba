package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


public class DropdownListPage extends BasePage {
    private final By dropDown = By.id("dropdown");
    private By title = By.tagName("h3");
    private final By option1 = By.xpath("//option[@value='1']");
    private final By option2 = By.xpath("//option[@value='2']");

    public DropdownListPage verifyPageTitle(String text) {
        Assert.assertEquals(getText(title), text);
        return this;
    }

    public DropdownListPage select(Integer index) {
        super.select(dropDown, index);
        return this;
    }

    public DropdownListPage select(String value) {
        super.select(dropDown, value);
        return this;
    }

    public DropdownListPage verifySelectedValue(String value) {
        Select select = new Select(findElement(dropDown));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), value);
        return this;
    }
}
