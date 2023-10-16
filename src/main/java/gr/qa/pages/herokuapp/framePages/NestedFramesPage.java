package gr.qa.pages.herokuapp.framePages;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NestedFramesPage extends BasePage {

    @FindBy(css = "body")
    private WebElement frameHeader;

    public WebElement getFrameHeader() {
        return frameHeader;
    }

    public String findTopLeftFrameHeader() {
        DriverManager.get().switchTo().frame("frame-top");
        DriverManager.get().switchTo().frame("frame-left");
        return frameHeader.getText();
    }

    public String findTopMiddleFrameHeader() {
        DriverManager.get().switchTo().frame("frame-top");
        DriverManager.get().switchTo().frame("frame-middle");
        return frameHeader.getText();
    }

    public String findTopRightFrameHeader() {
        DriverManager.get().switchTo().frame("frame-top");
        DriverManager.get().switchTo().frame("frame-right");
        return frameHeader.getText();
    }

    public String findBottomFrameHeader() {
        DriverManager.get().switchTo().frame("frame-bottom");
        return frameHeader.getText();
    }

    public void load() {
        DriverManager.get().get("https://the-internet.herokuapp.com/nested_frames");
    }

    public void isLoaded() {
        if (!DriverManager.get().getCurrentUrl().contains("/nested_frames")) {
            throw new Error("Page is not loaded!");
        }
    }

}
