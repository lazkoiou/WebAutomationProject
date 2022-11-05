package gr.qa.pages.herokuapp.dynamicLoadingPages;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicLoadingPage extends BasePage {

    @FindBy(linkText = "Example 1: Element on page that is hidden")
    private WebElement hiddenElementExampleLink;

    @FindBy(linkText = "Example 2: Element rendered after the fact")
    private WebElement elementRenderedAfterwardsExampleLink;

    public WebElement getHiddenElementExampleLink() {
        return hiddenElementExampleLink;
    }

    public WebElement getElementRenderedAfterwardsExampleLink() {
        return elementRenderedAfterwardsExampleLink;
    }

}
