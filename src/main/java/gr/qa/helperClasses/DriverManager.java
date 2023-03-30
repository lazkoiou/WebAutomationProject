package gr.qa.helperClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private final static Logger logger = LogManager.getLogger(DriverManager.class);

    public static ThreadLocal<EventFiringWebDriver> driver = new ThreadLocal<>();

    /**
     * Gets the driver
     * @return : the driver
     */
    public static EventFiringWebDriver getDriver() {
        return DriverManager.driver.get();
    }

    /**
     * Set the webdriver
     */
    public static void setDriver() {
        logger.info("Initializing web driver...");
        ChromeOptions chromeOptions = setupChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        DriverManager.driver.set(new EventFiringWebDriver(webDriver));
        customizeDriver();
        logger.info("The web driver has been initialized with hash code: " + DriverManager.driver.get().hashCode());
    }

    /**
     * Setup the chrome options to pass to the webdriver
     * @return : the chrome options
     */
    public static ChromeOptions setupChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*"); // this is needed because of a bug in chrome 111 (https://github.com/SeleniumHQ/selenium/issues/11750)
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
    public static void customizeDriver() {
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Quits the web driver
     */
    public static void tearDownDriver() {
        logger.info("Tearing down web driver.\n");
        DriverManager.driver.get().quit();
    }

    // TODO: This needs to be moved to BaseTest class
    /**
     * Sleeps for given time in ms
     * @param ms : milliseconds
     */
    public static void sleep(int ms) {
        logger.info("Sleeping for " + ms + " milliseconds...");
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
