package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class FileDownloadPage extends BasePage {

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(linkText = "some-file.txt")
    private WebElement fileToDownload;

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.FILE_DOWNLOAD.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.FILE_DOWNLOAD.getTitle())) {
            throw new Error("Page is not loaded!");
        }
    }
}
