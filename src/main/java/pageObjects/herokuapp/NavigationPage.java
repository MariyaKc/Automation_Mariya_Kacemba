package pageObjects.herokuapp;

import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;

public class NavigationPage extends BasePage {
    private By getByLink(String textLink) {
        return By.partialLinkText(textLink);
    }

    public NavigationPage open() {
        driver.get("http://the-internet.herokuapp.com/");
        return this;
    }

    public void navigateTo(NavigationItems navigationItem) {
        click(getByLink(navigationItem.getItem()));
    }
}
