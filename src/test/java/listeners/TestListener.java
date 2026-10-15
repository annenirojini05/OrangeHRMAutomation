package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utilities.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object instance = result.getInstance();

        if (instance instanceof BaseTest) {

            BaseTest test =
                    (BaseTest) instance;

            if (test.getDriver() != null) {

                ScreenshotUtils.captureScreenshot(
                        test.getDriver(),
                        result.getName()
                );
            }
        }
    }
}