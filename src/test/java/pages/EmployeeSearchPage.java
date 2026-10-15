package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EmployeeSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By employeeNameField =
            By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");

    private By employeeIdField =
            By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div//input");

    private By searchButton =
            By.xpath("//button[@type='submit']");

    private By autocompleteOptions =
            By.cssSelector(".oxd-autocomplete-dropdown .oxd-autocomplete-option");

    private By resultRows =
            By.cssSelector(".oxd-table-body .oxd-table-card");

    private By noRecordsMessage =
            By.xpath("//*[contains(text(),'No Records Found')]");

    public EmployeeSearchPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void enterEmployeeName(String employeeName) {

        WebElement field = wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(employeeNameField)
        );

        field.clear();
        field.sendKeys(employeeName);
    }

    public void selectFirstEmployeeSuggestion() {

        wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(autocompleteOptions)
        );

        List<WebElement> options =
                driver.findElements(autocompleteOptions);

        if (!options.isEmpty()) {
            options.get(0).click();
        }
    }

    public void searchEmployeeByName(String employeeName) {

        enterEmployeeName(employeeName);

        selectFirstEmployeeSuggestion();

        clickSearch();
    }

    public void enterEmployeeId(String employeeId) {

        WebElement field = wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(employeeIdField)
        );

        field.clear();
        field.sendKeys(employeeId);
    }

    public void clickSearch() {

        wait.until(
                ExpectedConditions
                        .elementToBeClickable(searchButton)
        ).click();
    }

    public int getResultCount() {

        // Allow search result area to update
        wait.until(driver ->
                !driver.findElements(resultRows).isEmpty()
                        ||
                        !driver.findElements(noRecordsMessage).isEmpty()
        );

        return driver
                .findElements(resultRows)
                .size();
    }

    public boolean isNoRecordsDisplayed() {

        try {

            wait.until(driver ->
                    !driver.findElements(noRecordsMessage).isEmpty()
                            ||
                            driver.findElements(resultRows).isEmpty()
            );

            return driver.findElements(resultRows).isEmpty();

        } catch (Exception e) {

            return false;
        }
    }
}