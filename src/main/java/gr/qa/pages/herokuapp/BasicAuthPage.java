package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasicAuthPage extends BasePage {

    private final static Logger logger = LogManager.getLogger(BasicAuthPage.class);

    @FindBy(css = ".example p")
    private WebElement pageMessage;

    public WebElement getPageMessage() {
        return pageMessage;
    }

    public void load() {
        driver.get(HerokuTestPagesEnum.BASIC_AUTH.getUrl());
    }

    public void isLoaded() {
        if (!driver.getCurrentUrl().contains("/basic_auth")) {
            logger.error("Url is not correct: " + driver.getCurrentUrl());
            throw new Error("Page is not loaded!");
        }
    }

}
