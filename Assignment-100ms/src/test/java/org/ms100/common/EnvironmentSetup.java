package org.ms100.common;

import java.util.Properties;

import com.ms100.driver.IDriver;
import com.ms100.interactions.*;
import com.ms100.io.*;
import com.ms100.selenium.driver.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.*;

public class EnvironmentSetup {
	String urlProps = "/src/test/resources/test_data/EnvironmentUrls.properties";

	public static String _mode = "", _myBrowser = "", _resolution = "", basePath = "", _env = "", _baseUrl = "", pathToScreenShots = "";

	public static Properties urls;

	public static IDriver idriver;
	public static WebDriver driver;
	public static IWaitOperations waitHandler;
	public static IQueryHtmlElement queryHandler;
	public static IWindowOperations windowHandler;
	public static IMouseOperations mouseHandler;
	public static IVerifications verificationsHandler;
	public static ScreenCapture screenshotHandler;
    public static IDropDownOperations dropDownHandler;
	public static IKeyboardOperations keyboardHandler;

	public static AjaxElementLocatorFactory factory;

	@BeforeClass
	@Parameters({ "myBrowser", "mode", "resolution", "env" })
	public void environmentSetUp(String myBrowser, String mode, String resolution, String env) {
		if (System.getProperty("mode") == null) {
			System.setProperty("mode", mode);
			_mode = mode;
		} else {
			_mode = System.getProperty("mode");
		}
		if (System.getProperty("myBrowser") == null) {
			_myBrowser = myBrowser;
		} else {
			_myBrowser = System.getProperty("myBrowser");
		}
		if (System.getProperty("resolution") == null) {
			_resolution = resolution;
		} else {
			_resolution = System.getProperty("resolution");
		}

		if (System.getProperty("env") != null) {
			_env = System.getProperty("env");
		} else {
			_env = env;
		}

		basePath = System.getProperty("user.dir");
		urls = PropertiesOps.readProperties(basePath + urlProps);

		if ("prod".equalsIgnoreCase(_env)) {
			_baseUrl = urls.getProperty("baseProdUrl");
		} else if ("stage".equalsIgnoreCase(_env)) {
			_baseUrl = urls.getProperty("baseStageUrl");
		} else if ("qa".equalsIgnoreCase(_env)) {
			_baseUrl = urls.getProperty("baseQaUrl");
		}
		
		pathToScreenShots = basePath + "/src/test/test-output/screenshots/";

		idriver = new Driver("src/main/resources/environmentConfig.properties");
		InitializeResources resources = new InitializeResources(idriver, myBrowser, _resolution);
		driver = resources.getDriver();
		waitHandler = resources.getWaitHandler();
		queryHandler = resources.getQueryHandler();
		windowHandler = resources.getWindowsHandler();
		mouseHandler = resources.getMouseHandler();
		verificationsHandler = resources.getVerificationHandler();
		screenshotHandler = resources.getScreenshotHandler();
        dropDownHandler = resources.getDropDownHandler();
		keyboardHandler = resources.getKeyboardHandler();
		factory = new AjaxElementLocatorFactory(driver, 10);
	}

	@AfterSuite
	public void tearDown() {
		idriver.quitDriver();
	}
}
