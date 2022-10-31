package pageObjects.rw_Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private final SelenideElement from = $("#acFrom");
    private final SelenideElement to = $(By.id("acTo"));
    private final SelenideElement date = $("#yDate");

    private final SelenideElement searchBtn = $("[class=std-button]");

    private SelenideElement getMiddleCalendar(String date) {
        return $(byXpath("//*[contains(@class, 'group-middle')]"))
                .should(Condition.visible)
                .find(By.xpath("table"))
                .find(By.linkText(date));
    }

    public HomePage enterFrom(String from) {
        this.from.sendKeys(from);
        return this;
    }

    public HomePage enterTo(String to) {
        this.to.sendKeys(to);
        return this;
    }

    public HomePage enterMiddleCalendar(String date) {
        this.date.click();
        this.getMiddleCalendar(date).click();
        return this;
    }

    public HomePage clickSearch() {
        this.searchBtn.should(Condition.enabled).click();
        return this;
    }
}
