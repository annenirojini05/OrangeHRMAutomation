package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        String browser =
                System.getProperty(
                        "browser",
                        ConfigReader.getProperty("browser")
                ).toLowerCase();

        String baseUrl =
                System.getProperty(
                        "baseUrl",
                        ConfigReader.getProperty("baseUrl")
                );

        boolean headless =
                Boolean.parseBoolean(
                        System.getProperty(
                                "headless",
                                ConfigReader.getProperty("headless")
                        )
                );

        if (browser.equals("chrome")) {

            ChromeOptions options =
                    new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
            }

            driver =
                    new ChromeDriver(options);

        } else if (browser.equals("firefox")) {

            FirefoxOptions options =
                    new FirefoxOptions();

            if (headless) {
                options.addArguments("-headless");
            }

            driver =
                    new FirefoxDriver(options);
        }

        driver.manage()
                .window()
                .setSize(
                        new Dimension(
                                1366,
                                768
                        )
                );

        driver.get(baseUrl);

        System.out.println(
                "Browser: "
                        + browser
                        + " | Headless: "
                        + headless
                        + " | URL: "
                        + baseUrl
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}