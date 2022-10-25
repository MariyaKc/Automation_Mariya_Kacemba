package pageObjects.moodpanda_Selenide.navigation;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public enum LinksEnum {
    Home ($(By.partialLinkText("Home"))),
    What ($(By.partialLinkText("What"))),
    How ($(By.partialLinkText("How"))),
    About ($(By.partialLinkText("About"))),
    Contact ($(By.partialLinkText("Contact")));

    private SelenideElement element;

    LinksEnum(SelenideElement element) {
        this.element = element;
    }

    public SelenideElement getElement() {
        return element;
    }
}

