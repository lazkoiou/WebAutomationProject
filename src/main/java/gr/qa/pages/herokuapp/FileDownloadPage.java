package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FileDownloadPage extends BasePage {

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(linkText = "some-file.txt")
    private WebElement fileToDownload;

    public WebElement getFileToDownload() {
        return fileToDownload;
    }

    public void load() {
        driver.get(HerokuTestPagesEnum.FILE_DOWNLOAD.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.FILE_DOWNLOAD.getTitle())) {
            throw new Error("Page is not loaded!");
        }
    }
}
