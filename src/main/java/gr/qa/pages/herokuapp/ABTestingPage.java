package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Generated;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class ABTestingPage extends BasePage {

    private final static Logger logger = LogManager.getLogger(ABTestingPage.class);

    @FindBy(css = ".example h3")
    private WebElement heading;

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.AB_TESTING.getUrl());
    }

    public void isLoaded() {
        waitUntilVisible(heading);
        if (
                !(heading.getText().equals(HerokuTestPagesEnum.AB_TESTING.getTitle()) ||
                (heading.getText().equals("A/B Test Variation 1")))
            ) {
            logger.error("Title is not correct: " + heading.getText());
            throw new Error("Page is not loaded!");
        }
    }
    
}
