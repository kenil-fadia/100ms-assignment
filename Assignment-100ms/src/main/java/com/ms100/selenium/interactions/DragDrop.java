package com.ms100.selenium.interactions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ms100.interactions.IDragDropOperations;
import com.ms100.interactions.IWindowOperations;

public class DragDrop implements IDragDropOperations {

    private Actions _action;
    private JavascriptExecutor _js;
    private QueryHtml _query;
    private WebDriver _driver;
    private WebElement _sourceElement, _targetElement;
    private IWindowOperations _browser;

    public DragDrop(Object driver) {
        _driver = (WebDriver) driver;
        _action = new Actions(_driver);
        _action.build();
        _js = (JavascriptExecutor) _driver;
        _query = new QueryHtml(_driver);
        _browser = new BrowserWindow(driver);
    }

    public boolean dragDrop(Object sourceLocator, Object targetLocator) {
        _sourceElement = _query.castLocator(sourceLocator);
        _targetElement = _query.castLocator(targetLocator);

        _action.clickAndHold(_sourceElement).moveToElement(_targetElement).release(_targetElement).perform();
        return true;
    }

    public boolean dragDrop(Object sourceLocator, int xOffset, int yOffset) {
        _action.dragAndDropBy(_query.castLocator(sourceLocator), xOffset, yOffset).perform();
        return true;
    }

    public boolean dragDropUsingCode(Object sourceLocator, Object targetLocator) {
        _sourceElement = _query.castLocator(sourceLocator);
        _targetElement = _query.castLocator(targetLocator);

        _browser.checkAndInjectSimulations();
        _js.executeScript("$(arguments[0]).simulate('drag-n-drop',{dragTarget:arguments[1],interpolation:{stepWidth:100,stepDelay:50}});", _sourceElement,
                        _targetElement);
        return true;
    }

    public boolean dragDropUsingCode(Object sourceLocator, int xOffset, int yOffset) {
        _sourceElement = _query.castLocator(sourceLocator);

        _browser.checkAndInjectSimulations();
        _js.executeScript("$(arguments[0]).simulate('drag-n-drop',{dx:arguments[1],dy:arguments[2],interpolation:{stepWidth:100,stepDelay:50}});",
                        _sourceElement, xOffset, yOffset);
        return true;
    }
}
