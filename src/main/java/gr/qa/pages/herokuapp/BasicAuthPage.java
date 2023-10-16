package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class BasicAuthPage extends BasePage {

    private final static Logger logger = LogManager.getLogger(BasicAuthPage.class);

    @FindBy(css = ".example p")
    private WebElement pageMessage;

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.BASIC_AUTH.getUrl());
    }

    public void isLoaded() {
        if (!DriverManager.get().getCurrentUrl().contains("/basic_auth")) {
            logger.error("Url is not correct: " + DriverManager.get().getCurrentUrl());
            throw new Error("Page is not loaded!");
        }
    }

}
