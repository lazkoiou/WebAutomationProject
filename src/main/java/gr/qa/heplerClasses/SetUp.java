package gr.qa.heplerClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SetUp extends BaseObject {

    private final static Logger logger = LogManager.getLogger(SetUp.class);

    public static WebDriver webDriver = null;
    public static WebDriver driver = null;

    /**
     * Setup the webdriver
     */
    public void setupDriver() {
        logger.info("Initializing web driver...");
        loadProperties();
        ChromeOptions chromeOptions = setupChromeOptions();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(chromeOptions);
        driver = new EventFiringWebDriver(webDriver);
        customizeDriver();
        logger.info("The web driver has been initialized.");
    }

    /**
     * Setup the chrome options to pass to the webdriver
     * @return : the chrome options
     */
    public ChromeOptions setupChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        // the 5 lines below are needed for the FileDownloadTest
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\testFiles\\downloaded";
        logger.info("Setting default download filepath: " + filePath);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", filePath);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }


    /**
     * Customize the driver
     */
    public void customizeDriver() {
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Quits the web driver
     */
    public void tearDownDriver() {
        driver.quit();
    }

    /**
     * Sleeps for given amount of ms
     * @param ms : ms to sleep
     */
    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
