package lesson13;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageFactory.moodpanda.HomePage;
import pageObjects.baseObjects.BaseTest;
import pageObjects.moodpanda.NavigationPage;
import pageObjects.moodpanda.SignUpPage;
import pageObjects.moodpanda.entity.SignUp;
import pageObjects.moodpanda.entity.SignUpBuilder;
public class SignUpValueObject_Test extends BaseTest {

    @Parameters({"url", "email", "password"})
    @Test
    public void login_Test(String url, String email, String password) {
        new HomePage().open(url);
        new NavigationPage().clickSignUp();

        SignUp signUp = new SignUp() {{ //реализация Value Object, передедача параметров через сетттеры
            setEmail(email);
            setCheckbox(true);
            setFirstName("First Name");
            setLastName("S");
            setPassword(password);
        }};

        new SignUpPage().enterData(signUp);
    }


    @Parameters({"url", "email", "password"})
    @Test
    public void loginBuilder_Test(String url, String email, String password) {
        new HomePage().open(url);
        new NavigationPage().clickSignUp();

        SignUpBuilder signUp = new SignUpBuilder.Builder()
                .withEmail(email)
                .withLastName("S")
                .withFirstName("First Name")
                .withCheckbox(false)
                .withPassword(password)
                .build();

        new SignUpPage().enterData(signUp);
    }
}
