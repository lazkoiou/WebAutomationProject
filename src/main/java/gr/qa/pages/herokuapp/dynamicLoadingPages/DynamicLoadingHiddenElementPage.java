package gr.qa.pages.herokuapp.dynamicLoadingPages;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicLoadingHiddenElementPage extends BasePage {

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
}
