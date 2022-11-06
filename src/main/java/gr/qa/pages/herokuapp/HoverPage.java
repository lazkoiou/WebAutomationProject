package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HoverPage extends BasePage {

    private final static Logger logger = LogManager.getLogger(HoverPage.class);

    @FindBy(css = ".example .figure")
    private List<WebElement> userProfileFigures;

    public List<WebElement> getUserProfileFigures() {
        return userProfileFigures;
    }

    public List<String> hoverAndGetUsername() {
        List<String> profileUsernames = new ArrayList<>();
        userProfileFigures.forEach(userProfileFigure -> {
            WebElement userProfileFigureImage = userProfileFigure.findElement(By.cssSelector("img"));
            Actions actions = new Actions(driver);
            actions.moveToElement(userProfileFigureImage).perform();
            profileUsernames.add(userProfileFigure.findElement(By.cssSelector(".figcaption h5")).getText(). replace("name: ", ""));
        });
        return profileUsernames;
    }

}
