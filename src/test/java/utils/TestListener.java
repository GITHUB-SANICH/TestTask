package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static tests.BaseTest.getDriver;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Allure.getLifecycle().addAttachment("Скриншшот упавшего теста", "image/png", "png", ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
    }
}
