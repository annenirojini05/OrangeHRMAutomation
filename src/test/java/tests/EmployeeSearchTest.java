package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeSearchPage;
import pages.LoginPage;

public class EmployeeSearchTest
        extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void searchExistingEmployeeTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        DashboardPage dashboardPage =
                new DashboardPage(driver);

        EmployeeSearchPage employeeSearchPage =
                new EmployeeSearchPage(driver);

        loginPage.login(
                "Admin",
                "admin123"
        );

        dashboardPage.openPIM();

        employeeSearchPage
                .searchEmployeeByName("a");

        Assert.assertTrue(
                employeeSearchPage.getResultCount() > 0,
                "No employee record was found"
        );
    }

    @Test
    public void searchInvalidEmployeeTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        DashboardPage dashboardPage =
                new DashboardPage(driver);

        EmployeeSearchPage employeeSearchPage =
                new EmployeeSearchPage(driver);

        loginPage.login(
                "Admin",
                "admin123"
        );

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Login did not reach the OrangeHRM dashboard"
        );

        dashboardPage.openPIM();

        employeeSearchPage
                .enterEmployeeId(
                        "999999999"
                );

        employeeSearchPage.clickSearch();

        Assert.assertTrue(
                employeeSearchPage
                        .isNoRecordsDisplayed(),
                "Expected no employee records"
        );
    }
}