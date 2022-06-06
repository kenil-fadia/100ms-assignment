package com.ms100.selenium.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ms100.interactions.IExecuteJavascript;
import com.ms100.interactions.IKeyboardOperations;
import com.ms100.interactions.IWindowOperations;

public class Keyboard implements IKeyboardOperations {

	private Actions _action;
	private IExecuteJavascript _js;
	private int _attempts;
	private QueryHtml _query;
	private String _script;
	private WebDriver _driver;
	private WebElement _element;
	private Verifications _verify;
	private Mouse _mouse;
	private IWindowOperations _browser;

	public Keyboard(Object driver) {
		_driver = (WebDriver) driver;
		_action = new Actions(_driver);
		_action.build();
		_js = new ExecuteJavascript(_driver);
		_query = new QueryHtml(_driver);
		_verify = new Verifications(driver);
		_mouse = new Mouse(_driver);
		_browser = new BrowserWindow(driver);
	}

	public boolean multipleKeyStrokes(Object locator, String keys) {
		_element = _query.castLocator(locator);

		_browser.checkAndInjectSimulations();
		_js.executeScript("$(arguments[0]).simulate('key-combo', {combo: arguments[1]})", _element, keys);
		return true;
	}

	public boolean type(Object locator, String value) {
		_attempts = 3;

		String tagName;
		_script = "arguments[0].value=arguments[1];";

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				tagName = _element.getTagName();
				if ("input".equals(tagName) || "div".equals(tagName) || "span".equals(tagName) || "p".equals(tagName)
						|| "textarea".equals(tagName)) {
					_element.sendKeys(value);
				} else {
					_js.executeScript(_script, _element, value);
				}
				return true;
			} catch (Exception e) {
				--_attempts;
			}
		}
		return false;
	}

	public boolean typeAfterClear(Object locator, String value) {
		_element = _query.castLocator(locator);

		_element.clear();
		_element.sendKeys(value);

		return true;
	}

	public boolean clearTextField(Object locator) {
		_element = _query.castLocator(locator);
		if (toBeCleared(_element)) {
			_element.clear();
		}
		if (toBeCleared(_element)) {
			_mouse.click(locator);
			multipleKeyStrokes(locator, "end");
			multipleKeyStrokes(locator, "shift+home");
			multipleKeyStrokes(locator, "backspace");
		}
		return true;
	}

	private boolean toBeCleared(Object element) {
		boolean flag = true;

		String text = _query.getText(element);

		if (_verify.isStringMatch("", text)) {
			flag = false;
		}

		return flag;
	}

	public boolean multipleKeyStrokes(Object locator, String keys, int loopCount) {
		for (int i = 0; i < loopCount; i++) {
			multipleKeyStrokes(locator, keys);
		}
		return true;
	}
}
