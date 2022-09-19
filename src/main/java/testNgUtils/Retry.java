package testNgUtils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int attempt = 1;
    private static final int MAX_RETRY = 5; //количество повторений, может быть любым, но 5-ти обычно достаточно

    @Override
    public boolean retry(ITestResult iTestResult) { ///ITestResult содержание состояния тестовых результатов, те теста который сейчас выполняется
        if (!iTestResult.isSuccess()) {// нас интересует статус теста, спрашиваем пасс он или нет
            if (attempt < MAX_RETRY) {//если не пас, то спрашиваем какое состояние
                attempt++;//счетчик
                iTestResult.setStatus(ITestResult.FAILURE);//сетапит фейл
                System.out.println("Retrying once again");//печаетает
                return true;//повторяем опять
            } else {//если прошли 5 раз и тест все равно провалился, то выкатывает фейл всему тесту
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);///если он пасс(тру)
        }
        return false;///не повторяем
    }
}
