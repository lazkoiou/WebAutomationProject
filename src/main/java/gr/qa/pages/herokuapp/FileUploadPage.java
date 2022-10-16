package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FileUploadPage extends BasePage {

    @FindBy(css = "#file-upload")
    private WebElement browseFile;

    @FindBy(css = "#file-submit")
    private WebElement fileSubmitButton;

    @FindBy(css = ".example h3")
    private WebElement successMessage;

    public WebElement getBrowseFile() {
        return browseFile;
    }

    public WebElement getFileSubmitButton() {
        return fileSubmitButton;
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }
}
