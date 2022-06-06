package com.ms100.selenium.interactions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ms100.interactions.IWaitOperations;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Timeout implements IWaitOperations {

    By elementLocator;
    int defaultTimeout;
    WebDriver driver;
    WebDriverWait waitTimeout;
    WebElement element;

    public Timeout(Object _driver) {
        defaultTimeout = 10;
        driver = (WebDriver) _driver;
    }

    public Timeout(Object _driver, int timeout) {
        defaultTimeout = timeout;
        driver = (WebDriver) _driver;
    }

    public boolean waitForElementToBeClickable(Object locator) {
        return waitForElementToBeClickable(locator, defaultTimeout);
    }

    public boolean waitForElementToBeClickable(Object locator, int timeout) {
        waitTimeout = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        try {
            elementLocator = (By) locator;
            waitTimeout.until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (ClassCastException e) {
            element = (WebElement) locator;
            waitTimeout.until(ExpectedConditions.elementToBeClickable(element));
        }

        return true;
    }

    public boolean waitForElementToBePresent(Object locator) {
        return waitForElementToBePresent(locator, defaultTimeout);
    }

    public boolean waitForElementToBePresent(Object locator, int timeout) {
        elementLocator = (By) locator;

        waitTimeout = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        waitTimeout.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        return true;
    }

    public boolean waitForInvisibilityOf(Object locator) {
        return waitForInvisibilityOf(locator, defaultTimeout);
    }

    public boolean waitForInvisibilityOf(Object locator, int timeout) {
        waitTimeout = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        try {
            elementLocator = (By) locator;
            waitTimeout.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
        } catch (ClassCastException e) {
            element = (WebElement) locator;
            waitTimeout.until(ExpectedConditions.invisibilityOf(element));
        }

        return true;
    }

    public boolean waitForNoOfWindows(int number) {
        waitTimeout = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitTimeout.until(ExpectedConditions.numberOfWindowsToBe(number));
        return true;
    }

    public boolean waitForVisibilityOf(Object locator) {
        return waitForVisibilityOf(locator, defaultTimeout);
    }

    public boolean waitForVisibilityOf(Object locator, int timeout) {
        waitTimeout = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        try {
            elementLocator = (By) locator;
            waitTimeout.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (ClassCastException e) {
            element = (WebElement) locator;
            waitTimeout.until(ExpectedConditions.visibilityOf(element));
        }

        return true;
    }

    @Step("Wait for page to load")
    public boolean waitForPageLoad() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
