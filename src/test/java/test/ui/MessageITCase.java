package test.ui;

import main.ui.actions.PrepareDriver;
import main.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static main.ui.util.testData.*;

public class MessageITCase {
    static WebDriver driver;
    LoginPage myLoginPage;

    @BeforeEach
    public void setup() {
        driver = PrepareDriver.driverInit("chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL + "login");
        myLoginPage = new LoginPage(driver);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validMessageData")
    @DisplayName("Should add new message")
    public void addNewMessage(String title, String text, String path) {
        Map<String, String> message = new HashMap<>();
        message.put("title", title);
        message.put("text", text);
        message.put("filename", path.split("/")[1]);

        myLoginPage
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .sendMessage(title, text, path)
                .checkLastCreatedMessage(path, title, text, ADMIN_NAME, ADMIN_EMAIL, 0)
                .verifyParamsOfLastCreatedInstanceInDB("message", message);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validMessageData")
    @DisplayName("Should add new message without image")
    public void addNewMessageWithoutImage(String title, String text) {
        Map<String, String> message = new HashMap<>();
        message.put("title", title);
        message.put("text", text);
        message.put("filename", null);

        myLoginPage
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .sendMessage(title, text)
                .checkLastCreatedMessage("pic/no-img", title, text, ADMIN_NAME, ADMIN_EMAIL, 0)
                .verifyParamsOfLastCreatedInstanceInDB("message", message);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#sendMessageValidationTestData")
    @DisplayName("Should check validation errors when adding a message")
    public void checkValidationErrorsOnAddingMessage(String title, String text, String validationError) {
        myLoginPage
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .sendMessage(title, text)
                .checkErrorInForm("message", validationError);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#searchMessageData")
    @DisplayName("Should check search of message")
    public void checkSearchOfMessage(String searchQuery, String path, String title, String text, String userName, String userEmail, int likesCount) {
        myLoginPage
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .findTheMessage(searchQuery)
                .checkMessage(path, title, text, userName, userEmail, likesCount)
                .checkCountOfMessagesOnPage(1);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#searchMessageValidationTestData")
    @DisplayName("Should check error message when the search result is empty")
    public void checkErrorMessageWhenSearchResultIsEmpty(String searchQuery, String validationError) {
        myLoginPage
                .loginWithCredential(ADMIN_EMAIL, ADMIN_PASSWORD, false)
                .switchToMainPage()
                .findTheMessage(searchQuery)
                .checkErrorMessage(validationError);
    }

    @ParameterizedTest
    @DisplayName("Should check likes of message")
    public void checkMessageLikes() {
    }

    @ParameterizedTest
    @DisplayName("Should check the message change")
    public void checkMessageChange() {
    }

    @ParameterizedTest
    @DisplayName("Should check the message deletion")
    public void checkMessageDeletion() {
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#admin")
    @DisplayName("Should check changing the number of displayed messages on Main page")
    public void checkChangingNumberOfMessagesPerPageOnMainPage(String email, String password) {
        myLoginPage
                .loginWithCredential(email, password, false)
                .switchToMainPage()
                .checkCountOfMessagesOnPage(6)
                .changeMessagesCountPerPage(9)
                .checkCountOfMessagesOnPage(9)
                .changeMessagesCountPerPage(12)
                .checkCountOfMessagesOnPage(12);
    }

    @ParameterizedTest
    @MethodSource("main.ui.util.testData#validRegisterData")
    @DisplayName("Should check changing the number of displayed messages on My messages page")
    public void checkChangingNumberOfMessagesPerPageOnMessagesPage(String name, String surname, String email, String password) {
        myLoginPage
                .switchToRegisterPage()
                .register(name, surname, email, password)
                .loginWithCredential(email, password, false)
                .switchToMainPage()
                .sentRandomMessages(12)
                .switchToMessagesPage()
                .checkCountOfMessagesOnPage(6)
                .changeMessagesCountPerPage(9)
                .checkCountOfMessagesOnPage(9)
                .changeMessagesCountPerPage(12)
                .checkCountOfMessagesOnPage(12);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }
}
