package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


public class DropdownListPage extends BasePage {
    private final By dropDown = By.id("dropdown");
    private final By option1 = By.xpath("//option[@value='1']");
    private final By option2 = By.xpath("//option[@value='2']");

    public DropdownListPage selectByName (String value) {
        selectByText(dropDown,value);
        return this;
    }

    public DropdownListPage selectByIndex (int value) {
        selectByIndex(dropDown,value);
        return this;
    }

   public DropdownListPage verifyFirstOptionIsSelected(){
       Assert.assertTrue(driver.findElement(option1).isSelected());
        return this;
   }

    public DropdownListPage verifySecondOptionIsSelected(){
        Assert.assertTrue(driver.findElement(option2).isSelected());
        return this;
    }

}
