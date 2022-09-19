package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.io.File;

public class FileUploadPage extends BasePage {

    private final By chooseFileBtn = By.id("file-upload");
    private final By uploadBtn = By.id("file-submit");
    private final By nameOfUploadedFile = By.id("uploaded-files");


    public FileUploadPage FileUpload(){
        File file= new File((System.getProperty("user.dir") + "/files/unnamed.png"));
        driver.findElement(chooseFileBtn).sendKeys(file.getAbsolutePath());
        return this;
    }

    public FileUploadPage ClickUpload(){
        click(uploadBtn);
        return this;
    }

    public FileUploadPage verifyFileName(String fileName){
        Assert.assertEquals(getText(nameOfUploadedFile),fileName);
        return this;
    }
}
