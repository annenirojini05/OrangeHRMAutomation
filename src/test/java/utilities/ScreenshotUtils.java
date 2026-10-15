package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        String timestamp =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyyMMdd_HHmmss"
                                )
                        );

        String path =
                "target/screenshots/"
                        + testName
                        + "_"
                        + timestamp
                        + ".png";

        File source =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(
                                OutputType.FILE
                        );

        File destination =
                new File(path);

        try {

            destination
                    .getParentFile()
                    .mkdirs();

            FileUtils.copyFile(
                    source,
                    destination
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to save screenshot",
                    e
            );
        }

        return path;
    }
}