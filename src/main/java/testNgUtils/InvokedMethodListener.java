package testNgUtils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import static driver.SimpleDriver.getWebDriver;
/**Listener для скриншотов в Allure Reports*/
public class InvokedMethodListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!testResult.isSuccess() && getWebDriver() != null) {//если тест не прошел и если драйвер не пустой
            byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            saveScreenshots(screenshot);
        }
    }

    //скриншоты нужно прогнать через аттачмент чтобы они присоединились к allure reports
    @Attachment(value = "Screenshots", type = "image/png")
    private byte[] saveScreenshots(byte[] bytes) {
        return bytes;
    }
}
