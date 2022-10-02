package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.List;

public class AddRemoveElementsPage extends BasePage {

    private final By addBtn = By.xpath("//button[text()='Add Element']");
    private final By deleteBtn = By.xpath("//button[text()='Delete']");

    public AddRemoveElementsPage clickAddElement(int countClick) {
        for (int i = 0; i < countClick; i++) {
            click(addBtn);
        }
        return this;
    }

    public AddRemoveElementsPage clickDelete(int countClick) {
        for (int i = 0; i < countClick; i++) {
            click(deleteBtn);
        }
        return this;
    }

    public AddRemoveElementsPage verifyAddBtnDelete() {
        Assert.assertTrue(driver.findElement(deleteBtn).isDisplayed());
        return this;
    }

    public AddRemoveElementsPage verifyQuantityDeleteBtn() {
        List<WebElement> deleteBtnList = driver.findElements(deleteBtn);
        Assert.assertEquals(deleteBtnList.size(), 6);
        return this;
    }

    public AddRemoveElementsPage verifyDeleteBtnNotExist() {
        fluentWait(20, 1).until((driver -> ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(deleteBtn))));
        return this;
    }


}
