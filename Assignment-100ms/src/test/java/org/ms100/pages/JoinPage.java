package org.ms100.pages;

import org.ms100.common.EnvironmentSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JoinPage extends EnvironmentSetup {

    @FindBy(css = "[data-testid = 'preview_name_field']")
    WebElement nameTextField;

    @FindBy(css = "[data-testid = 'preview_join_btn']")
    WebElement joinButton;

    public JoinPage() {
        PageFactory.initElements(factory, this);
    }

    public void waitForPageLoad() {
        waitHandler.waitForPageLoad();
        waitHandler.waitForVisibilityOf(nameTextField);
        waitHandler.waitForVisibilityOf(joinButton);
    }

    public void joinConference(String name) {
        keyboardHandler.typeAfterClear(nameTextField, name);
        mouseHandler.click(joinButton);
    }
}
