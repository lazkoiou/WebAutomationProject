package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ABTestingPage extends BasePage {

    @FindBy(css = ".example h3")
    private WebElement heading;

    public WebElement getHeading() {
        return heading;
    }
    
}
