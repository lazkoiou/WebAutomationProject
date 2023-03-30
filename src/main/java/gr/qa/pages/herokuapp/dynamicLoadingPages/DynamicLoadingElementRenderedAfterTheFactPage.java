package gr.qa.pages.herokuapp.dynamicLoadingPages;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicLoadingElementRenderedAfterTheFactPage extends BasePage {

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(css = "#start button")
    private WebElement startButton;

    @FindBy(css = "#finish")
    private WebElement unhiddenText;

    public WebElement getStartButton() {
        return startButton;
    }

    public WebElement getUnhiddenText() {
        return unhiddenText;
    }

    public void load() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
    }

    public void isLoaded() {
        if (!title.getText().equals("Dynamically Loaded Page Elements")) {
            throw new Error("Page is not loaded!");
        }
    }

}
