package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotificationMessagePage extends BasePage {

    @FindBy(linkText = "Click here")
    private WebElement loadNewNotificationButton;

    @FindBy(css = "#flash")
    private WebElement actionStatus;

    public WebElement getLoadNewNotificationButton() {
        return loadNewNotificationButton;
    }

    public WebElement getActionStatus() {
        return actionStatus;
    }

    /**
     * Retries the action if the notification says that it was unsuccessful
     * @return : false upon 10 failed actions (highly improbable)
     */
    public boolean retryIfActionUnsuccessful() {
        for(int tries = 0; tries < 10; tries++) {
            loadNewNotificationButton.click();
            if(actionStatus.getText().equals("Action successful")) {
                return true;
            }
        }
        return false;
    }

}
