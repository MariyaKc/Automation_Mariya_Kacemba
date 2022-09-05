package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.io.File;
import java.util.List;

public class FileDownloadPage extends BasePage {

    private final By fileLink = By.xpath("//div[@class='example']/a");

    public List<WebElement> downloadElementList() {
        return driver.findElements(fileLink);
    }

    public FileDownloadPage downloadElementByIndex(int index) throws InterruptedException {
        click(downloadElementList().get(index - 1));
        Thread.sleep(5000);
        return this;
    }

    public File getLastDownloadFileInDirectory() { // поиск последнего скачанного файла в директории
        File directory = new File(System.getProperty("user.dir"));
        File[] files = directory.listFiles(File::isFile);
        if (files == null || files.length == 0) {
            return null;
        }

        File lastDownloadFile = files[0];
        for (int i = 0; i < files.length; i++) {
            if (lastDownloadFile.lastModified() < files[i].lastModified()) {
                lastDownloadFile = files[i];
            }
        }
        System.out.println("I'm download :: " + lastDownloadFile.getName());
        return lastDownloadFile;
    }

    public FileDownloadPage verifyLastFileInDirectory() {
        File lastFile = getLastDownloadFileInDirectory();
        System.out.println("I'm found file :: " + lastFile.getName());
        Assert.assertTrue(lastFile.exists()); //проверка
        lastFile.deleteOnExit(); //удаление
        return this;
    }
}






