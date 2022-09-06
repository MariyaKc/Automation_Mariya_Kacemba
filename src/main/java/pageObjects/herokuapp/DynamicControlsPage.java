package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

public class DynamicControlsPage extends BasePage {
    private final By checkBox = By.id("checkbox");
    private final By removeBtn = By.xpath("//button[text()='Remove']");
    private final By input = By.xpath("//input[@type='text']");
    private final By enableBtn = By.xpath("//button[text()='Enable']");
    private final By GoneMessage = By.xpath("//p[contains(text(), 'gone')]");
    private final By EnabledMessage = By.xpath("//p[contains(text(), 'enable')]");

    public DynamicControlsPage verifyCheckBoxIsFind(){
        Assert.assertTrue(driver.findElement(checkBox).isDisplayed());
        return this;
    }

    public DynamicControlsPage clickRemove(){
        click(removeBtn);
        return this;
    }

    public DynamicControlsPage waitGoneMessage(String expectedMessage){
        wait.until(ExpectedConditions.textToBe(GoneMessage, expectedMessage));
        return this;
    }

    public DynamicControlsPage verifyCheckBoxIsNotExist(){
        //долгое ожидание у обоих методов
        //Assert.assertEquals(driver.findElements(checkBox).size(),0);
        //wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(checkBox)));

       fluentWait(30,1).until(driver->ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(checkBox)));
        return this;
    }

    public DynamicControlsPage verifyInputIsFind(){
        Assert.assertTrue(driver.findElement(input).isDisplayed());
        return this;
    }

    public DynamicControlsPage verifyInputIsDisabled(){
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(input)));
        return this;
    }

    public DynamicControlsPage clickEnable(){
        click(enableBtn);
        return this;
    }

    public DynamicControlsPage waitEnabledMessage(String expectedMessage){
        wait.until(ExpectedConditions.textToBe(EnabledMessage,expectedMessage));
        return this;
    }

    public DynamicControlsPage verifyInputIsEnabled(){
        wait.until(ExpectedConditions.elementToBeClickable(input));
        return this;
    }
}
