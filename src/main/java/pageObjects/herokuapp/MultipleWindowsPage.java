package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


public class MultipleWindowsPage extends BasePage {

    private final By clickHereBtn = By.linkText("Click Here");
    private final String handleHomeTab = driver.getWindowHandle();// текущий дескриптор окна


    public MultipleWindowsPage clickHere() {
        click(clickHereBtn);
        return this;
    }

    public MultipleWindowsPage switchToNewTab() {
        driver.getWindowHandles().forEach(newHandle ->
                driver.switchTo().window(newHandle));
        return this;
    }

    public MultipleWindowsPage verifyText() {
        Assert.assertEquals(driver.findElement(By.tagName("h3")).getText(), "New Window");
        return this;
    }

    public MultipleWindowsPage returnToHomeTab(){
        driver.close();
        driver.switchTo().window(handleHomeTab);
        return this;
    }


    public MultipleWindowsPage verifyNewTabIsClosed() {
        Assert.assertTrue(driver.getWindowHandles().size()==1);
        return this;
    }
}

