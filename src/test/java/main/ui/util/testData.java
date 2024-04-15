package main.ui.util;

import com.github.javafaker.Faker;
import main.ui.pages.BasePage;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class testData {
    public static Faker faker = new Faker();
    public static final String URL = "http://localhost:8082/";
    public static final String NAME = faker.name().firstName();
    public static final String SURNAME = faker.name().lastName();
    public static final String PASSWORD = faker.internet().password();
    public static final String EMAIL = BasePage.getUserEmail(NAME, SURNAME);

    public static Stream<Arguments> loginValidationTestData() {
        return Stream.of(
                Arguments.of("", "1", "Email cannot be empty"),
                Arguments.of("1", "1", "Email is not correct"),
                Arguments.of("test@mail.com", "", "Password cannot be empty")
        );
    }

    public static Stream<Arguments> validRegisterData() {
        return Stream.of(
                Arguments.of(NAME, SURNAME, EMAIL, PASSWORD, true)
        );
    }

    public static Stream<Arguments> invalidCaptchaRegisterData() {
        return Stream.of(
                Arguments.of(NAME, SURNAME, EMAIL, PASSWORD, false)
        );
    }

    public static Stream<Arguments> registerValidationTestData() {
        return Stream.of(
                Arguments.of("", SURNAME, EMAIL, PASSWORD, false, "Name cannot be empty"),
                Arguments.of(NAME, "", EMAIL, PASSWORD, false, "Surname cannot be empty"),
                Arguments.of(NAME, SURNAME, "", PASSWORD, false, "Email cannot be empty"),
                Arguments.of(NAME, SURNAME, "1", PASSWORD, false, "Email is not correct"),
                Arguments.of(NAME, SURNAME, EMAIL, "", false, "Password cannot be empty")
//                Arguments.of("Tom", "Anderson", "t1@gmail.com", "1111", true, "User exists!")
        );
    }
}