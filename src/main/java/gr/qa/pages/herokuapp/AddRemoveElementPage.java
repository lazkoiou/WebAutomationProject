package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class AddRemoveElementPage extends BasePage {

    @FindBy(css = "#content h3")
    private WebElement title;

    @FindBy(css = ".example button")
    private WebElement addElementButton;

    @FindBy(css = "#elements .added-manually")
    private WebElement deleteElementButton;

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.ADD_REMOVE_ELEMENTS.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.ADD_REMOVE_ELEMENTS.getTitle())) {
            throw new AssertionError("Page is not loaded!");
        }
    }

}
