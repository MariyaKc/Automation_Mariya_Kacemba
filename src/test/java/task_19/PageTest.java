package task_19;

import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;

/** Ввести значение в поле ввода
 Кликнуть на чекбокс
 Выбрать элемент из списка
 Кликнуть на кнопку
 Проверить что картинка существует
 Проверить текст тега р
 Кликнуть на ссылку и проверить что новая страница была открыта */

public class PageTest extends BaseTest {


    @Test(description = "Tests :: enter in inputs,click button, check checkbox, select element in dropdown, verify img, verify text,verify link ")
    public void pagTest(){
        get(TablePage.class).open()
          .verifyImg()
          .verifyTagP()
          .enterInput("username","123456")
          .clickBtn()
          .checkCheckbox()
          .selectByValue(3)
          .verifyTwitter();
    }
}
