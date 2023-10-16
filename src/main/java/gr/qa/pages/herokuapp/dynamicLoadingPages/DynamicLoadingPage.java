package gr.qa.pages.herokuapp.dynamicLoadingPages;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class DynamicLoadingPage extends BasePage {

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(linkText = "Example 1: Element on page that is hidden")
    private WebElement hiddenElementExampleLink;

    @FindBy(linkText = "Example 2: Element rendered after the fact")
    private WebElement elementRenderedAfterwardsExampleLink;

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.DYNAMIC_LOADING.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.DYNAMIC_LOADING.getTitle())) {
            throw new Error("Page is not loaded!");
        }
    }

}
