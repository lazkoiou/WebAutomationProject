package gr.qa.pages.herokuapp.framePages;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrameInitialPage extends BasePage {

    @FindBy(linkText = "Nested Frames")
    private WebElement nestedFramesLink;

    @FindBy(linkText = "iFrame")
    private WebElement iFrameLink;

    public WebElement getNestedFramesLink() {
        return nestedFramesLink;
    }

    public WebElement getiFrameLink() {
        return iFrameLink;
    }

}
