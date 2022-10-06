package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


public class InputsPage extends BasePage {

    private final WebElement input = driver.findElement(By.tagName("input"));

    public InputsPage sendKeysAndVerify(String value) {
        input.clear();
        input.sendKeys(value);
        Assert.assertEquals(value, input.getAttribute("value"));
        return this;
    }


    public InputsPage setUpAndVerify(int startValue, int numberOfIteration) {
        String valueFromInput = input.getAttribute("value");
        for (int i = 0; i <= numberOfIteration; i++) {
            input.sendKeys(Keys.UP);
            int expectedValue = startValue;
            String expectedStrValue = String.valueOf(expectedValue);
            Assert.assertEquals(valueFromInput, expectedStrValue);
            expectedValue++;
        }
        return this;
    }

    public InputsPage setDownAndVerify(int startValue, int numberOfIteration) {
        String valueFromInput = input.getAttribute("value");
        for (int i = numberOfIteration; i >= 0; i--) {
            int expectedValue = startValue;
            input.sendKeys(Keys.DOWN);
            String expectedStrValue = String.valueOf(expectedValue);
            Assert.assertEquals(valueFromInput, expectedStrValue);
            expectedValue--;
        }
        return this;
    }
}
