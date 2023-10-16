package gr.qa.pages;

import gr.qa.helperClasses.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base class for the web pages
 * Contains some basic functions that each page might use
 */
public abstract class BasePage extends LoadableComponent<BasePage> {

    private WebDriverWait wait;

    /**
     * Constructor
     */
    public void initializeElements() {
        wait = new WebDriverWait(DriverManager.get(), Duration.ofSeconds(10));
        PageFactory.initElements(DriverManager.get(), this);
    }

    /**
     * Sleeps for given ms
     */
    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

}
