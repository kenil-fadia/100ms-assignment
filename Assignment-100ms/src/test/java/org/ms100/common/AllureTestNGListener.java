package org.ms100.common;

import com.ms100.io.ScreenCapture;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureTestNGListener extends EnvironmentSetup implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public static byte[] saveScreenshotPNG() {
        if(driver instanceof WebDriver) {
            byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png", screenShot);
            return screenShot;
        }
        return null;
    }

    @Step("INFO::: {0}")
    public static String logInfo(String message) {
        saveScreenshotPNG();
        return message;
    }

    @Step("FAILURE::: {0}")
    public static String logFailure(String message) {
        Allure.getLifecycle().updateStep(stepResult -> stepResult.setStatus(Status.FAILED));
        saveScreenshotPNG();
        Assert.fail(message);
        return message;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshotPNG();
        logInfo(getTestMethodName(iTestResult) + " - Failed");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logInfo(getTestMethodName(iTestResult) + " - Passed");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        iTestContext.setAttribute("WebDriver", driver);
    }
}
