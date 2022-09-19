package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.FileUploadPage;
import pageObjects.herokuapp.NavigationPage;
import static pageObjects.herokuapp.NavigationItems.*;

/** File Upload
 Загрузить файл
 Проверить, что имя файла на странице совпадает с именем загруженного файла */

public class FileUpload_test extends BaseTest {

    @BeforeMethod
    public void precondition() {
        new NavigationPage()
                .open();
    }

    @Test
    public void test1() {
        new NavigationPage()
                .navigateTo(FILE_UPLOAD);
        new FileUploadPage()
                .FileUpload()
                .ClickUpload()
                .verifyFileName("unnamed.png");
    }
}