package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NotificationMessagePage extends BasePage {

    private final static Logger logger = LogManager.getLogger(NotificationMessagePage.class);

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(linkText = "Click here")
    private WebElement loadNewNotificationButton;

    @FindBy(css = "#flash")
    private WebElement actionStatus;

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.NOTIFICATION_MESSAGES.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.NOTIFICATION_MESSAGES.getTitle())) {
            throw new Error("Page is not loaded!");
        }
    }

    /**
     * Retries the action if the notification says that it was unsuccessful
     * @return : false upon 10 failed actions (highly improbable)
     */
    public boolean retryIfActionUnsuccessful() {
        for(int tries = 0; tries < 10; tries++) {
            loadNewNotificationButton.click();
            sleep(500);
            // the below element needs to be searched each time to find the updated value
            logger.info("***text: " + DriverManager.get().findElement(By.cssSelector("#flash")).getText());
            logger.info("condition: " + DriverManager.get().findElement(By.cssSelector("#flash")).getText().equals("Action successful"));
            if (DriverManager.get().findElement(By.cssSelector("#flash")).getText().contains("Action successful")) {
                return true;
            }
        }
        return false;
    }

}
