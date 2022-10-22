package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FileDownloadPage extends BasePage {

    @FindBy(linkText = "some-file.txt")
    private WebElement fileToDownload;

    public WebElement getFileToDownload() {
        return fileToDownload;
    }
}
