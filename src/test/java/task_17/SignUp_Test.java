package task_17;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.SignUpPage;
import pageObjects.moodpanda_Selenide.lombok.SignUp;
import pageObjects.moodpanda_Selenide.navigation.NavigationPage;

import java.lang.reflect.Method;

/**
 * Написать 3 теста регистрацию
 */
public class SignUp_Test extends SelenideBaseTest {

    @BeforeTest
    public void precondition() {
        get(NavigationPage.class).clickSignUp();
    }

    @Test(description = "Sign up tests with an empty one of the required fields ", dataProvider = "data")
    public void negativeSignUpTest(String firstname, String lastname, String email, String password, Boolean check, String alert, String required) {

        SignUp signUp = new SignUp.SignUpBuilder()
                .withFirstName(firstname)
                .withLastName(lastname)
                .withEmail(email)
                .withPassword(password)
                .withCheckbox(check).create();

        get(SignUpPage.class).checkInformingText().enterData(signUp).clickSignUpMe();
        if (signUp.getCheckbox() == false) {
            get(SignUpPage.class).checkAlertIfCheckboxUncheck().checkAlertValidation(alert);
        } else if (signUp.getCheckbox() == true) {
            get(SignUpPage.class).checkAlertValidation(alert).checkAlertFiledRequired(required);
        }
    }

    @Test(description = "Sign up tests with all required fields", dataProvider = "data")
    public void positiveSignUpTest(String firstname, String lastname, String email, String password, Boolean check, String alert) {

        SignUp signUp = new SignUp.SignUpBuilder()
                .withFirstName(firstname)
                .withLastName(lastname)
                .withEmail(email)
                .withPassword(password)
                .withCheckbox(check).create();

        get(SignUpPage.class).checkInformingText()
                .enterData(signUp).clickSignUpMe()
                .checkAlertValidation(alert);
    }

    @DataProvider(name = "data")
    public Object[][] data(Method method) {
        Object[][] result = null;
        if (method.getName().equals("negativeSignUpTest")) {
            result = new Object[][]{
                    {"FirstName", "L", "test@test.ru", "123456789987", false, "One or more validation errors occurred.", ""},
                    {"", "L", "test@test.ru", "123456789987", true, "One or more validation errors occurred.", "First name is required"},
                    {"FirstName", "", "test@test.ru", "123456789987", true, "One or more validation errors occurred.", "Last name initial is required"},
                    {"FirstName", "L", "", "123456789987", true, "One or more validation errors occurred.", "Email address is required"},
                    {"FirstName", "L", "test@test.ru", "", true, "One or more validation errors occurred.", "Password is required"},
                    {"", "", "", "", false, "One or more validation errors occurred.", ""}};
        } else if (method.getName().equals("positiveSignUpTest")) {
            result = new Object[][]{
                    {properties.getProperty("firstName"), properties.getProperty("lastNameInitial"), properties.getProperty("email"), properties.getProperty("password"), true, "You already have an account, or this account cannot be created"}
            };
        }
        return result;
    }
}