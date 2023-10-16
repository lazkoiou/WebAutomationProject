package gr.qa.pages.herokuapp.framePages;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class IFramePage extends BasePage {

    private final static Logger logger = LogManager.getLogger(IFramePage.class);

    @FindBy(css = "#tinymce")
    private WebElement editorText;

    public void inputTextIntoIFrameEditor(String text) {
        editorText.clear();
        editorText.sendKeys(text);
    }

    public void load() {
        DriverManager.get().get("https://the-internet.herokuapp.com/iframe");
    }

    public void isLoaded() {
        if (!DriverManager.get().getCurrentUrl().contains("/iframe")) {
            logger.error("Current url is: " + DriverManager.get().getCurrentUrl());
            throw new Error("Page is not loaded!");
        }
    }

}
