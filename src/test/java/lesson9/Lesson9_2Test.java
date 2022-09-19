package lesson9;

import com.sun.net.httpserver.Authenticator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;
import org.testng.util.RetryAnalyzerCount;
import testNgUtils.Retry;


public class Lesson9_2Test {

    int count = 1;
    int timeout = 4;

    @Test(enabled = false, priority = 1)//задизейблен
    public void test1() {
        System.out.println("Hello i'am test 1");
    }

    @Test(enabled = true, priority = 2) //видимый, необзяательно проставлять тк по умолчанию видим
    public void test2() {
        System.out.println("Hello i'am test 2");
    }

    //тест упадет потому что не выполнится за таймаут из-за паузы
    //описание теста для отчета
    @Test(timeOut = 1000, description = "Test 3 from lecture 9", priority = 3)
    public void test3() {
        pause(2);
        System.out.println("Hello i'am test 3");
    }
   //скипнется, потому что зависит от 3 теста, а он падает. Ускоряет процесс падения тестов
    @Test(dependsOnMethods = "test3", priority = 4)
    public void test4() {
        System.out.println("Hello i'am test 4");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Hello i'am  @BeforeMethod 5 and this is my repeat #" + count);
    }

    //повторит тест столько раз, сколько указано threadPoolSize - 5 потоков
    @Test(invocationCount = 5, threadPoolSize = 5, priority = 5)
    public void test5() {
        pause(1);
        System.out.println("Hello i'am test 5 and this is my repeat #" + count++);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Hello i'am @AfterMethod 5 and this is my repeat #" + count);
    }

    @Test(timeOut = 1000, priority = 6, retryAnalyzer = Retry.class)
    public void test6() {
        timeout--;//с каждым тестом таймаут будет отниматься, те первый 4, потом 3, потом 2, потом 1 и на нем он пройдет(так пройдет с 5 ой попытки, когла таймаут уйдет в 0, потмоу что бещ паузы он не ждет, пока время отминусуется
        pause(timeout);//с этой штукой с 4
        System.out.println("Hello i'am test 6 with timeout #" + timeout);
    }

    private void pause(long timeout) { // зыбит поток на определнное время
        try {
            Thread.sleep(timeout * 1000); //чекед исключение.все что связано с этим классом чекед исключение. нужно трай кеч или сигнатура с ошибкой.удобнее трай кеч
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
