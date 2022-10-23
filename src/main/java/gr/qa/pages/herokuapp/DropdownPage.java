package gr.qa.pages.herokuapp;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

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

}
