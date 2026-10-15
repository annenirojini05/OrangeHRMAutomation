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
            By.xpath("//h6[normalize-space()='Dashboard']");

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

        try {

            wait.until(
                    ExpectedConditions.urlContains(
                            "/dashboard"
                    )
            );

            return wait.until(
                    ExpectedConditions
                            .visibilityOfElementLocated(
                                    dashboardHeading
                            )
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public void openPIM() {

        wait.until(
                ExpectedConditions.urlContains(
                        "/dashboard"
                )
        );

        WebElement pim =
                wait.until(
                        ExpectedConditions
                                .elementToBeClickable(
                                        pimMenu
                                )
                );

        pim.click();
    }
}