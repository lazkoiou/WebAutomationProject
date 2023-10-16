package gr.qa.pages.herokuapp;

import gr.qa.helperClasses.DriverManager;
import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HerokuHomepage extends BasePage {

    @FindBy(css = "#content .heading")
    private WebElement title;

    public void load() {
        DriverManager.get().get("https://the-internet.herokuapp.com/");
    }

    public void isLoaded() {
        if (!title.getText().equals("Welcome to the-internet")) {
            throw new Error("Page is not loaded!");
        }
    }

}
