package gr.qa.pages.herokuapp.multipleWindows;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewMultipleWindowsPage extends BasePage {

    @FindBy(css = ".example h3")
    private WebElement newWindowHeader;

    public WebElement getNewWindowHeader() {
        return newWindowHeader;
    }

}
