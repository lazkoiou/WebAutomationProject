package gr.qa.pages.herokuapp.multipleWindows;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NewMultipleWindowsPage extends BasePage {

    @FindBy(css = ".example h3")
    private WebElement newWindowHeader;

    public void load() {
        DriverManager.get().get("https://the-internet.herokuapp.com/windows/new");
    }

    public void isLoaded() {
        if (!newWindowHeader.getText().equals("New Window")) {
            throw new Error("Page is not loaded!");
        }
    }

}
