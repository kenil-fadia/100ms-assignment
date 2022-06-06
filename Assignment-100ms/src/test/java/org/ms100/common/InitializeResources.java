package org.ms100.common;

import com.ms100.driver.IDriver;
import com.ms100.interactions.*;
import com.ms100.io.*;
import com.ms100.selenium.interactions.*;
import org.ms100.data.ScreenResolutions;
import org.openqa.selenium.WebDriver;

public class InitializeResources {

	Object driver;
	IMouseOperations mouseHandler;
	IWindowOperations windowHandler;
	IVerifications verificationHandler;
	IWaitOperations waitHandler;
	IQueryHtmlElement queryHandler;
	IKeyboardOperations keyboardHandler;
	ScreenCapture screenshotHandler;
	ExcelFileOps excelFileHandler;
	ScreenResolutions screenResolutions;
    IDropDownOperations dropDownHandler;

	public InitializeResources(IDriver idriver, String myBrowser, String resolution) {

		driver = idriver.getDriver(myBrowser);
		mouseHandler = new Mouse(driver);
		verificationHandler = new Verifications(driver);
		waitHandler = new Timeout(driver, 10);
		keyboardHandler = new Keyboard(driver);
		queryHandler = new QueryHtml(driver);
		excelFileHandler = new ExcelFileOps();
		screenResolutions = new ScreenResolutions();
		screenshotHandler = new ScreenCapture(driver);
        dropDownHandler = new DropDown(driver);
		if (verificationHandler.isStringMatch("hd", resolution)) {
			windowHandler = new BrowserWindow(driver, screenResolutions.fullHD.get("width"),
					screenResolutions.fullHD.get("height"));
		} else if (verificationHandler.isStringMatch("laptop", resolution)) {
			windowHandler = new BrowserWindow(driver, screenResolutions.laptop.get("width"),
					screenResolutions.laptop.get("height"));
		} else if (verificationHandler.isStringMatch("tablet-landscape", resolution)) {
			windowHandler = new BrowserWindow(driver, screenResolutions.tabletLandscape.get("width"),
					screenResolutions.tabletLandscape.get("height"));
		} else if (verificationHandler.isStringMatch("tablet-portrait", resolution)) {
			windowHandler = new BrowserWindow(driver, screenResolutions.tabletPortrait.get("width"),
					screenResolutions.tabletPortrait.get("height"));
		} else if (verificationHandler.isStringMatch("mobile", resolution)) {
			windowHandler = new BrowserWindow(driver, screenResolutions.mobile.get("width"),
					screenResolutions.mobile.get("height"));
		}
	}

	public WebDriver getDriver() {
		return (WebDriver)driver;
	}
	
	public IWindowOperations getWindowsHandler() {
		return windowHandler;
	}

	public IMouseOperations getMouseHandler() {
		return mouseHandler;
	}

	public IVerifications getVerificationHandler() {
		return verificationHandler;
	}

	public IWaitOperations getWaitHandler() {
		return waitHandler;
	}

	public IQueryHtmlElement getQueryHandler() {
		return queryHandler;
	}

	public IKeyboardOperations getKeyboardHandler() {
		return keyboardHandler;
	}

	public ExcelFileOps getExcelFileHandler() {
		return excelFileHandler;
	}
	
	public ScreenCapture getScreenshotHandler() {
		return screenshotHandler;
	}

    public IDropDownOperations getDropDownHandler() {
        return dropDownHandler;
    }
}
