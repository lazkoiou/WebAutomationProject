package gr.qa.pages.herokuapp.framePages;

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
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        return frameHeader.getText();
    }

    public String findTopMiddleFrameHeader() {
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        return frameHeader.getText();
    }

    public String findTopRightFrameHeader() {
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        return frameHeader.getText();
    }

    public String findBottomFrameHeader() {
        driver.switchTo().frame("frame-bottom");
        return frameHeader.getText();
    }

    public void load() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
    }

    public void isLoaded() {
        if (!driver.getCurrentUrl().contains("/nested_frames")) {
            throw new Error("Page is not loaded!");
        }
    }

}
