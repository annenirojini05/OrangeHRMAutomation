package tests;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);

        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.login(
                "Admin",
                "admin123"
        );

        Assert.assertTrue(
                dashboardPage
                        .isDashboardDisplayed(),
                "Dashboard was not displayed"
        );
    }

    @Test(groups = {"regression"})
    public void invalidLoginTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "wrongUser",
                "wrongPassword"
        );

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials"
        );
    }

    @Test
    public void loginPageResponsiveCheck() {

        driver.manage()
                .window()
                .setSize(
                        new Dimension(
                                375,
                                812
                        )
                );

        LoginPage loginPage =
                new LoginPage(driver);

        Assert.assertTrue(
                loginPage.isLoginButtonDisplayed(),
                "Login button is not visible"
        );
    }
}