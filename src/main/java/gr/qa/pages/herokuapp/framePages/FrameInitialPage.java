package gr.qa.pages.herokuapp.framePages;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class FrameInitialPage extends BasePage {

    @FindBy(linkText = "Nested Frames")
    private WebElement nestedFramesLink;

    @FindBy(linkText = "iFrame")
    private WebElement iFrameLink;

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.FRAMES.getUrl());
    }

    public void isLoaded() {
        if (!DriverManager.get().getCurrentUrl().contains(HerokuTestPagesEnum.FRAMES.getUrl())) {
            throw new Error("Page is not loaded!");
        }
    }

}
