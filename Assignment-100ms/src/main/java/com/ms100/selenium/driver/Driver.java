package com.ms100.selenium.driver;

import java.util.Properties;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;

import com.ms100.driver.IDriver;
import com.ms100.io.BasicFileOps;
import com.ms100.io.PropertiesOps;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver implements IDriver {
	BasicFileOps file = new BasicFileOps();
	Properties prop;
	private String _configPath;
	String browser;
	private WebDriver _driver;

	public Driver(String configPath) {
		_configPath = configPath;
		_driver = null;
		prop = PropertiesOps.readProperties(_configPath);
	}

	public Properties getConfigProperties() {
		return prop;
	}

	public Object getDriver(String browserName) {
		if (_driver == null) {
			this.initializeDriver(browserName);
		}
		return _driver;
	}

	public void initializeDriver(String browserName) {

		browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("parallel")) {
			browser = browserName;
		}

		if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("Google Chrome")
				|| browser.equalsIgnoreCase("GoogleChrome")) {
			ChromeOptions cOptions = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if ("headless".equalsIgnoreCase(System.getProperty("mode"))) {
				cOptions.setHeadless(true);
				cOptions.addArguments("--headless", "window-size=1920,1080", "--no-sandbox");
			}

			cOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
			cOptions.addArguments("disable-infobars");
			cOptions.addArguments("--start-maximized");
			cOptions.addArguments("--disable-notifications");
			cOptions.addArguments("use-fake-device-for-media-stream");
			cOptions.addArguments("use-fake-ui-for-media-stream");
			cOptions.addArguments("--auto-select-desktop-capture-source");

			_driver = new ChromeDriver(cOptions);

		} else if (browser.equalsIgnoreCase("safari")) {
			_driver = new SafariDriver();
		} else {
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions ffOptions = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			profile.setPreference("security.fileuri.strict_origin_policy", false);
			if ("headless".equalsIgnoreCase(System.getProperty("mode"))) {
				ffOptions.addArguments("--headless", "window-size=1920,1080");
				ffOptions.setHeadless(true);
			}
			ffOptions.setProfile(profile);
			ffOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
			ffOptions.addArguments("--disable-notifications");

			_driver = new FirefoxDriver(ffOptions);
		}
		_driver.manage().window().maximize();
	}

	public void quitDriver() {
		if (_driver != null) {
			_driver.quit();
		}
	}
}
