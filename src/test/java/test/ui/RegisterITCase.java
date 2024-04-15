package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.LoginPage;
import main.ui.pages.RegistrationPage;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static main.ui.util.testData.URL;

public class RegisterITCase {
    static WebDriver driver;
    LoginPage myLoginPage;
    RegistrationPage myRegistrationPage;

    @BeforeEach
    public void setup() {
        driver = PrepareDriver.driverInit("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL + "login");
        myLoginPage = new LoginPage(driver);
        myRegistrationPage = new RegistrationPage(driver);
    }

    @ParameterizedTest
    @Ignore
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should register new user")
    public void registerNewUser(String name, String surname, String email, String password, boolean reCaptcha) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password, reCaptcha);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#invalidCaptchaRegisterData")
    @DisplayName("Should check captcha error on registration")
    public void checkCaptchaErrorOnRegistration(String name, String surname, String email, String password, boolean reCaptcha) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password, reCaptcha)
                .checkReCaptchaError();
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#registerValidationTestData")
    @DisplayName("Should check validation errors on registration")
    public void checkValidationErrorsOnRegistration(String name, String surname, String email, String password, boolean reCaptcha, String validationError) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password, reCaptcha)
                .checkErrorInForm("register", validationError);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}