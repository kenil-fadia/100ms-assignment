package com.ms100.selenium.interactions;

import java.awt.Toolkit;
import java.util.Set;

import com.ms100.interactions.IExecuteJavascript;
import com.ms100.interactions.IWindowOperations;
import com.ms100.io.TextFileOps;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class BrowserWindow implements IWindowOperations {

    protected IExecuteJavascript js;
    protected int attempts;
    protected QueryHtml query;
    protected String jquery = "", jquerySimulations = "";
    protected TextFileOps textFile;
    protected Timeout timeout;
    protected WebDriver _driver;

    public BrowserWindow(Object driver) {
        _driver = (WebDriver) driver;
        js = new ExecuteJavascript(_driver);
        query = new QueryHtml(_driver);
        textFile = new TextFileOps(_driver);
        readJsFiles();
        timeout = new Timeout(_driver);
    }

    public BrowserWindow(Object driver, int width, int height) {
        _driver = (WebDriver) driver;
        js = new ExecuteJavascript(_driver);
        query = new QueryHtml(_driver);
        textFile = new TextFileOps(_driver);
        readJsFiles();
        setSize(width, height);
        timeout = new Timeout(_driver);
    }

    public String[] getAllWindowHandles() {
        Set<String> handles = _driver.getWindowHandles();
        return handles.toArray(new String[handles.size()]);
    }

    public Object getSize() {
        return _driver.manage().window().getSize();
    }

    public boolean injectSimulations() {

        js.executeScript(jquery);
        js.executeScript(jquerySimulations);
        return true;
    }

    public boolean maximizeWindow() {
        _driver.manage().window().maximize();
        return true;
    }

    public boolean navigateBack() {
        _driver.navigate().back();
        injectSimulations();
        return true;
    }

    public boolean open(String url) {
        attempts = Integer.parseInt(System.getProperty("numOfAttempts", "3"));

        while (attempts > 0) {
            try {
                _driver.get(url);
                return true;
            } catch (Exception e) {
            	e.printStackTrace();
                --attempts;
            }
        }
        return false;
    }

    private void readJsFiles() {
        String path = "./src/main/resources";
        jquery = textFile.readJsFiles(path + "/jquery/jquery.min.js");
        jquery += textFile.readJsFiles(path + "/jquery/jquery-ui-1.11.4.min.js");
        jquerySimulations = textFile.readJsFiles(path + "/dragdrop/jquery-simulate.js");
        jquerySimulations += textFile.readJsFiles(path + "/dragdrop/bililiteRange.js");
        jquerySimulations += textFile.readJsFiles(path + "/dragdrop/jquery-simulate-key-combo.js");
        jquerySimulations += textFile.readJsFiles(path + "/dragdrop/jquery-simulate-key-sequence.js");
    }

    public boolean refresh() {
        _driver.navigate().refresh();
        return true;
    }

    public boolean setSize(int width, int height) {
    	java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	if (width == screenSize.width && height == screenSize.height) {
    		_driver.manage().window().maximize();
    	} else {
    		_driver.manage().window().setSize(new Dimension(width, height));
    	}
        return true;
    }

    public boolean switchToDefault() {
        _driver.switchTo().defaultContent();
        return true;
    }

    public boolean switchToNewFrame(Object element) {
        timeout.waitForElementToBePresent(element);
        _driver.switchTo().frame(query.castLocator(element));
        return true;
    }

    public boolean switchToNewFrame(Object element, int period) {
        timeout.waitForElementToBePresent(element, period);
        _driver.switchTo().frame(query.castLocator(element));
        return true;
    }

    public boolean switchToNewFrameByIndex(int frameIndex) {
        _driver.switchTo().frame(frameIndex);
        return true;
    }

    public boolean switchToNewFrameByName(String frameName) {
        _driver.switchTo().frame(frameName);
        return true;
    }

    public boolean switchToWindow(String windowHandle) {
        _driver.switchTo().window(windowHandle);
        return true;
    }

    public String getCurrentWindowHandle() {
        return _driver.getWindowHandle();
    }

    public void checkAndInjectSimulations() {
        try {
            js.executeScript("$('body').simulate()");
        } catch (WebDriverException e) {
            String message = e.getMessage();
            if (message.contains("simulate is not a function") || message.contains("$ is not defined")) {
                injectSimulations();
            }
        }
    }

    public boolean closeWindow() {
        _driver.close();
        return true;
    }
}
