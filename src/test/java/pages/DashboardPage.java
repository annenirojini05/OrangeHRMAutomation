package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By dashboardHeading =
            By.xpath("//h6[text()='Dashboard']");

    private By pimMenu =
            By.xpath("//span[normalize-space()='PIM']");

    public DashboardPage(WebDriver driver) {

        this.driver = driver;

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );
    }

    public boolean isDashboardDisplayed() {

        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                dashboardHeading
                        )
        ).isDisplayed();
    }

    public void openPIM() {

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                dashboardHeading
                        )
        );

        WebElement pim =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        pimMenu
                                )
                );

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(
                                pim
                        )
        ).click();
    }
}