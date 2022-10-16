package pageObjects.moodpanda;

import org.openqa.selenium.By;
import pageObjects.baseObjects.BasePage;

public class NavigationPage extends BasePage {

    private By signUpBtn = By.partialLinkText("Sign up");

    public NavigationPage clickSignUp() {
        click(signUpBtn);
        return this;
    }
}
