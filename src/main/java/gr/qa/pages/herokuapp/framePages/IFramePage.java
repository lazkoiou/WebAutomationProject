package gr.qa.pages.herokuapp.framePages;

import gr.qa.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IFramePage extends BasePage {

    private final static Logger logger = LogManager.getLogger(IFramePage.class);

    @FindBy(css = "#tinymce")
    private WebElement editorText;

    public WebElement getEditorText() {
        return editorText;
    }

    public void inputTextIntoIFrameEditor(String text) {
        editorText.clear();
        editorText.sendKeys(text);
    }

    public void load() {
        driver.get("https://the-internet.herokuapp.com/iframe");
    }

    public void isLoaded() {
        if (!driver.getCurrentUrl().contains("/iframe")) {
            logger.error("Current url is: " + driver.getCurrentUrl());
            throw new Error("Page is not loaded!");
        }
    }

}
