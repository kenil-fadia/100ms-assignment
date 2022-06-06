package com.ms100.selenium.interactions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ms100.interactions.IQueryHtmlElement;

public class QueryHtml implements IQueryHtmlElement {

    Timeout _timeout;
    WebDriver _driver;
    WebElement _element;
    ExecuteJavascript js;

    public QueryHtml(Object driver) {
        _driver = (WebDriver) driver;
        _timeout = new Timeout(_driver);
        _element = null;
        js = new ExecuteJavascript(driver);
    }

    public Object findElement(Object locator) {
        _timeout.waitForElementToBePresent(locator);

        return _driver.findElement((By) locator);
    }

    public List<Object> findElements(Object locator) {
        _timeout.waitForElementToBePresent(locator);

        List<WebElement> elements = _driver.findElements((By) locator);
        List<Object> webElements = new ArrayList<Object>(elements.size());
        for (WebElement element : elements) {
            webElements.add(element);
        }

        return webElements;
    }

    public List<Object> findElements(Object locator, int timeout) {
        _timeout.waitForElementToBePresent(locator, timeout);

        List<WebElement> elements = _driver.findElements((By) locator);
        List<Object> webElements = new ArrayList<Object>(elements.size());
        for (WebElement element : elements) {
            webElements.add(element);
        }

        return webElements;
    }

    public Object findElementWithinElement(Object element, Object locator) {
        WebElement ele = castLocator(element);
        By loc = (By) locator;

        return ele.findElement(loc);
    }

    public String getAttributes(Object locator, String attributeName) {
        _element = castLocator(locator);
        return _element.getAttribute(attributeName);
    }

    public String getBrowserName() {
        Capabilities cap = ((RemoteWebDriver) _driver).getCapabilities();
        return cap.getBrowserName();
    }

    public Object getCoordinates(Object locator) {
        _element = castLocator(locator);
        return _element.getLocation();
    }

    public Object getXCoordinates(Object locator) {
        return ((Point) getCoordinates(locator)).x;
    }

    public Object getYCoordinates(Object locator) {
        return ((Point) getCoordinates(locator)).y;
    }

    public String getCss(Object locator, String propertyName) {
        _element = castLocator(locator);
        return _element.getCssValue(propertyName);
    }

    public Object getDimensionsOfElementLocatedBy(Object locator) {
        _element = castLocator(locator);
        return _element.getSize();
    }

    public Object getHeightOfElement(Object locator) {
        return ((Dimension) getDimensionsOfElementLocatedBy(locator)).getHeight();
    }

    public Object getWidthOfElement(Object locator) {
        return ((Dimension) getDimensionsOfElementLocatedBy(locator)).getWidth();
    }

    public String getText(Object locator) {
        int attempts = 3;
        _element = castLocator(locator);

        while (attempts > 0) {
            try {
                return _element.getText();
            } catch (StaleElementReferenceException e) {
                attempts--;
            }
        }
        return null;
    }

    public String getTitle() {
        return _driver.getTitle();
    }

    public String getUrl() {
        return _driver.getCurrentUrl();
    }

    public WebElement castLocator(Object locator) {
        WebElement ele;

        try {
            ele = (WebElement) locator;
        } catch (ClassCastException e) {
            ele = (WebElement) findElement(locator);
        }

        return ele;
    }

    public void removeDomElement(Object locator) {
        WebElement ele = castLocator(locator);

        js.executeScript("$(arguments[0].remove())", ele);
    }
}
