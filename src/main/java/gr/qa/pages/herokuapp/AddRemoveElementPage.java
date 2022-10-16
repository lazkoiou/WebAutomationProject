package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddRemoveElementPage extends BasePage {

    @FindBy(css = ".example button")
    private WebElement addElementButton;

    @FindBy(css = "#elements .added-manually")
    private WebElement deleteElementButton;

    public WebElement getAddElementButton() {
        return addElementButton;
    }

    public WebElement getDeleteElementButton() {
        return deleteElementButton;
    }

}
