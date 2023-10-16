package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HoverPage extends BasePage {

    private final static Logger logger = LogManager.getLogger(HoverPage.class);

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(css = ".example .figure")
    private List<WebElement> userProfileFigures;

    /**
     * Iteratively hovers over the profile user image so that the username appears.
     * Extracts the username and adds it on a list.
     * @return : a list with profile usernames that have been extracted
     */
    public List<String> hoverAndGetUsername() {
        List<String> profileUsernames = new ArrayList<>();
        userProfileFigures.forEach(userProfileFigure -> {
            WebElement userProfileFigureImage = userProfileFigure.findElement(By.cssSelector("img"));
            Actions actions = new Actions(DriverManager.get());
            actions.moveToElement(userProfileFigureImage).perform();
            profileUsernames.add(userProfileFigure.findElement(By.cssSelector(".figcaption h5")).getText(). replace("name: ", ""));
        });
        return profileUsernames;
    }

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.HOVERS.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.HOVERS.getTitle())) {
            throw new Error("Page is not loaded!");
        }
    }

}
