package main.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='auth-email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@id='auth-password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@id='auth-name']")
    private WebElement nameInput;
    @FindBy(xpath = "//input[@id='auth-surname']")
    private WebElement surnameInput;
    @FindBy(xpath = "//button[@id='auth-button']")
    private WebElement signUpButton;

    public RegistrationPage clickOnSignUp() {
        signUpButton.click();
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public RegistrationPage typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public RegistrationPage typeName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    public RegistrationPage typeSurname(String surname) {
        surnameInput.sendKeys(surname);
        return this;
    }

    public RegistrationPage register(String name, String surname, String email, String password) {
        this.typeName(name);
        this.typeSurname(surname);
        this.typeEmail(email);
        this.typePassword(password);
        clickOnSignUp();
        return this;
    }
}
