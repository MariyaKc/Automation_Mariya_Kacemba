package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.baseObjects.BasePage;

public class DynamicLoadingPage extends BasePage {
    private By startBtn = By.id("start");
    private By loading = By.id("loading");
    private By finish = By.id("finish");

    private By getByLink(String textLink) {
        return By.partialLinkText(textLink);
    }


    public DynamicLoadingPage clickOnExample(String text) {
        click(getByLink(text));
        return this;
    }

    public DynamicLoadingPage clickOnStart() {
        click(startBtn);
        return this;
    }

    public String pageIsLoaded() {
        wait.until(ExpectedConditions.textToBe(loading, "Loading...")); //ждем, когда появится текст у элемента loading
        return getText(loading);
    }
}
