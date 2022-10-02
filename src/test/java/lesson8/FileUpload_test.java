package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.FileUploadPage;
import pageObjects.herokuapp.NavigationPage;

import static pageObjects.herokuapp.NavigationItems.FILE_UPLOAD;

/**
 * File Upload
 * Загрузить файл
 * Проверить, что имя файла на странице совпадает с именем загруженного файла
 */

public class FileUpload_test extends BaseTest {
    @Parameters("url")
    @BeforeMethod
    public void precondition(String url) {
        new NavigationPage()
                .open(url);
    }

    @Test(priority = 2, description = " File upload test")
    public void test1() {
        new NavigationPage()
                .navigateTo(FILE_UPLOAD);
        new FileUploadPage()
                .FileUpload()
                .ClickUpload()
                .verifyFileName("unnamed.png");
    }
}