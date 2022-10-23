package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPage extends BasePage {

    @FindBy(css = ".example p")
    private WebElement pageMessage;

    public WebElement getPageMessage() {
        return pageMessage;
    }

}
