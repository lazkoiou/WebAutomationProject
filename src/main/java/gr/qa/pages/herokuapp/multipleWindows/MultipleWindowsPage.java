package gr.qa.pages.herokuapp.multipleWindows;

import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class MultipleWindowsPage extends BasePage {

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(css = "#content .example a")
    private WebElement openNewWindowButton;

    public WebElement getOpenNewWindowButton() {
        return openNewWindowButton;
    }

    public void load() {
        driver.get(HerokuTestPagesEnum.MULTIPLE_WINDOWS.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals(HerokuTestPagesEnum.MULTIPLE_WINDOWS.getTitle())) {
            throw new Error("Page is not loaded!");
        }
    }

    /**
     * Clicks on the button to open a new window (tab)
     * and switch to it
     * @param firstTab : the window (tab) that we are currently on
     */
    public void openNewWindowAndSwitchToIt(String firstTab) {
        openNewWindowButton.click();
        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs) {
            if (!tab.equals(firstTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    /**
     * Close the newly opened window (tab) and switch to the previous one
     * @param firstTab : the previous window (tab)
     */
    public void closeNewWindowAndSwitchToPrevious(String firstTab) {
        driver.close();
        driver.switchTo().window(firstTab);
    }

}
