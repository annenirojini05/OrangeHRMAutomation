package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.cssSelector(".oxd-alert-content-text");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public boolean isLoginButtonDisplayed() {
        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                loginButton
                        )
        ).isDisplayed();
    }

    public void enterUsername(String username) {
        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(usernameField)
        ).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(passwordField)
        ).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(
                ExpectedConditions
                        .elementToBeClickable(loginButton)
        ).click();
    }

    public void login(String username,
                      String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(errorMessage)
        ).getText();
    }
}