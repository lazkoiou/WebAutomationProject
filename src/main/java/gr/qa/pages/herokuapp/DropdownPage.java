package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import gr.qa.pages.herokuapp.enums.HerokuTestPagesEnum;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    @FindBy(css = "#content .example h3")
    private WebElement title;

    @FindBy(css = "#dropdown")
    private WebElement dropdownList;

    @FindBy(css = "[selected='selected']")
    private WebElement selectedOption;

    public WebElement getDropdownList() {
        return dropdownList;
    }

    public WebElement getSelectedOption() {
        return selectedOption;
    }

    public void selectOptionFromDropdown(String optionText) {
        Select dropdownOptions = new Select(dropdownList);
        dropdownOptions.selectByVisibleText(optionText);
    }

    public String getTextFromSelectedOption() {
        return selectedOption.getText();
    }

    public void load() {
        DriverManager.get().get(HerokuTestPagesEnum.DROPDOWN.getUrl());
    }

    public void isLoaded() {
        if (!waitUntilVisible(title).getText().equals("Dropdown List")) {
            throw new Error("Page is not loaded!");
        }
    }

}
