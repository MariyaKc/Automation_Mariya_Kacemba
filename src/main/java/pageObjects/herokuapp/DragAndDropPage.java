package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class DragAndDropPage extends BasePage {

    private final WebElement elementA = driver.findElement(By.id("column-a"));
    private final WebElement elementB = driver.findElement(By.id("column-b"));

    /**
     * Срабатывает, только если в момент выполнения теста курсор находится там, куда нужно перетащить элемент
     */
    public DragAndDropPage FromAToB() throws IOException {
        //actions.dragAndDrop(elementA, elementB).build().perform();
        String filePath = System.getProperty("user.dir") + "/files/drag_and_drop_helper.js";
        StringBuffer buffer = new StringBuffer();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        while ((line = bufferedReader.readLine()) != null)
            buffer.append(line);
        String javaScript = buffer.toString();

        javaScript = javaScript + "$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});";
        ((JavascriptExecutor) driver).executeScript(javaScript);

        return this;
    }

    public DragAndDropPage FromBToA() throws IOException {

        String filePath = System.getProperty("user.dir") + "/files/drag_and_drop_helper.js";
        StringBuffer buffer = new StringBuffer();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null)
            buffer.append(line);
        String javaScript = buffer.toString();

        javaScript = javaScript + "$('#column-b').simulateDragDrop({ dropTarget: '#column-a'});";
        ((JavascriptExecutor) driver).executeScript(javaScript);

        return this;
    }

    public DragAndDropPage verifyChangeA() {
        Assert.assertTrue(elementA.getText().equals("B"));
        return this;
    }

    public DragAndDropPage verifyChangeB() {
        Assert.assertTrue(elementB.getText().equals("B"));
        return this;
    }


}
