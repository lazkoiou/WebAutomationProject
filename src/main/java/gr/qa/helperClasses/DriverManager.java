package gr.qa.helperClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private final static Logger logger = LogManager.getLogger(DriverManager.class);

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    /**
     * Gets the webdriver (initializes one if not already initialized)
     */
    public synchronized static WebDriver get() {
        if (driver.get() == null) {
            logger.info("Initializing web driver...");
            ChromeOptions chromeOptions = setupChromeOptions(); // we can put an if here to distinguish between Desktop or Mobile initialization
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver(chromeOptions));
            customizeDriver();
            logger.info("The web driver has been initialized with hash code: " + DriverManager.driver.get().hashCode());
        }
        return  driver.get();
    }

    /**
     * Set up the chrome options to pass to the webdriver
     * @return : the chrome options
     */
    private static ChromeOptions setupChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*"); // this is needed because of a bug in Chrome 111 (https://github.com/SeleniumHQ/selenium/issues/11750)
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
    private static void customizeDriver() {
        driver.get().manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * Quits the web driver
     */
    public synchronized static void tearDownDriver() {
        logger.info("Tearing down web driver.\n");
        driver.get().close();
        driver.get().quit();
        driver.set(null);
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
