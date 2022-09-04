package pageObjects.herokuapp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

public class ContextMenuPage extends BasePage {
    private By hotSpotMenu = By.id("hot-spot");

    public ContextMenuPage clickContext() {
        actions.contextClick(driver.findElement(hotSpotMenu)).build().perform();//клик правой кнопкой
        return this;
    }


    public ContextMenuPage verifyAlert(String expectedText) {
        Alert alert = driver.switchTo().alert();//переключение на алерт
        Assert.assertEquals(alert.getText(), expectedText); //проверить текст
        alert.accept();// закрыть
        return this;
    }
}
