package gr.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base class for the web pages
 * Contains some basic functions that each page might use
 */
public class BasePage {

    public static WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor
     * @param driver: webdriver
     */
    public void setDriverInitElements(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
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

}
