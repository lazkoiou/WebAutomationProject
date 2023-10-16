package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FileUploadPage extends BasePage {

    @FindBy(css = "#content .example h3") // this changes to the success message after successful upload
    private WebElement title;

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

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.FILE_UPLOAD.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.FILE_UPLOAD.getTitle())) {
            throw new Error("Page is not loaded!");
        }
    }

}
