package gr.qa.pages.herokuapp.dynamicLoadingPages;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DynamicLoadingHiddenElementPage extends BasePage {

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(css = "#start button")
    private WebElement startButton;

    @FindBy(css = "#finish")
    private WebElement unhiddenText;

    public void load() {
        DriverManager.get().get("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    public void isLoaded() {
        if (!title.getText().equals("Dynamically Loaded Page Elements")) {
            throw new Error("Page is not loaded!");
        }
    }

}
