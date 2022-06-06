package com.ms100.selenium.interactions;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ms100.interactions.IExecuteJavascript;
import com.ms100.interactions.IMouseOperations;
import com.ms100.interactions.IWindowOperations;

public class Mouse implements IMouseOperations {

	private Actions _action;
	private IExecuteJavascript _js;
	private int _attempts;
	private QueryHtml _query;
	private String _script;
	private Timeout _timeout;
	private WebDriver _driver;
	private WebElement _element;
	private IWindowOperations _browser;
	private String _browserName = "";

	public Mouse(Object driver) {
		_driver = (WebDriver) driver;
		_action = new Actions(_driver);
		_action.build();
		_js = new ExecuteJavascript(_driver);
		_query = new QueryHtml(_driver);
		_timeout = new Timeout(_driver);
		_browser = new BrowserWindow(driver);
		_browserName = _query.getBrowserName().toLowerCase();
	}

	private void click(WebElement pageElement) {
		_script = "arguments[0].click()";
		if (!_browserName.equalsIgnoreCase("Internet Explorer")) {
			scroll(pageElement);
		}
		try {
			pageElement.click();
		} catch (ElementNotVisibleException e) {
			_js.executeScript(_script, pageElement);
		} catch (RuntimeException e) {
			_js.executeScript(_script, pageElement);
		}
	}

	public boolean click(Object object) {
		_attempts = 5;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(object);
				_timeout.waitForElementToBeClickable(_element);
				click(_element);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean clickAllElementsLocatedBy(Object locator) {
		List<Object> elements = _query.findElements(locator);
		boolean flag = true;

		for (Object pageElement : elements) {
			_timeout.waitForElementToBeClickable(pageElement);
			_attempts = 3;
			while (_attempts > 0) {
				try {
					click(pageElement);
					break;
				} catch (Exception e) {
					if (_attempts < 2) {
						throw new RuntimeException(e.getLocalizedMessage());
					}
					--_attempts;
				}
			}
		}
		return flag;
	}

	public boolean contextClick(Object locator) {
		_timeout.waitForElementToBeClickable(locator);

		_attempts = 3;

		while (_attempts > 0) {
			try {
				((Actions) mouseOver(locator)).contextClick().perform();
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean doubleClick(Object locator) {
		_timeout.waitForElementToBeClickable(locator);

		_attempts = 3;

		while (_attempts > 0) {
			try {
				((Actions) mouseOver(locator)).doubleClick().perform();
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean doubleClickUsingCode(Object locator) {
		_attempts = 3;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				_browser.checkAndInjectSimulations();
				_js.executeScript("$(arguments[0]).simulate('dblclick')", _element);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean listClick(Object locator, int index) {
		_attempts = 5;

		while (_attempts > 0) {
			_element = (WebElement) _query.findElements(locator).get(index);

			scroll(_element);
			_timeout.waitForElementToBeClickable(locator);
			try {
				click(_element);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				_attempts--;
			}
		}
		return false;
	}

	public Object mouseOver(Object locator) {
		_attempts = 3;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				_action.moveToElement(_element).perform();
				return _action;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean mouseDown(Object locator) {
		_attempts = 3;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				_browser.checkAndInjectSimulations();
				_js.executeScript("$(arguments[0]).simulate('mousedown')", _element);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean mouseDown(Object locator, int x, int y) {
		_attempts = 3;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				_browser.checkAndInjectSimulations();
				_js.executeScript("$(arguments[0]).simulate('mousedown', {clientX:arguments[1],clientY:arguments[2]})",
						_element, x, y);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean mouseMove(Object locator, int x, int y) {
		_attempts = 3;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				_browser.checkAndInjectSimulations();
				_js.executeScript("$(arguments[0]).simulate('mousemove', {clientX:arguments[1],clientY:arguments[2]})",
						_element, x, y);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean mouseUp(Object locator) {
		_attempts = 3;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				_browser.checkAndInjectSimulations();
				_js.executeScript("$(arguments[0]).simulate('mousemoveup')", _element);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}

	public boolean scroll(int xVal, int yVal) {
		_script = "window.scrollBy(arguments[0], arguments[1])";
		_js.executeScript(_script, xVal, yVal);
		return true;
	}

	public boolean scroll(Object locator) {
		Object pageElement = _query.castLocator(locator);
		_js.executeScript("arguments[0].scrollIntoView(false);", pageElement);
		return true;
	}

	public boolean click(Object locator, boolean ctrlKey, boolean altKey, boolean shiftKey, boolean metaKey) {
		_attempts = 3;

		while (_attempts > 0) {
			try {
				_element = _query.castLocator(locator);
				_browser.checkAndInjectSimulations();
				_js.executeScript(
						"$(arguments[0]).simulate('mousedown', {ctrlKey:arguments[1],altKey:arguments[2],shiftKey:arguments[3],metaKey:arguments[4],button:0})",
						_element, ctrlKey, altKey, shiftKey, metaKey);
				_js.executeScript(
						"$(arguments[0]).simulate('mouseup', {ctrlKey:arguments[1],altKey:arguments[2],shiftKey:arguments[3],metaKey:arguments[4],button:0})",
						_element, ctrlKey, altKey, shiftKey, metaKey);
				return true;
			} catch (Exception e) {
				if (_attempts < 2) {
					throw new RuntimeException(e.getLocalizedMessage());
				}
				--_attempts;
			}
		}
		return false;
	}
}
